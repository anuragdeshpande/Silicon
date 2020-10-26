
package framework.integrations.gwServices.maintenanceToolsAPI.bc.com.guidewire.bc.ws.gw.webservice.bc.maintenancetoolsapi;

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
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "MaintenanceToolsAPI", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MaintenanceToolsAPI", wsdlLocation = "http://bc8dev/bc/ws/gw/webservice/bc/MaintenanceToolsAPI?WSDL")
public class MaintenanceToolsAPI
    extends Service
{

    private final static URL MAINTENANCETOOLSAPI_WSDL_LOCATION;
    private final static WebServiceException MAINTENANCETOOLSAPI_EXCEPTION;
    private final static QName MAINTENANCETOOLSAPI_QNAME = new QName("http://guidewire.com/bc/ws/gw/webservice/bc/MaintenanceToolsAPI", "MaintenanceToolsAPI");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://bc8dev/bc/ws/gw/webservice/bc/MaintenanceToolsAPI?WSDL");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        MAINTENANCETOOLSAPI_WSDL_LOCATION = url;
        MAINTENANCETOOLSAPI_EXCEPTION = e;
    }

    public MaintenanceToolsAPI() {
        super(__getWsdlLocation(), MAINTENANCETOOLSAPI_QNAME);
    }

    public MaintenanceToolsAPI(WebServiceFeature... features) {
        super(__getWsdlLocation(), MAINTENANCETOOLSAPI_QNAME, features);
    }

    public MaintenanceToolsAPI(URL wsdlLocation) {
        super(wsdlLocation, MAINTENANCETOOLSAPI_QNAME);
    }

    public MaintenanceToolsAPI(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, MAINTENANCETOOLSAPI_QNAME, features);
    }

    public MaintenanceToolsAPI(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public MaintenanceToolsAPI(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns MaintenanceToolsAPIPortType
     */
    @WebEndpoint(name = "MaintenanceToolsAPISoap11Port")
    public MaintenanceToolsAPIPortType getMaintenanceToolsAPISoap11Port() {
        return super.getPort(new QName("http://guidewire.com/bc/ws/gw/webservice/bc/MaintenanceToolsAPI", "MaintenanceToolsAPISoap11Port"), MaintenanceToolsAPIPortType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns MaintenanceToolsAPIPortType
     */
    @WebEndpoint(name = "MaintenanceToolsAPISoap11Port")
    public MaintenanceToolsAPIPortType getMaintenanceToolsAPISoap11Port(WebServiceFeature... features) {
        return super.getPort(new QName("http://guidewire.com/bc/ws/gw/webservice/bc/MaintenanceToolsAPI", "MaintenanceToolsAPISoap11Port"), MaintenanceToolsAPIPortType.class, features);
    }

    private static URL __getWsdlLocation() {
        if (MAINTENANCETOOLSAPI_EXCEPTION!= null) {
            throw MAINTENANCETOOLSAPI_EXCEPTION;
        }
        return MAINTENANCETOOLSAPI_WSDL_LOCATION;
    }

}
