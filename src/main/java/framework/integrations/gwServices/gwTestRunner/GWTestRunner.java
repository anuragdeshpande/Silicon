package framework.integrations.gwServices.gwTestRunner;

import framework.applications.gw.gwTestRunner.IGWUnitTestRunner;
import framework.enums.Environment;
import framework.integrations.gwServices.gwTestRunner.com.waysysweb.*;
import framework.integrations.gwServices.gwTestRunner.generated.Testsuite;

import javax.xml.ws.BindingProvider;

public class GWTestRunner implements IGWUnitTestRunner {

    private RunTestPortType port;

    public GWTestRunResults startGWTestsOn(Environment environment, GWTestBean bean){
        return new GWTestRunResults(startTests(environment.getEnvironmentUrl(), bean, "su", "gw"));
    }

    public GWTestRunResults startGWTestsOnGWInstance(String instanceURL, GWTestBean bean, String username, String password){
        return new GWTestRunResults(startTests(instanceURL, bean, username, password));
    }

    public GWTestRunResults startGWTestsWithCustomUserOn(Environment environment, GWTestBean bean, String username, String password){
        return new GWTestRunResults(startTests(environment.getEnvironmentUrl(), bean, username, password));
    }



    private Testsuite startTests(String environmentURL, GWTestBean bean, String userName, String password){
        RunTest_Service runnerService = new RunTest_Service();
        this.port = runnerService.getRunTestSoap11Port();
        BindingProvider bindingProvider = (BindingProvider) this.port;
        bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
                environmentURL.replaceAll("GuidewireCenter.do", "")+"ws/unittestcase/RunTest/soap11");
        bindingProvider.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userName);
        bindingProvider.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);
        try {
            RunGeneratedPackageSuiteAsJUnitResponse.Return aReturn = this.port.runGeneratedPackageSuiteAsJUnit(bean.getSuiteName(), null, bean.getPackageString());
            return aReturn.getTestsuite();
        } catch (WsiAuthenticationException_Exception e) {
            throw new RuntimeException(e);
        }
    }


//    public static void main(String[] args) {
//        System.setProperty("com.sun.xml.ws.transport.http.client.HttpTransportPipe.dump", "true");
//        System.setProperty("com.sun.xml.internal.ws.transport.http.client.HttpTransportPipe.dump", "true");
//        System.setProperty("com.sun.xml.ws.transport.http.HttpAdapter.dump", "true");
//        System.setProperty("com.sun.xml.internal.ws.transporthttp.HttpAdapter.dump", "true");
//        System.setProperty("com.sun.xml.internal.ws.transport.http.HttpAdapter.dumpTreshold", "999999");

//    }

    @Override
    public void runTests() {
        GWTestRunner testRunner = new GWTestRunner();
        GWTestRunResults gwTestRunResults = testRunner.startGWTestsOnGWInstance("http://localhost:8080/cc/GuidewireCenter.do", GWTestBean.getInstance("UnitTests", "com.idfbins.cc.unitTests"), "su", "gw");
        gwTestRunResults.generateHTMLReport();
        gwTestRunResults.recordResultsInReportsDb();
    }
}
