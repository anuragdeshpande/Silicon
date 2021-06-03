package framework.integrations.gwServices.debugToolsAPI.ab;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * This class was generated by Apache CXF 3.3.4
 * 2021-06-03T13:44:58.545-06:00
 * Generated source version: 3.3.4
 *
 */
@WebServiceClient(name = "ABDebugToolsAPI",
                  wsdlLocation = "file:/D:/Work/IntellijProjects/Silicon/src/main/java/framework/integrations/gwServices/debugToolsAPI/ab/ABDebugToolsAPI.wsdl",
                  targetNamespace = "http://guidewire.com/ab/ws/gw/webservice/ab/ab1000/abdebugtoolsapi/ABDebugToolsAPI")
public class ABDebugToolsAPI extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://guidewire.com/ab/ws/gw/webservice/ab/ab1000/abdebugtoolsapi/ABDebugToolsAPI", "ABDebugToolsAPI");
    public final static QName ABDebugToolsAPISoap12Port = new QName("http://guidewire.com/ab/ws/gw/webservice/ab/ab1000/abdebugtoolsapi/ABDebugToolsAPI", "ABDebugToolsAPISoap12Port");
    public final static QName ABDebugToolsAPISoap11Port = new QName("http://guidewire.com/ab/ws/gw/webservice/ab/ab1000/abdebugtoolsapi/ABDebugToolsAPI", "ABDebugToolsAPISoap11Port");
    static {
        URL url = null;
        try {
            url = new URL("file:/D:/Work/IntellijProjects/Silicon/src/main/java/framework/integrations/gwServices/debugToolsAPI/ab/ABDebugToolsAPI.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(ABDebugToolsAPI.class.getName())
                .log(java.util.logging.Level.INFO,
                     "Can not initialize the default wsdl from {0}", "file:/D:/Work/IntellijProjects/Silicon/src/main/java/framework/integrations/gwServices/debugToolsAPI/ab/ABDebugToolsAPI.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public ABDebugToolsAPI(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public ABDebugToolsAPI(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ABDebugToolsAPI() {
        super(WSDL_LOCATION, SERVICE);
    }

    public ABDebugToolsAPI(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public ABDebugToolsAPI(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public ABDebugToolsAPI(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }




    /**
     *
     * @return
     *     returns ABDebugToolsAPIPortType
     */
    @WebEndpoint(name = "ABDebugToolsAPISoap12Port")
    public ABDebugToolsAPIPortType getABDebugToolsAPISoap12Port() {
        return super.getPort(ABDebugToolsAPISoap12Port, ABDebugToolsAPIPortType.class);
    }

    /**
     *
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ABDebugToolsAPIPortType
     */
    @WebEndpoint(name = "ABDebugToolsAPISoap12Port")
    public ABDebugToolsAPIPortType getABDebugToolsAPISoap12Port(WebServiceFeature... features) {
        return super.getPort(ABDebugToolsAPISoap12Port, ABDebugToolsAPIPortType.class, features);
    }


    /**
     *
     * @return
     *     returns ABDebugToolsAPIPortType
     */
    @WebEndpoint(name = "ABDebugToolsAPISoap11Port")
    public ABDebugToolsAPIPortType getABDebugToolsAPISoap11Port() {
        return super.getPort(ABDebugToolsAPISoap11Port, ABDebugToolsAPIPortType.class);
    }

    /**
     *
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ABDebugToolsAPIPortType
     */
    @WebEndpoint(name = "ABDebugToolsAPISoap11Port")
    public ABDebugToolsAPIPortType getABDebugToolsAPISoap11Port(WebServiceFeature... features) {
        return super.getPort(ABDebugToolsAPISoap11Port, ABDebugToolsAPIPortType.class, features);
    }

}
