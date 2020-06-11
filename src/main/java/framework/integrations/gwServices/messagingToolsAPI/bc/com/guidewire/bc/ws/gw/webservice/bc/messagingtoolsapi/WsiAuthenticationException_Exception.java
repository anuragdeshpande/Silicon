
package framework.integrations.gwServices.messagingToolsAPI.bc.com.guidewire.bc.ws.gw.webservice.bc.messagingtoolsapi;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "WsiAuthenticationException", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI")
public class WsiAuthenticationException_Exception
    extends Exception
{

    /**
	 * 
	 */
	private static final long serialVersionUID = 7235343481746463792L;
	/**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private WsiAuthenticationException faultInfo;

    /**
     * 
     * @param message
     * @param faultInfo
     */
    public WsiAuthenticationException_Exception(String message, WsiAuthenticationException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param message
     * @param faultInfo
     * @param cause
     */
    public WsiAuthenticationException_Exception(String message, WsiAuthenticationException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: framework.integrations.gwServices.messagingToolsAPI.bc.com.guidewire.bc.ws.gw.webservice.bc.messagingtoolsapi.WsiAuthenticationException
     */
    public WsiAuthenticationException getFaultInfo() {
        return faultInfo;
    }

}
