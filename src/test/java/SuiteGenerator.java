import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfoList;
import org.assertj.core.util.Arrays;
import org.testng.TestNG;
import org.testng.annotations.Test;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class SuiteGenerator {

    public static void main(String[] args) {
        if (args[0] != null && args[1] == null) {
            System.out.println(Thread.currentThread().getId() + ": No Package details available, running full suite at \"guidewireTests\" package");
            generateSuiteXML("1", "guidewireTests");
        } else {
            generateSuiteXML(args[0], args[1]);
        }
    }

    private static void generateSuiteXML(String threadCounts, String basePackage) {
        basePackage = System.getProperty("RunPackage") == null ? basePackage : System.getProperty("RunPackage");
        int threadCount = threadCounts == null ? 1 : Integer.valueOf(threadCounts);
        XmlSuite.ParallelMode parallelMode = XmlSuite.ParallelMode.TESTS;


        String suiteName = System.getProperty("SuiteName") == null ? "Regression" : System.getProperty("SuiteName");
        System.out.println(Thread.currentThread().getId() + ": !!!!!!! -- STARTING SUITE GENERATOR -- !!!!!!!");

        TestNG testNG = new TestNG();
        ClassGraph graph = new ClassGraph();
        ClassInfoList classesWithAnnotation = graph.whitelistPackages(basePackage).enableAllInfo().scan().getClassesWithMethodAnnotation(Test.class.getCanonicalName());

        // Add Listener
        XmlSuite xmlSuite = new XmlSuite();
        xmlSuite.setName(suiteName + " Regression");
        xmlSuite.setVerbose(1);
        if (threadCount > 1) {
            xmlSuite.setParallel(parallelMode);
        }

        xmlSuite.setThreadCount(threadCount);
        ArrayList<String> listeners = new ArrayList<>();
        listeners.add("framework.Listener");
        xmlSuite.setListeners(listeners);

        classesWithAnnotation.forEach(classInfo -> {
            // Add Test
            XmlTest xmlTest = new XmlTest(xmlSuite);
            xmlTest.setName(classInfo.getSimpleName());
            xmlTest.setPreserveOrder(true);
            if (threadCount > 1) {
                xmlTest.setParallel(parallelMode);
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

        testNG.setXmlSuites(Collections.singletonList(xmlSuite));
        System.out.println(xmlSuite.toXml());
        testNG.run();

    }


}
