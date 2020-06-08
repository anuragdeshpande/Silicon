
package framework.integrations.gwServices.maintenanceToolsAPI.ab.com.guidewire.ab.ws.gw.webservice.ab.ab801.maintenancetoolsapi;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "WebServiceException", targetNamespace = "http://guidewire.com/ab/ws/gw/webservice/ab/ab801/MaintenanceToolsAPI")
public class WebServiceException_Exception
    extends Exception
{

    /**
	 * 
	 */
	private static final long serialVersionUID = -2528690734619423159L;
	/**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private WebServiceException faultInfo;

    /**
     * 
     * @param message
     * @param faultInfo
     */
    public WebServiceException_Exception(String message, WebServiceException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param message
     * @param faultInfo
     * @param cause
     */
    public WebServiceException_Exception(String message, WebServiceException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: com.guidewire.ab.ws.gw.webservice.ab.ab801.maintenancetoolsapi.WebServiceException
     */
    public WebServiceException getFaultInfo() {
        return faultInfo;
    }

}
