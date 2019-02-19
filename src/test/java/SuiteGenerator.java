import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfoList;
import org.testng.TestNG;
import org.testng.annotations.Test;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.util.Collections;
import java.util.LinkedList;

public class SuiteGenerator {

    public static void main(String[] args) {
        generateSuiteXML(args[0]);
    }

    public static void generateSuiteXML(String basePackage) {

        System.out.println("!!!!!!! -- STARTING SUITE GENERATOR -- !!!!!!!");

        TestNG testNG = new TestNG();
        ClassGraph graph = new ClassGraph();
        ClassInfoList classesWithAnnotation = graph.whitelistPackages(basePackage).enableAllInfo().scan().getClassesWithMethodAnnotation(Test.class.getCanonicalName());

        // Add Listener
        XmlSuite xmlSuite = new XmlSuite();
        xmlSuite.setName("Regression");
        xmlSuite.setVerbose(1);
        xmlSuite.setParallel(XmlSuite.ParallelMode.METHODS);
        xmlSuite.setThreadCount(45);
        xmlSuite.setListeners(Collections.singletonList("framework.Listener"));

        // Add Test
        XmlTest xmlTest = new XmlTest(xmlSuite);
        xmlTest.setName("Regression");

        xmlTest.setParallel(XmlSuite.ParallelMode.CLASSES);
        xmlTest.setPreserveOrder(false);

        // Add Classes
        LinkedList<XmlClass> classes = new LinkedList<>();
        classesWithAnnotation.forEach(classInfo -> {
            XmlClass xmlClass = new XmlClass();
            xmlClass.setName(classInfo.getName());
            classes.add(xmlClass);
        });
        xmlTest.setClasses(classes);

        if (Boolean.valueOf(System.getProperty("isClockMove"))) {
            xmlTest.addIncludedGroup("ClockMove");
        } else {
            xmlTest.addExcludedGroup("ClockMove");
        }

        testNG.setXmlSuites(Collections.singletonList(xmlSuite));
        testNG.run();

    }



}
