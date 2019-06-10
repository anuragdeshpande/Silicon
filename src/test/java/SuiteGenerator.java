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
        ClassInfoList classesWithAnnotation = graph.whitelistPackages(basePackage).enableAllInfo().scan().getClassesWithMethodAnnotation(Test.class.getCanonicalName());
        ClassInfoList regressionTests = classesWithAnnotation;

        List<XmlSuite> suitesToRun = new ArrayList<>();
        boolean shouldRunSmokeTests = System.getProperty("EnableSmokeTests") != null;
        boolean shouldRunRegressionTests = System.getProperty("EnableRegressionTests") != null;

        if(shouldRunSmokeTests){
            ClassInfoList smokeTests = regressionTests.filter(classInfo -> classInfo.hasAnnotation(SmokeTest.class.getCanonicalName()));
            regressionTests = regressionTests.exclude(smokeTests);
            suitesToRun.add(createSuite("Smoke Tests", smokeTests, threadCount));
        }

        if(shouldRunRegressionTests){
            suitesToRun.add(createSuite(suiteName,regressionTests, threadCount));
        }

        if(!suitesToRun.isEmpty()){
            TestNG testNG = new TestNG();
            testNG.setXmlSuites(suitesToRun);
            testNG.run();
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

        return xmlSuite;

    }
}
