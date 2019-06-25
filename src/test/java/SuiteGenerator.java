import annotations.APITest;
import annotations.SmokeTest;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfoList;
import org.testng.TestNG;
import org.testng.annotations.Test;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SuiteGenerator {

    public static void main(String[] args) {
        if (args[0] != null && args[1] == null) {
            System.out.println(Thread.currentThread().getId() + ": No Package details available, running full suite at \"guidewireTests\" package");
            startTests("1", "guidewireTests");
        } else {
            startTests(args[0], args[1]);
        }
    }

    private static void startTests(String threadCounts, String basePackage) {
        basePackage = System.getProperty("RunPackage") == null ? basePackage : System.getProperty("RunPackage");
        int threadCount = threadCounts == null ? 1 : Integer.valueOf(threadCounts);
        String suiteName = System.getProperty("SuiteName") == null ? "Regression" : System.getProperty("SuiteName");
        System.out.println(Thread.currentThread().getId() + ": !!!!!!! -- STARTING SUITE GENERATOR -- !!!!!!!");


        ClassGraph graph = new ClassGraph();
        ClassInfoList regressionTests = graph.whitelistPackages(basePackage).enableAllInfo().scan().getClassesWithMethodAnnotation(Test.class.getCanonicalName());

        List<XmlSuite> suitesToRun = new ArrayList<>();
        boolean shouldRunSmokeTests = System.getProperty("EnableSmokeTests") != null;
        boolean shouldRunRegressionTests = System.getProperty("EnableRegressionTests") != null;
        boolean shouldRunAPITests = System.getProperty("EnableAPITests") != null;

        /* Start Filtering: Need to remove all the other tests before creating the main regression tests */
        if(shouldRunSmokeTests){
            ClassInfoList smokeTests = regressionTests.filter(classInfo -> classInfo.hasMethodAnnotation(SmokeTest.class.getCanonicalName()));
            System.out.println("Adding smoke tests: "+smokeTests.size());
            regressionTests = regressionTests.exclude(smokeTests);
            suitesToRun.add(createSuite("SmokeTests", smokeTests, threadCount));
        }

        if(shouldRunAPITests){
            ClassInfoList apiTests = regressionTests.filter(classInfo -> classInfo.hasMethodAnnotation(APITest.class.getCanonicalName()));
            System.out.println("Adding API Tests: " + apiTests.size());
            regressionTests = regressionTests.exclude(apiTests);
            suitesToRun.add(createSuite("APITests", apiTests, threadCount));
        }

        /*  End Filtering and start main regression tests */

        if(shouldRunRegressionTests){
            System.out.println("Adding Regression Tests: "+regressionTests.size());
            suitesToRun.add(createSuite(suiteName,regressionTests, threadCount));
        }

        if(!suitesToRun.isEmpty()){
            TestNG testNG = new TestNG();
            testNG.setXmlSuites(suitesToRun);
            testNG.run();
        } else {
            System.out.println("No Suites to run.");
        }
    }


    private static XmlSuite createSuite(String suiteName, ClassInfoList testClasses, int threadCount){
        XmlSuite xmlSuite = new XmlSuite();
        xmlSuite.setName(suiteName);
        xmlSuite.setVerbose(1);
        if (threadCount > 1) {
            xmlSuite.setParallel(XmlSuite.ParallelMode.TESTS);
        }

        xmlSuite.setThreadCount(threadCount);
        ArrayList<String> listeners = new ArrayList<>();
        listeners.add("framework.Listener");
        xmlSuite.setListeners(listeners);

        testClasses.forEach(classInfo -> {
            // Add Test
            XmlTest xmlTest = new XmlTest(xmlSuite);
            xmlTest.setName(classInfo.getSimpleName());
            xmlTest.setPreserveOrder(true);
            if (threadCount > 1) {
                xmlTest.setParallel(XmlSuite.ParallelMode.TESTS);
            }
            xmlTest.setThreadCount(threadCount);

            // Add Classes
            LinkedList<XmlClass> classes = new LinkedList<>();
            XmlClass xmlClass = new XmlClass();
            xmlClass.setName(classInfo.getName());
            classes.add(xmlClass);
            xmlTest.setClasses(classes);

            if (Boolean.valueOf(System.getProperty("isClockMove"))) {
                xmlTest.addIncludedGroup("ClockMove");
            } else {
                xmlTest.addExcludedGroup("ClockMove");
            }
        });

        System.out.println(xmlSuite.toXml());
        return xmlSuite;

    }
}
