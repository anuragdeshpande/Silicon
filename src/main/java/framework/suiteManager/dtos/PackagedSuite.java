package framework.suiteManager.dtos;

import java.util.ArrayList;

public class PackagedSuite {
    private final ArrayList<String> testNames;
    private long totalRuntime;
    private int iterationCount;
    private final long numberOfThreads;
    private long currentTestCount;
    private final String packageIdentifier;

    public PackagedSuite(int numberOfThreads, String packageIdentifier) {
        this.numberOfThreads = numberOfThreads;
        this.testNames = new ArrayList<>();
        this.totalRuntime = 0;
        this.currentTestCount = 0;
        this.iterationCount = 0;
        this.packageIdentifier = packageIdentifier;
    }

    public long addTest(String className, long runtime){
        this.testNames.add(className);
        currentTestCount++;
        this.totalRuntime+=runtime;
        if(currentTestCount == (numberOfThreads-1)){
            currentTestCount = 0;
            iterationCount++;
        }

        return this.totalRuntime;
    }

    public int getIterationCount(){
        return this.iterationCount;
    }

    public long getTotalRuntime() {
        return totalRuntime;
    }

    public String getPackageIdentifier(){
        return this.packageIdentifier;
    }

    public long getTotalTests(){
        return this.testNames.size();
    }

    public ArrayList<String> getTestNames() {
        return testNames;
    }

    public static ArrayList<PackagedSuite> initializePackages(int numberOfThreads, String distributeAgainst){
        String[] distributionList = distributeAgainst.split(",");
        ArrayList<PackagedSuite> packages = new ArrayList<>();
        for (String runnerName : distributionList) {
            packages.add(new PackagedSuite(numberOfThreads, runnerName));
        }

        return packages;
    }

    @Override
    public String toString() {
        return "Name: "+this.getPackageIdentifier()+" Tests: "+this.getTotalTests()+" Round: "+this.getIterationCount()+" Runtime: "+this.getTotalRuntime();
    }
}
