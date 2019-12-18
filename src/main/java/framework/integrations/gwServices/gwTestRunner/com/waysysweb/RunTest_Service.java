
package framework.integrations.gwServices.gwTestRunner.com.waysysweb;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "RunTest", targetNamespace = "http://waysysweb.com", wsdlLocation = "file:/D:/Work/IntellijProjects/GW8Framework/src/main/resources/wsdl/GWTestRunner.wsdl")
public class RunTest_Service
    extends Service
{

    private final static URL RUNTEST_WSDL_LOCATION;
    private final static WebServiceException RUNTEST_EXCEPTION;
    private final static QName RUNTEST_QNAME = new QName("http://waysysweb.com", "RunTest");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("file:/D:/Work/IntellijProjects/GW8Framework/src/main/resources/wsdl/GWTestRunner.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        RUNTEST_WSDL_LOCATION = url;
        RUNTEST_EXCEPTION = e;
    }

    public RunTest_Service() {
        super(__getWsdlLocation(), RUNTEST_QNAME);
    }

    public RunTest_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), RUNTEST_QNAME, features);
    }

    public RunTest_Service(URL wsdlLocation) {
        super(wsdlLocation, RUNTEST_QNAME);
    }

    public RunTest_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, RUNTEST_QNAME, features);
    }

    public RunTest_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public RunTest_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns RunTestPortType
     */
    @WebEndpoint(name = "RunTestSoap11Port")
    public RunTestPortType getRunTestSoap11Port() {
        return super.getPort(new QName("http://waysysweb.com", "RunTestSoap11Port"), RunTestPortType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns RunTestPortType
     */
    @WebEndpoint(name = "RunTestSoap11Port")
    public RunTestPortType getRunTestSoap11Port(WebServiceFeature... features) {
        return super.getPort(new QName("http://waysysweb.com", "RunTestSoap11Port"), RunTestPortType.class, features);
    }

    private static URL __getWsdlLocation() {
        if (RUNTEST_EXCEPTION!= null) {
            throw RUNTEST_EXCEPTION;
        }
        return RUNTEST_WSDL_LOCATION;
    }

}
