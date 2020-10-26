
package framework.integrations.gwServices.messagingToolsAPI.pc.com.guidewire.pc.ws.gw.webservice.pc.pc800.messagingtoolsapi;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "IllegalArgumentException", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI")
public class IllegalArgumentException_Exception
    extends Exception
{

    /**
	 * 
	 */
	private static final long serialVersionUID = 8718347333650027144L;
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
     *     returns fault bean: framework.integrations.gwServices.messagingToolsAPI.pc.com.guidewire.pc.ws.gw.webservice.pc.pc800.messagingtoolsapi.IllegalArgumentException
     */
    public IllegalArgumentException getFaultInfo() {
        return faultInfo;
    }

}
