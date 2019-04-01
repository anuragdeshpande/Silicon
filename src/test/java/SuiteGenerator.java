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
        if(args[0] == null){
            System.out.println("No Package details available, running full suite at \"guidewireTests\" package");
            generateSuiteXML("guidewireTests");
        } else {
            generateSuiteXML(args[0]);
        }
    }

    private static void generateSuiteXML(String basePackage) {
        String suiteName = System.getProperty("ApplicationName") == null ? "Regression" : System.getProperty("ApplicationName");
        System.out.println("!!!!!!! -- STARTING SUITE GENERATOR -- !!!!!!!");

        TestNG testNG = new TestNG();
        ClassGraph graph = new ClassGraph();
        ClassInfoList classesWithAnnotation = graph.whitelistPackages(basePackage).enableAllInfo().scan().getClassesWithMethodAnnotation(Test.class.getCanonicalName());

        // Add Listener
        XmlSuite xmlSuite = new XmlSuite();
        xmlSuite.setName(suiteName+" Regression");
        xmlSuite.setVerbose(1);
//        xmlSuite.setParallel(XmlSuite.ParallelMode.TESTS);
        xmlSuite.setThreadCount(1);
        xmlSuite.setListeners(Collections.singletonList("framework.Listener"));

        // Add Test
        XmlTest xmlTest = new XmlTest(xmlSuite);
        xmlTest.setName("Regression");

        xmlTest.setPreserveOrder(true);
//        xmlTest.setParallel(XmlSuite.ParallelMode.CLASSES);
//        xmlTest.setThreadCount(1);

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
        System.out.println(xmlSuite.toXml());
        testNG.run();

    }



}
