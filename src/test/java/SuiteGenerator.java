import annotations.*;
import framework.constants.StringConstants;
import framework.suiteManager.SuiteCreator;
import framework.webdriver.ThreadFactory;
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

    public static void main(final String[] args) throws IOException {
        startTests(System.getProperty("ThreadCount"));
    }


    private static void startTests(final String threadCounts) throws IOException {
        final int threadCount = threadCounts == null ? 1 : Integer.parseInt(threadCounts);
        System.out.println(ThreadFactory.getID() + ": !!!!!!! -- STARTING SUITE GENERATOR -- !!!!!!!");
        final boolean isClockMove = System.getProperty("isClockMove", "false").equalsIgnoreCase("true");
        final String runningNode = System.getProperty("RunningNode");
        ClassInfoList regressionTests = null;
        ClassGraph graph = new ClassGraph();
        final String runPackage = System.getProperty("RunPackage");
        if (runPackage != null && !runPackage.isEmpty()) {
            System.out.println("Default Class white listing from RunPackage(s): " + runPackage);
            regressionTests = graph.acceptPackages(runPackage.split(",")).enableAllInfo().scan().getClassesWithMethodAnnotation(Test.class.getCanonicalName());
        }

        final String runClasses = System.getProperty("RunClasses");
        System.out.println("Default Class(es) To be run: " + runClasses);
        if (runClasses != null && !runClasses.isEmpty()) {
            final ClassInfoList classesWithAnnotation = graph.acceptClasses(runClasses.split(",")).enableAllInfo().scan().getAllClasses();
            if (regressionTests != null) {
                regressionTests.addAll(classesWithAnnotation);
            } else {
                regressionTests = classesWithAnnotation;
            }
        }

        if (System.getProperty("LoadBalancedFile") != null
                && !System.getProperty("LoadBalancedFile").isEmpty()
                && System.getProperty("LoadBalancedFile").equalsIgnoreCase("true")) {
            regressionTests = null;
            graph = new ClassGraph();
            final String filePath;
            if (isClockMove) {
                filePath = StringConstants.DISTRIBUTED_TESTS_FILES_CLOCK_MOVE_LOCATION + "\\" + runningNode + ".txt";
            } else {
                filePath = StringConstants.DISTRIBUTED_TESTS_FILES_NON_CLOCK_MOVE_LOCATION + "\\" + runningNode + ".txt";
            }

            System.out.println("Resetting existing regression tests and Reading new tests from path: " + filePath);
            final List<String> lines = FileUtils.readLines(new File(filePath), StandardCharsets.UTF_8);
            final String[] classes = lines.toArray(new String[0]);
            if (classes.length > 0) {
                regressionTests = graph.acceptClasses(classes).enableAllInfo().scan().getClassesWithMethodAnnotation(Test.class.getCanonicalName());
            }
        }

        final List<XmlSuite> suitesToRun = new ArrayList<>();
        if (regressionTests != null && regressionTests.size() > 0) {
            final ClassInfoList disabledTests = regressionTests.filter(classInfo -> classInfo.hasAnnotation(DisabledTest.class.getCanonicalName()));
            regressionTests = regressionTests.exclude(disabledTests);

            final boolean shouldRunCPPTests = System.getProperty("IncludeCPPTests", "false").equalsIgnoreCase("true");
            if (!shouldRunCPPTests) {
                final ClassInfoList cppOnlyTests = regressionTests.filter(classInfo -> classInfo.hasAnnotation(CPPTest.class.getCanonicalName()));
                regressionTests = regressionTests.exclude(cppOnlyTests);
            }


            final boolean shouldRunSmokeTests = System.getProperty("TestType").equalsIgnoreCase(StringConstants.SMOKE_TEST);
            final boolean shouldRunRegressionTests = System.getProperty("TestType").equalsIgnoreCase(StringConstants.UI_TEST);
            final boolean shouldRunAPITests = System.getProperty("TestType").equalsIgnoreCase(StringConstants.API_TEST);

            /* Start Filtering: Need to remove all the other tests before creating the main regression tests */
            if (shouldRunSmokeTests) {
                final ClassInfoList smokeTests = regressionTests.filter(classInfo -> classInfo.hasAnnotation(SmokeTest.class.getCanonicalName()));
                System.out.println("Adding smoke tests: " + smokeTests.size());
                System.out.println(smokeTests);
                regressionTests = regressionTests.exclude(smokeTests);
                final SuiteCreator creator = new SuiteCreator(isClockMove);
                suitesToRun.add(creator.createSuite(System.getProperty("SuiteName", "SmokeTests" + runningNode), smokeTests, threadCount));

            }

            if (shouldRunAPITests) {
                final ClassInfoList apiTests = regressionTests.filter(classInfo -> classInfo.hasAnnotation(APITest.class.getCanonicalName()));
                System.out.println("Adding API Tests: " + apiTests.size());
                System.out.println(apiTests);
                regressionTests = regressionTests.exclude(apiTests);
                final SuiteCreator creator = new SuiteCreator(isClockMove);
                suitesToRun.add(creator.createSuite(System.getProperty("SuiteName", "APITests" + runningNode), apiTests, threadCount));

            }

            /*  End Filtering and start main regression tests */
            if (shouldRunRegressionTests) {
                System.out.println("Total UI Tests: " + regressionTests.size());
                System.out.println(regressionTests);
                final ClassInfoList clockMoveTests = regressionTests.filter(classInfo -> classInfo.hasAnnotation(ClockMoveTest.class.getCanonicalName()));
                System.out.println(clockMoveTests.size() + " Clock move tests");
                System.out.println(clockMoveTests);
                System.out.println("Request received for ClockMove: " + isClockMove + " running only requested tests");
                regressionTests = regressionTests.exclude(clockMoveTests);
                final SuiteCreator creator = new SuiteCreator(isClockMove);
                if (isClockMove) {
                    suitesToRun.add(creator.createSuite(System.getProperty("SuiteName", "UI_Regression" + runningNode), clockMoveTests, threadCount));
                } else {
                    suitesToRun.add(creator.createSuite(System.getProperty("SuiteName", "UI_Regression" + runningNode), regressionTests, threadCount));
                }
            }
        }

        if (!suitesToRun.isEmpty()) {
            final TestNG testNG = new TestNG();
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
}
