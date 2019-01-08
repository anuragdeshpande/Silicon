package framework.listener;

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
        System.out.println(System.getProperty("jenkinsBuildNumber"));
        System.out.println(iTestResult.getName());
        System.out.println(iTestResult.getMethod().getMethodName());
        System.out.println(iTestResult.getStartMillis());
        System.out.println(iTestResult.getEndMillis());
        System.out.println(iTestResult.getStatus());
        System.out.println(iTestResult.getTestContext().getSuite().getName());
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println(System.getProperty("jenkinsBuildNumber"));
        System.out.println(iTestResult.getName());
        System.out.println(iTestResult.getMethod().getMethodName());
        System.out.println(iTestResult.getStartMillis());
        System.out.println(iTestResult.getEndMillis());
        System.out.println(iTestResult.getStatus());
        System.out.println(iTestResult.getThrowable().getLocalizedMessage());
        System.out.println(iTestResult.getTestContext().getSuite().getName());
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

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
