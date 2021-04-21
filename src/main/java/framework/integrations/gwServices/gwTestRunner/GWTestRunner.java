package framework.integrations.gwServices.gwTestRunner;

import framework.environmentResolution.Environment;
import framework.integrations.gwServices.gwTestRunner.com.waysysweb.RunTestAsJUnitResponse;
import framework.integrations.gwServices.gwTestRunner.com.waysysweb.RunTestPortType;
import framework.integrations.gwServices.gwTestRunner.generated.Testsuite;
import org.apache.commons.io.IOUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;

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
            URLConnection con = new URL(environmentURL+"service/rununittests?username="+userName+"&password="+password+"&testpackage="+bean.getPackageString()).openConnection();
            InputStream inputStream = con.getInputStream();
            String encoding = con.getContentEncoding();
            encoding = encoding == null ? "UTF-8" : encoding;
            String body = IOUtils.toString(inputStream, encoding);
            JAXBContext jaxbContext = JAXBContext.newInstance(RunTestAsJUnitResponse.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Testsuite testsuite = (Testsuite) unmarshaller.unmarshal(new StringReader(body));
            testsuite.setName(bean.getSuiteName());
            return testsuite;
        } catch (IOException | JAXBException e) {
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

//    public static void main(String[] args) {
//        GWTestRunner testRunner = new GWTestRunner();
//        GWTestRunResults gwTestRunResults = testRunner.startGWTestsOnGWInstance("http://localhost:8080/cc/", GWTestBean.getInstance("UnitTests", "com.idfbins.cc.unitTests"), "su", "gw");
//        gwTestRunResults.generateHTMLReport();
//    }

    private void runTests() {

    }
}
