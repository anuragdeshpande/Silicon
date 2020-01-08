package framework.integrations.gwServices.gwTestRunner;

import framework.environmentResolution.Environment;
import framework.integrations.gwServices.gwTestRunner.com.waysysweb.*;
import framework.integrations.gwServices.gwTestRunner.generated.Testsuite;

import javax.xml.ws.BindingProvider;
import java.net.MalformedURLException;
import java.net.URL;

public class GWTestRunner {

    private RunTestPortType port;

    public GWTestRunResults startGWTestsOn(Environment environment, GWTestBean bean) {
        return new GWTestRunResults(startTests(environment.getEnvironmentUrl(), bean, "su", "gw"));
    }

    public GWTestRunResults startGWTestsOnGWInstance(String instanceURL, GWTestBean bean, String username, String password) {
        return new GWTestRunResults(startTests(instanceURL, bean, username, password));
    }

    public GWTestRunResults startGWTestsWithCustomUserOn(Environment environment, GWTestBean bean, String username, String password) {
        return new GWTestRunResults(startTests(environment.getEnvironmentUrl(), bean, username, password));
    }


    private Testsuite startTests(String environmentURL, GWTestBean bean, String userName, String password) {
        try {
            RunTest_Service runnerService = new RunTest_Service(new URL(environmentURL + "ws/unittestcase/RunTest?wsdl"));
            this.port = runnerService.getRunTestSoap11Port();
            BindingProvider bindingProvider = (BindingProvider) this.port;
            bindingProvider.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userName);
            bindingProvider.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);

            RunGeneratedPackageSuiteAsJUnitResponse.Return aReturn = this.port.runGeneratedPackageSuiteAsJUnit(bean.getSuiteName(), null, bean.getPackageString());
            return aReturn.getTestsuite();
        } catch (WsiAuthenticationException_Exception | MalformedURLException e) {
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

    private void runTests() {
        GWTestRunner testRunner = new GWTestRunner();
        GWTestRunResults gwTestRunResults = testRunner.startGWTestsOnGWInstance("http://localhost:8080/cc/GuidewireCenter.do", GWTestBean.getInstance("UnitTests", "com.idfbins.cc.unitTests"), "su", "gw");
        gwTestRunResults.generateHTMLReport();
        gwTestRunResults.recordResultsInReportsDb();
    }
}
