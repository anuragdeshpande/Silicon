
package framework.integrations.gwServices.maintenanceToolsAPI.pc.com.guidewire.pc.ws.gw.webservice.pc.pc800.maintenancetoolsapi;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "IllegalArgumentException", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MaintenanceToolsAPI")
public class IllegalArgumentException_Exception
    extends Exception
{

    /**
	 * 
	 */
	private static final long serialVersionUID = -7591745295459921636L;
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
     *     returns fault bean: com.guidewire.pc.ws.gw.webservice.pc.pc800.maintenancetoolsapi.IllegalArgumentException
     */
    public IllegalArgumentException getFaultInfo() {
        return faultInfo;
    }

}