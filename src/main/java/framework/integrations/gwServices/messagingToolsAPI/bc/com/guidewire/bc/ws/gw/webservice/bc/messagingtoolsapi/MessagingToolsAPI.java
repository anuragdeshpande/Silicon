
package framework.integrations.gwServices.messagingToolsAPI.bc.com.guidewire.bc.ws.gw.webservice.bc.messagingtoolsapi;

import javax.xml.namespace.QName;
import javax.xml.ws.*;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "MessagingToolsAPI", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI", wsdlLocation = "http://bc8uat/bc/ws/gw/webservice/bc/MessagingToolsAPI?WSDL")
public class MessagingToolsAPI
    extends Service
{

    private final static URL MESSAGINGTOOLSAPI_WSDL_LOCATION;
    private final static WebServiceException MESSAGINGTOOLSAPI_EXCEPTION;
    private final static QName MESSAGINGTOOLSAPI_QNAME = new QName("http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI", "MessagingToolsAPI");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://bc8uat/bc/ws/gw/webservice/bc/MessagingToolsAPI?WSDL");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        MESSAGINGTOOLSAPI_WSDL_LOCATION = url;
        MESSAGINGTOOLSAPI_EXCEPTION = e;
    }

    public MessagingToolsAPI() {
        super(__getWsdlLocation(), MESSAGINGTOOLSAPI_QNAME);
    }

    public MessagingToolsAPI(WebServiceFeature... features) {
        super(__getWsdlLocation(), MESSAGINGTOOLSAPI_QNAME, features);
    }

    public MessagingToolsAPI(URL wsdlLocation) {
        super(wsdlLocation, MESSAGINGTOOLSAPI_QNAME);
    }

    public MessagingToolsAPI(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, MESSAGINGTOOLSAPI_QNAME, features);
    }

    public MessagingToolsAPI(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public MessagingToolsAPI(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns MessagingToolsAPIPortType
     */
    @WebEndpoint(name = "MessagingToolsAPISoap11Port")
    public MessagingToolsAPIPortType getMessagingToolsAPISoap11Port() {
        return super.getPort(new QName("http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI", "MessagingToolsAPISoap11Port"), MessagingToolsAPIPortType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns MessagingToolsAPIPortType
     */
    @WebEndpoint(name = "MessagingToolsAPISoap11Port")
    public MessagingToolsAPIPortType getMessagingToolsAPISoap11Port(WebServiceFeature... features) {
        return super.getPort(new QName("http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI", "MessagingToolsAPISoap11Port"), MessagingToolsAPIPortType.class, features);
    }

    private static URL __getWsdlLocation() {
        if (MESSAGINGTOOLSAPI_EXCEPTION!= null) {
            throw MESSAGINGTOOLSAPI_EXCEPTION;
        }
        return MESSAGINGTOOLSAPI_WSDL_LOCATION;
    }

}