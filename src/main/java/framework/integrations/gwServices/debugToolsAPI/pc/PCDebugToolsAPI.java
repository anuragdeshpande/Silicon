package framework.integrations.gwServices.debugToolsAPI.pc;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * This class was generated by Apache CXF 3.3.4
 * 2021-06-01T17:53:30.681-06:00
 * Generated source version: 3.3.4
 *
 */
@WebServiceClient(name = "PCDebugToolsAPI",
                  wsdlLocation = "file:/D:/Work/IntellijProjects/Silicon/src/main/java/framework/integrations/gwServices/debugToolsAPI/pc/PCDebugTools.wsdl",
                  targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/pcdebugtools/PCDebugToolsAPI")
public class PCDebugToolsAPI extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/pcdebugtools/PCDebugToolsAPI", "PCDebugToolsAPI");
    public final static QName PCDebugToolsAPISoap12Port = new QName("http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/pcdebugtools/PCDebugToolsAPI", "PCDebugToolsAPISoap12Port");
    public final static QName PCDebugToolsAPISoap11Port = new QName("http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/pcdebugtools/PCDebugToolsAPI", "PCDebugToolsAPISoap11Port");
    static {
        URL url = null;
        try {
            url = new URL("file:/D:/Work/IntellijProjects/Silicon/src/main/java/framework/integrations/gwServices/debugToolsAPI/pc/PCDebugTools.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(PCDebugToolsAPI.class.getName())
                .log(java.util.logging.Level.INFO,
                     "Can not initialize the default wsdl from {0}", "file:/D:/Work/IntellijProjects/Silicon/src/main/java/framework/integrations/gwServices/debugToolsAPI/pc/PCDebugTools.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public PCDebugToolsAPI(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public PCDebugToolsAPI(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public PCDebugToolsAPI() {
        super(WSDL_LOCATION, SERVICE);
    }

    public PCDebugToolsAPI(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public PCDebugToolsAPI(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public PCDebugToolsAPI(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }




    /**
     *
     * @return
     *     returns PCDebugToolsAPIPortType
     */
    @WebEndpoint(name = "PCDebugToolsAPISoap12Port")
    public PCDebugToolsAPIPortType getPCDebugToolsAPISoap12Port() {
        return super.getPort(PCDebugToolsAPISoap12Port, PCDebugToolsAPIPortType.class);
    }

    /**
     *
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns PCDebugToolsAPIPortType
     */
    @WebEndpoint(name = "PCDebugToolsAPISoap12Port")
    public PCDebugToolsAPIPortType getPCDebugToolsAPISoap12Port(WebServiceFeature... features) {
        return super.getPort(PCDebugToolsAPISoap12Port, PCDebugToolsAPIPortType.class, features);
    }


    /**
     *
     * @return
     *     returns PCDebugToolsAPIPortType
     */
    @WebEndpoint(name = "PCDebugToolsAPISoap11Port")
    public PCDebugToolsAPIPortType getPCDebugToolsAPISoap11Port() {
        return super.getPort(PCDebugToolsAPISoap11Port, PCDebugToolsAPIPortType.class);
    }

    /**
     *
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns PCDebugToolsAPIPortType
     */
    @WebEndpoint(name = "PCDebugToolsAPISoap11Port")
    public PCDebugToolsAPIPortType getPCDebugToolsAPISoap11Port(WebServiceFeature... features) {
        return super.getPort(PCDebugToolsAPISoap11Port, PCDebugToolsAPIPortType.class, features);
    }

}
