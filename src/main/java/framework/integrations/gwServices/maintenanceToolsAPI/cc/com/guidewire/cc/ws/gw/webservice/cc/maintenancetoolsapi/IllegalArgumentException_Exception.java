
package framework.integrations.gwServices.maintenanceToolsAPI.cc.com.guidewire.cc.ws.gw.webservice.cc.maintenancetoolsapi;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "IllegalArgumentException", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI")
public class IllegalArgumentException_Exception
    extends Exception
{

    /**
	 * 
	 */
	private static final long serialVersionUID = -584334217819805026L;
	/**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private IllegalArgumentException faultInfo;

    /**
     * 
     * @param message
     * @param faultInfo
     */
    public IllegalArgumentException_Exception(String message, IllegalArgumentException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param message
     * @param faultInfo
     * @param cause
     */
    public IllegalArgumentException_Exception(String message, IllegalArgumentException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: com.guidewire.cc.ws.gw.webservice.cc.maintenancetoolsapi.IllegalArgumentException
     */
    public IllegalArgumentException getFaultInfo() {
        return faultInfo;
    }

}
