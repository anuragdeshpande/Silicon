package framework.suiteManager;

import annotations.ClockMoveTest;
import io.github.classgraph.ClassInfoList;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class SuiteCreator {

    private boolean skipListeners;
    private boolean isClockMove;

    public SuiteCreator(){

    }

    public SuiteCreator(boolean isClockMove){
        this.isClockMove = isClockMove;
    }

    public XmlSuite createSuite(String suiteName, ClassInfoList testClasses, int threadCount) {
        XmlSuite xmlSuite = new XmlSuite();
        xmlSuite.setName(suiteName);
        xmlSuite.setVerbose(1);

        // setting suite level parameters (if any)
        if (!System.getProperty("SuiteLevelParams").isEmpty()) {
            HashMap<String, String> suiteLevelParameters = new HashMap<>();
            String suiteLevelParams = System.getProperty("SuiteLevelParams");
            for (String parameter : suiteLevelParams.split(",")) {
                if (parameter.contains(":")) {
                    String[] split = parameter.split(":");
                    suiteLevelParameters.put(split[0], split[1]);
                }
            }
            xmlSuite.setParameters(suiteLevelParameters);
        }
        if (threadCount > 1) {
            xmlSuite.setParallel(XmlSuite.ParallelMode.TESTS);
        }

        xmlSuite.setThreadCount(threadCount);

        if(!this.skipListeners){
            ArrayList<String> listeners = new ArrayList<>();
            listeners.add(System.getProperty("TestNGListener", "framework.Listener"));
            xmlSuite.setListeners(listeners);
        }


        // filtering clock move if marked in the constructor
        if(isClockMove && System.getProperty("UseClockMoveAnnotation", "false").equalsIgnoreCase("true")){
            // removing test classes that do NOT have clock move annotation
            testClasses.removeIf(classInfo -> !classInfo.hasAnnotation(ClockMoveTest.class.getCanonicalName()));
        } else if(!isClockMove && System.getProperty("UseClockMoveAnnotation", "false").equalsIgnoreCase("true")) {
            // filtering all classes that have clock move annotation
            testClasses.removeIf(classInfo -> classInfo.hasAnnotation(ClockMoveTest.class.getCanonicalName()));
        }

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

            // adding groups filter only if the build is marked to NOT use the ClockMove annotation
            if(isClockMove && System.getProperty("UseClockMoveAnnotation", "false").equalsIgnoreCase("false")){
                xmlTest.addIncludedGroup("ClockMove");
            } else if (!isClockMove && System.getProperty("UseClockMoveAnnotation", "false").equalsIgnoreCase("false")) {
                xmlTest.addExcludedGroup("ClockMove");
            }
        });

        System.out.println(xmlSuite.toXml());
        return xmlSuite;

    }

    public void setSkipListeners(boolean value){
        this.skipListeners = value;
    }
}
