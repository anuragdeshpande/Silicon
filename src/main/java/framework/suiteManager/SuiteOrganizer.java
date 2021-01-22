package framework.suiteManager;

import annotations.*;
import framework.ReportManager;
import framework.constants.StringConstants;
import framework.database.ConnectionManager;
import framework.database.models.DBConnectionDTO;
import framework.database.models.TestRuntimeDTO;
import framework.suiteManager.dtos.PackagedSuite;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfoList;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class SuiteOrganizer {

    private static List<TestRuntimeDTO> dtos;
    private static final List<TestRuntimeDTO> nonClockMoveDTOs;
    private static final List<TestRuntimeDTO> clockMoveDTOs;
    private static final List<TestRuntimeDTO> potentialTests;


    static {
        nonClockMoveDTOs = new ArrayList<>();
        clockMoveDTOs = new ArrayList<>();
        try {
            deleteVoidTests();
            dtos = getAllExistingTestsFromDB();
            potentialTests = indexPotentialTestsInRunPackage();
            dtos = getAllExistingTestsFromDB();
            dtos.forEach(runtimeDTO -> {
                if(runtimeDTO.getIsClockMove().equalsIgnoreCase("true")){
                    clockMoveDTOs.add(runtimeDTO);
                } else {
                    nonClockMoveDTOs.add(runtimeDTO);
                }
            });
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
    }

    public SuiteOrganizer() {

    }

    public static void main(String[] args) throws IOException {
        int threadCount = Integer.parseInt(System.getProperty("ThreadCount", "7"));
        ArrayList<PackagedSuite> nonClockMovePackages = PackagedSuite.initializePackages(threadCount, System.getProperty("DistributeAgainstNonClock", "TR01,TR02,TR03,TR04,TR05,TR06,TR07,TR08,TR09,TR10,QA1,QA2,QA3,QA4,QA5,QA6,QA7,QA8,QA10"));
        ArrayList<PackagedSuite> clockMovePackages = PackagedSuite.initializePackages(threadCount, System.getProperty("DistributeAgainstClock", "TR01,TR02,TR03,TR04,TR05,TR06"));
        distributeTests(nonClockMoveDTOs.iterator(), nonClockMovePackages);
        distributeTests(clockMoveDTOs.iterator(), clockMovePackages);

        // outputting distributed package numbers
        System.out.println("Non Clock Tests Distribution");
        writeFilesToResources(nonClockMovePackages, StringConstants.DISTRIBUTED_TESTS_FILES_NON_CLOCK_MOVE_LOCATION);
        System.out.println("Clock Move Tests Distribution");
        writeFilesToResources(clockMovePackages, StringConstants.DISTRIBUTED_TESTS_FILES_CLOCK_MOVE_LOCATION);
    }

    private static void sortPackages(ArrayList<PackagedSuite> packages) {
        packages.sort((o1, o2) -> {
            Long O1 = o1.getTotalRuntime();
            Long O2 = o2.getTotalRuntime();
            return O1.compareTo(O2);
        });
    }

    private static void writeFilesToResources(ArrayList<PackagedSuite> packages, String writeLocation) throws IOException {
        if(!new File(writeLocation).exists()){
            new File(writeLocation).mkdirs();
        }



        for (PackagedSuite aPackage : packages) {
            System.out.println(aPackage.toString());
            FileWriter writer = new FileWriter(writeLocation +"\\"+ aPackage.getPackageIdentifier() + ".txt");
            for (String testName : aPackage.getTestNames()) {
                writer.write(testName);
                writer.write("\n");
            }
            writer.close();

        }
    }

    private static List<TestRuntimeDTO> indexPotentialTestsInRunPackage() {
        String basePackage = System.getProperty("RunPackage");
        String projectSource = System.getProperty("ApplicationName");
        if (basePackage != null) {
            ClassGraph graph = new ClassGraph();
            ClassInfoList classesWithTestAnnotation = graph.whitelistPackages(basePackage.split(",")).enableAllInfo().scan().getClassesWithMethodAnnotation(Test.class.getCanonicalName());
            // Excluding disabled tests
            ClassInfoList disabledTests = classesWithTestAnnotation.filter(classInfo -> classInfo.hasAnnotation(DisabledTest.class.getCanonicalName()));
            classesWithTestAnnotation = classesWithTestAnnotation.exclude(disabledTests);

            // Excluding script tests
            ClassInfoList postResetScriptTests = classesWithTestAnnotation.filter(classInfo -> classInfo.hasMethodAnnotation(PostResetScript.class.getCanonicalName()));
            classesWithTestAnnotation = classesWithTestAnnotation.exclude(postResetScriptTests);
            ClassInfoList preResetScriptTests = classesWithTestAnnotation.filter(classInfo -> classInfo.hasMethodAnnotation(PreResetScript.class.getCanonicalName()));
            classesWithTestAnnotation = classesWithTestAnnotation.exclude(preResetScriptTests);

            Map<String, TestRuntimeDTO> classNames = dtos.stream().collect(Collectors.toMap(TestRuntimeDTO::getFullClassName, e -> e));
            Set<String> newClassesInserted = new HashSet<>();
            ArrayList<TestRuntimeDTO> potentialTests = new ArrayList<>();
            classesWithTestAnnotation.forEach(classInfo -> {
                String testType = StringConstants.UI_TEST;
                if (classInfo.hasAnnotation(SmokeTest.class.getCanonicalName())) {
                    testType = StringConstants.SMOKE_TEST;
                }

                if (classInfo.hasAnnotation(APITest.class.getCanonicalName())) {
                    testType = StringConstants.API_TEST;
                }

                TestRuntimeDTO runtimeDTO = TestRuntimeDTO.getInstance(classInfo.getName(), classInfo.getPackageName(), classNames.containsKey(classInfo.getName()) ? classNames.get(classInfo.getName()).getTotalRuntime() : 1, projectSource, classInfo.hasAnnotation(ClockMoveTest.class.getCanonicalName()) + "", testType);
                ReportManager.insertIntoTestRuntimeCatalog(runtimeDTO);
                potentialTests.add(runtimeDTO);
                newClassesInserted.add(classInfo.getName());
            });

            System.out.println("Inserted/ Updated classes that were not indexed before ("+newClassesInserted.size()+"):" + Arrays.toString(newClassesInserted.toArray()));
            return potentialTests;
        } else {
            throw new IllegalStateException("No Base Package details found. Value is: " + basePackage + ". Cannot start proper regression run without full indexing of the tests");
        }

    }

    private static void deleteVoidTests() throws SQLException {
        String basePackage = System.getProperty("RunPackage");
        // deleting tests where their run times are at 0 or 1
        QueryRunner regressionDB = ConnectionManager.getDBConnectionTo(DBConnectionDTO.TEST_NG_REPORTING_SERVER);
        int totalRowsUpdated = regressionDB.update("Delete from TestRuntimeCatalog where packageName = '"+basePackage+"' and  totalRunTime = 1 or totalRunTime = 0");
        System.out.println("Deleted "+totalRowsUpdated+" rows that were at runtime = 0/1 so that the current run could insert if the tests are still needed");
    }

    private static List<TestRuntimeDTO> getAllExistingTestsFromDB() throws SQLException {
        String testType = System.getProperty("TestType");
        String projectSource = System.getProperty("ApplicationName");
        QueryRunner regressionDB = ConnectionManager.getDBConnectionTo(DBConnectionDTO.TEST_NG_REPORTING_SERVER);
        return regressionDB.query("select * from TestRuntimeCatalog where projectSource='" + projectSource + "' and testType='" + testType + "' order by totalRunTime DESC", new BeanListHandler<>(TestRuntimeDTO.class));
    }

    private static void distributeTests(Iterator<TestRuntimeDTO> sourceIterator, ArrayList<PackagedSuite> targetPackages){
        int currentIterationCount = 0;
        long currentMaxRuntime = 0;

        Iterator<PackagedSuite> packageIterator;
        while (sourceIterator.hasNext()) {
            TestRuntimeDTO dto = sourceIterator.next();
            Optional<TestRuntimeDTO> match = potentialTests.stream().filter(potentialTest -> potentialTest.getFullClassName().equalsIgnoreCase(dto.getFullClassName())).findFirst();
            if(match.isPresent()) {
                // sort package list
                sortPackages(targetPackages);
                packageIterator = targetPackages.iterator();
                PackagedSuite aPackage = packageIterator.next();
                // add to the list if the packagedSuite is in current round or still in previous round
                if ((aPackage.getTotalRuntime() <= currentMaxRuntime) && (aPackage.getIterationCount() <= currentIterationCount)) {
                    long updatedPackageRunTime = aPackage.addTest(dto.getFullClassName(), dto.getTotalRuntime());
                    if (updatedPackageRunTime > currentMaxRuntime) {
                        currentMaxRuntime = updatedPackageRunTime;
                    }
                    if (currentIterationCount < aPackage.getIterationCount()) {
                        currentIterationCount = aPackage.getIterationCount();
                    }
                }
            }
        }
    }
}
