import annotations.APITest;
import annotations.ClockMoveTest;
import annotations.SmokeTest;
import framework.applications.gw.gwTestRunner.IGWIntegrationTestRunner;
import framework.applications.gw.gwTestRunner.IGWSystemIntegrationTestRunner;
import framework.applications.gw.gwTestRunner.IGWUnitTestRunner;
import framework.suiteManager.SuiteCreator;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfoList;
import org.testng.TestNG;
import org.testng.annotations.Test;
import org.testng.xml.XmlSuite;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class SuiteGenerator {

    public static void main(String[] args) {
        boolean runUITests =
                System.getProperty("EnableRegressionTests") != null ||
                        System.getProperty("EnableAPITests") != null ||
                        System.getProperty("EnableSmokeTests") != null;

        boolean runGWUnitTests =
                System.getProperty("gwUnitTestPackage") != null;

        boolean runGWIntegrationTests =
                System.getProperty("gwIntegrationTestPackage") != null;

        boolean runGWSystemIntegrationTests =
                System.getProperty("gwSystemIntegrationTestPackage") != null;

        if (runUITests) {
            startTests(System.getProperty("ThreadCount"), System.getProperty("RunPackage"));
        }

        if (runGWUnitTests) {
            startGWUnitTests();
        }

        if (runGWIntegrationTests) {
            startGWIntegrationTests();
        }

        if (runGWSystemIntegrationTests) {
            startGWSystemIntegrationTests();
        }

    }

    private static void startGWUnitTests() {
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
    }

    private static void startTests(String threadCounts, String basePackage) {
        basePackage = System.getProperty("RunPackage") == null ? basePackage : System.getProperty("RunPackage");
        int threadCount = threadCounts == null ? 1 : Integer.parseInt(threadCounts);
        System.out.println(Thread.currentThread().getId() + ": !!!!!!! -- STARTING SUITE GENERATOR -- !!!!!!!");


        ClassGraph graph = new ClassGraph();
        ClassInfoList regressionTests = graph.whitelistPackages(basePackage.split(",")).enableAllInfo().scan().getClassesWithMethodAnnotation(Test.class.getCanonicalName());

        List<XmlSuite> suitesToRun = new ArrayList<>();
        boolean shouldRunSmokeTests = System.getProperty("EnableSmokeTests") != null && System.getProperty("EnableSmokeTests").equalsIgnoreCase("true");
        boolean shouldRunRegressionTests = System.getProperty("EnableRegressionTests") != null && System.getProperty("EnableRegressionTests").equalsIgnoreCase("true");
        boolean shouldRunAPITests = System.getProperty("EnableAPITests") != null && System.getProperty("EnableAPITests").equalsIgnoreCase("true");

        /* Start Filtering: Need to remove all the other tests before creating the main regression tests */
        if (shouldRunSmokeTests) {
            ClassInfoList smokeTests = regressionTests.filter(classInfo -> classInfo.hasMethodAnnotation(SmokeTest.class.getCanonicalName()));
            System.out.println("Adding smoke tests: " + smokeTests.size());
            System.out.println(smokeTests);
            regressionTests = regressionTests.exclude(smokeTests);
            if(System.getProperty("isClockMove", "false").equalsIgnoreCase("true")){
                SuiteCreator creator = new SuiteCreator(true);
                suitesToRun.add(creator.createSuite(System.getProperty("SuiteName", "SmokeTestsClockMove"), smokeTests, threadCount));
            }

            SuiteCreator creator = new SuiteCreator(false);
            suitesToRun.add(creator.createSuite(System.getProperty("SuiteName", "SmokeTests"), smokeTests, threadCount));

        }

        if (shouldRunAPITests) {
            ClassInfoList apiTests = regressionTests.filter(classInfo -> classInfo.hasMethodAnnotation(APITest.class.getCanonicalName()));
            System.out.println("Adding API Tests: " + apiTests.size());
            System.out.println(apiTests);
            regressionTests = regressionTests.exclude(apiTests);
            if(System.getProperty("isClockMove", "false").equalsIgnoreCase("true")) {
                SuiteCreator creator = new SuiteCreator(true);
                suitesToRun.add(creator.createSuite(System.getProperty("SuiteName", "APITestsClockMove"), apiTests, threadCount));
            }

            SuiteCreator creator = new SuiteCreator(false);
            suitesToRun.add(creator.createSuite(System.getProperty("SuiteName", "APITests"), apiTests, threadCount));

        }

        /*  End Filtering and start main regression tests */

        if (shouldRunRegressionTests) {
            System.out.println("Adding Regression Tests: " + regressionTests.size());
            System.out.println(regressionTests);
            ClassInfoList clockMoveTests = regressionTests.filter(classInfo -> classInfo.hasAnnotation(ClockMoveTest.class.getCanonicalName()));
            System.out.println(clockMoveTests.size()+" Clock move tests");
            System.out.println(clockMoveTests);
            if(System.getProperty("isClockMove", "false").equalsIgnoreCase("true")) {
                SuiteCreator creator = new SuiteCreator(true);
                suitesToRun.add(creator.createSuite(System.getProperty("SuiteName", "UI_Regression_TestsClockMove"), regressionTests, threadCount));
            } else {
                SuiteCreator creator = new SuiteCreator(false);
                suitesToRun.add(creator.createSuite(System.getProperty("SuiteName", "UI_Regression_Tests"), regressionTests, threadCount));
            }
        }

        if (!suitesToRun.isEmpty()) {
            TestNG testNG = new TestNG();
            testNG.setXmlSuites(suitesToRun);
            testNG.run();
        } else {
            System.out.println("No Suites to run.");
        }
    }



}
