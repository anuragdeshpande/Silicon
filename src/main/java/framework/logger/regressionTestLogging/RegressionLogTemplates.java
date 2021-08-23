package framework.logger.regressionTestLogging;

import annotations.AutomatedTest;
import com.aventstack.extentreports.Status;
import com.google.common.base.Joiner;
import framework.reports.models.TestDetailsDTO;
import org.apache.commons.lang3.ArrayUtils;
import org.testng.ITestResult;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RegressionLogTemplates {

    public static String getLogTemplateForTestEndPass(final ITestResult iTestResult, final String... additionalTags) {
        return _getBaseTemplateTestEnd(iTestResult, Status.PASS.getName(), additionalTags);
    }

    public static String getLogTemplateForTestEndFailed(final ITestResult iTestResult, String... additionalTags) {
        final Throwable throwable = iTestResult.getThrowable();
        if (throwable != null) {
            final String exceptionType = throwable.getClass().getSimpleName();
            final String exceptionMessage = throwable.getMessage();
            final ArrayList<String> strings = new ArrayList<>(Arrays.asList(additionalTags));
            strings.addAll(Arrays.asList("ExceptionType=" + exceptionType, "ExceptionMessage=" + exceptionMessage));
            additionalTags = strings.toArray(new String[0]);
        }
        return _getBaseTemplateTestEnd(iTestResult, Status.FAIL.getName(), additionalTags);
    }

    public static String getLogTemplateForTestEndFatal(final ITestResult iTestResult, final String... additionalTags) {
        return _getBaseTemplateTestEnd(iTestResult, "FATAL", additionalTags);
    }

    public static String getLogTemplateForTestEndSkipped(final ITestResult iTestResult, final String... additionalTags) {
        return _getBaseTemplateTestEnd(iTestResult, Status.SKIP.getName(), additionalTags);
    }

    private static String _getBaseTemplateTestEnd(final ITestResult iTestResult, final String status, final String... additionalTags) {
        String logTemplate = _getBaseTestLifeCycleTemplate(buildTestDetailsDTO(iTestResult));
        final String[] themes = iTestResult.getMethod().getConstructorOrMethod().getMethod().getAnnotationsByType(AutomatedTest.class)[0].Themes();
        final long timeTakenInSeconds = (new Timestamp(iTestResult.getEndMillis()).getTime() - new Timestamp(iTestResult.getStartMillis()).getTime()) / 1000;
        if (ArrayUtils.isNotEmpty(themes)) {
            final List<String> collect = Arrays.stream(themes).map(s -> s.replaceAll(" ", "_")).collect(Collectors.toList());
            logTemplate = logTemplate + " Themes=" + Joiner.on(",").skipNulls().join(collect);
        }

        logTemplate = logTemplate + " TimeTaken=" + timeTakenInSeconds + " Seconds has Ended. TestStatus=" + status;

        if (ArrayUtils.isNotEmpty(additionalTags)) {
            logTemplate = logTemplate + Joiner.on(" ").skipNulls().join(additionalTags);
        }

        return logTemplate;
    }

    private static String _getBaseTestLifeCycleTemplate(final TestDetailsDTO testDetails) {
        return "TestName=" + testDetails.getTestName() + " ClassName=" + testDetails.getClassName() + " PackageName=" + testDetails.getPackageName();
    }

    public static TestDetailsDTO buildTestDetailsDTO(final ITestResult iTestResult) {
        final TestDetailsDTO dto = new TestDetailsDTO();
        dto.setTestName(iTestResult.getMethod().getConstructorOrMethod().getMethod().getName());
        dto.setClassName(iTestResult.getMethod().getConstructorOrMethod().getDeclaringClass().getSimpleName());
        dto.setPackageName(iTestResult.getMethod().getConstructorOrMethod().getDeclaringClass().getPackage().getName());
        return dto;
    }
}
