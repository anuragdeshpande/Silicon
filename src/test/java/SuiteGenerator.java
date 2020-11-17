import annotations.APITest;
import annotations.ClockMoveTest;
import annotations.DisabledTest;
import annotations.SmokeTest;
import framework.constants.StringConstants;
import framework.suiteManager.SuiteCreator;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfoList;
import org.apache.commons.io.FileUtils;
import org.testng.TestNG;
import org.testng.annotations.Test;
import org.testng.xml.XmlSuite;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class SuiteGenerator {

    public static void main(String[] args) throws IOException {
        startTests(System.getProperty("ThreadCount"));
    }


    private static void startTests(String threadCounts) throws IOException {
        int threadCount = threadCounts == null ? 1 : Integer.parseInt(threadCounts);
        System.out.println(Thread.currentThread().getId() + ": !!!!!!! -- STARTING SUITE GENERATOR -- !!!!!!!");
        boolean isClockMove = System.getProperty("isClockMove", "false").equalsIgnoreCase("true");
        String runningNode = System.getProperty("RunningNode");
        ClassInfoList regressionTests;
        ClassGraph graph = new ClassGraph();
        System.out.println("Default Class white listing from RunPackage(s): "+System.getProperty("RunPackage"));
        regressionTests = graph.whitelistPackages(System.getProperty("RunPackage").split(",")).enableAllInfo().scan().getClassesWithAnnotation(Test.class.getCanonicalName());

        if (System.getProperty("LoadBalancedFile") != null) {
            String filePath;
            if (isClockMove) {
                filePath = StringConstants.DISTRIBUTED_TESTS_FILES_CLOCK_MOVE_LOCATION + "\\" + runningNode + ".txt";
            } else {
                filePath = StringConstants.DISTRIBUTED_TESTS_FILES_NON_CLOCK_MOVE_LOCATION + "\\" + runningNode + ".txt";
            }

            System.out.println("Resetting existing regression tests and Reading new tests from path: " + filePath);
            List<String> lines = FileUtils.readLines(new File(filePath), StandardCharsets.UTF_8);
            String[] classes = lines.toArray(new String[0]);
            regressionTests = graph.whitelistClasses(classes).enableAllInfo().scan().getClassesWithMethodAnnotation(Test.class.getCanonicalName());
        }

        ClassInfoList disabledTests = regressionTests.filter(classInfo -> classInfo.hasAnnotation(DisabledTest.class.getCanonicalName()));
        regressionTests = regressionTests.exclude(disabledTests);


        List<XmlSuite> suitesToRun = new ArrayList<>();
        boolean shouldRunSmokeTests = System.getProperty("TestType").equalsIgnoreCase(StringConstants.SMOKE_TEST);
        boolean shouldRunRegressionTests = System.getProperty("TestType").equalsIgnoreCase(StringConstants.UI_TEST);
        boolean shouldRunAPITests = System.getProperty("TestType").equalsIgnoreCase(StringConstants.API_TEST);

        /* Start Filtering: Need to remove all the other tests before creating the main regression tests */

        if (shouldRunSmokeTests) {
            ClassInfoList smokeTests = regressionTests.filter(classInfo -> classInfo.hasAnnotation(SmokeTest.class.getCanonicalName()))
                    .filter(classInfo -> classInfo.hasMethodAnnotation(SmokeTest.class.getCanonicalName()));
            System.out.println("Adding smoke tests: " + smokeTests.size());
            System.out.println(smokeTests);
            regressionTests = regressionTests.exclude(smokeTests);
            SuiteCreator creator = new SuiteCreator(isClockMove);
            suitesToRun.add(creator.createSuite(System.getProperty("SuiteName", "SmokeTests" + runningNode), smokeTests, threadCount));

        }

        if (shouldRunAPITests) {
            ClassInfoList apiTests = regressionTests.filter(classInfo -> classInfo.hasAnnotation(APITest.class.getCanonicalName()))
                    .filter(classInfo -> classInfo.hasMethodAnnotation(APITest.class.getCanonicalName()));
            System.out.println("Adding API Tests: " + apiTests.size());
            System.out.println(apiTests);
            regressionTests = regressionTests.exclude(apiTests);
            SuiteCreator creator = new SuiteCreator(isClockMove);
            suitesToRun.add(creator.createSuite(System.getProperty("SuiteName", "APITests" + runningNode), apiTests, threadCount));

        }

        /*  End Filtering and start main regression tests */

        if (shouldRunRegressionTests) {
            System.out.println("Total UI Tests: " + regressionTests.size());
            System.out.println(regressionTests);
            ClassInfoList clockMoveTests = regressionTests.filter(classInfo -> classInfo.hasAnnotation(ClockMoveTest.class.getCanonicalName()));
            System.out.println(clockMoveTests.size() + " Clock move tests");
            System.out.println(clockMoveTests);
            System.out.println("Request received for ClockMove: " + isClockMove + " running only requested tests");
            regressionTests = regressionTests.exclude(clockMoveTests);
            SuiteCreator creator = new SuiteCreator(isClockMove);
            if(isClockMove){
                suitesToRun.add(creator.createSuite(System.getProperty("SuiteName", "UI_Regression" + runningNode), clockMoveTests, threadCount));
            } else {
                suitesToRun.add(creator.createSuite(System.getProperty("SuiteName", "UI_Regression" + runningNode), regressionTests, threadCount));
            }
        }

        if (!suitesToRun.isEmpty()) {
            TestNG testNG = new TestNG();
            testNG.setXmlSuites(suitesToRun);
            if (!System.getProperty("DryRun", "false").equalsIgnoreCase("true")) {
                testNG.run();
            } else {
                System.out.println("Suite marked as Dry Run, Skipping the initialize process for TestNG");
            }
        } else {
            System.out.println("No Suites to run.");
        }
    }


    // Reference Methods
    /*private static void startGWUnitTests() {
        ClassGraph graph = new ClassGraph();
        String gwUnitTestPackage = System.getProperty("gwUnitTestPackage");
        ClassInfoList gwUnitTestRunners = graph.whitelistPackages(gwUnitTestPackage).enableAllInfo().scan().getClassesImplementing(IGWUnitTestRunner.class.getCanonicalName()).getStandardClasses();
        System.out.println("Found " + gwUnitTestRunners.size() + " Unit Test Runners in the package: " + gwUnitTestPackage);
        gwUnitTestRunners.forEach(classInfo -> {
            try {
                classInfo.loadClass(IGWUnitTestRunner.class).getConstructor().newInstance().runTests();
            } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                e.printStackTrace();
            }
        });

    }

    private static void startGWIntegrationTests() {
        ClassGraph graph = new ClassGraph();
        String gwIntegrationTestPackage = System.getProperty("gwIntegrationTestPackage");
        ClassInfoList gwIntegrationTestRunners = graph.whitelistPackages(gwIntegrationTestPackage).enableAllInfo().scan().getClassesImplementing(IGWIntegrationTestRunner.class.getCanonicalName()).getStandardClasses();
        System.out.println("Found " + gwIntegrationTestRunners.size() + " Unit Test Runners in the package: " + gwIntegrationTestPackage);
        gwIntegrationTestRunners.forEach(classInfo -> {
            try {
                classInfo.loadClass(IGWIntegrationTestRunner.class).getConstructor().newInstance().runTests();
            } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                e.printStackTrace();
            }
        });
    }

    private static void startGWSystemIntegrationTests() {
        String gwSystemIntegrationTestPackage = System.getProperty("gwSystemIntegrationTestPackage");
        ClassGraph graph = new ClassGraph();
        ClassInfoList gwSystemIntegrationTestRunners = graph.whitelistPackages(gwSystemIntegrationTestPackage).enableAllInfo().scan().getClassesImplementing(IGWSystemIntegrationTestRunner.class.getCanonicalName()).getStandardClasses();
        System.out.println("Found " + gwSystemIntegrationTestRunners.size() + " Unit Test Runners in the package: " + gwSystemIntegrationTestPackage);
        gwSystemIntegrationTestRunners.forEach(classInfo -> {
            try {
                classInfo.loadClass(IGWSystemIntegrationTestRunner.class).getConstructor().newInstance().runTests();
            } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                e.printStackTrace();
            }
        });
    }*/


}
