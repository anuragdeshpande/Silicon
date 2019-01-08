package framework.listener;

import framework.database.ConnectionManager;
import framework.webdriver.DriverManager;
import org.testng.*;

public class Listener implements ISuiteListener, ITestListener {

    @Override
    public void onStart(ISuite iSuite) {

    }

    @Override
    public void onFinish(ISuite iSuite) {
        System.out.println(iSuite.getName());
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println(iTestResult.getTestClass());
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        DriverManager.recordTestResult(iTestResult, "Success");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        DriverManager.recordTestResult(iTestResult, "Failure");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        DriverManager.recordTestResult(iTestResult, "Skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        iTestContext.getSuite().getName();
    }
}
