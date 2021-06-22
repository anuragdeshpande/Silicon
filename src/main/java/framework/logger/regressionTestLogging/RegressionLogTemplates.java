package framework.logger.regressionTestLogging;

import annotations.AutomatedTest;
import com.aventstack.extentreports.Status;
import com.google.common.base.Joiner;
import framework.reports.models.TestDetailsDTO;
import org.apache.commons.lang3.ArrayUtils;
import org.testng.ITestResult;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RegressionLogTemplates {

    public static String getLogTemplateForTestEndPass(ITestResult iTestResult, String... additionalTags){
        return _getBaseTemplateTestEnd(iTestResult, Status.PASS.getName(), additionalTags);
    }

    public static String getLogTemplateForTestEndFailed(ITestResult iTestResult, String... additionalTags){
        return _getBaseTemplateTestEnd(iTestResult, Status.FAIL.getName(), additionalTags);
    }

    public static String getLogTemplateForTestEndFatal(ITestResult iTestResult, String... additionalTags){
        return _getBaseTemplateTestEnd(iTestResult, "FATAL", additionalTags);
    }

    public static String getLogTemplateForTestEndSkipped(ITestResult iTestResult, String... additionalTags){
        return _getBaseTemplateTestEnd(iTestResult, Status.SKIP.getName(), additionalTags);
    }

    private static String _getBaseTemplateTestEnd(ITestResult iTestResult, String status, String... additionalTags){
        String logTemplate = _getBaseTestLifeCycleTemplate(buildTestDetailsDTO(iTestResult));
        String[] themes = iTestResult.getMethod().getConstructorOrMethod().getMethod().getAnnotationsByType(AutomatedTest.class)[0].Themes();
        long timeTakenInSeconds = (new Timestamp(iTestResult.getEndMillis()).getTime() - new Timestamp(iTestResult.getStartMillis()).getTime())/1000;
        if(ArrayUtils.isNotEmpty(themes)){
            List<String> collect = Arrays.stream(themes).map(s -> s.replaceAll(" ", "_")).collect(Collectors.toList());
            logTemplate = logTemplate+" Themes="+ Joiner.on(",").skipNulls().join(collect);
        }

        logTemplate = logTemplate+" TimeTaken="+timeTakenInSeconds+" Seconds has Ended. TestStatus="+status;

        if(ArrayUtils.isNotEmpty(additionalTags)){
            logTemplate = logTemplate + Joiner.on(" ").skipNulls().join(additionalTags);
        }

        return logTemplate;
    }

    private static String _getBaseTestLifeCycleTemplate(TestDetailsDTO testDetails){
        return "TestName="+testDetails.getTestName()+" ClassName="+testDetails.getClassName()+" PackageName="+testDetails.getPackageName();
    }

    public static TestDetailsDTO buildTestDetailsDTO(ITestResult iTestResult) {
        TestDetailsDTO dto = new TestDetailsDTO();
        dto.setTestName(iTestResult.getMethod().getConstructorOrMethod().getMethod().getName());
        dto.setClassName(iTestResult.getMethod().getConstructorOrMethod().getDeclaringClass().getSimpleName());
        dto.setPackageName(iTestResult.getMethod().getConstructorOrMethod().getDeclaringClass().getPackage().getName());
        return dto;
    }
}
