
package framework.integrations.gwServices.debugToolsAPI.ab;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "WsiAuthenticationException", targetNamespace = "http://guidewire.com/ab/ws/gw/webservice/ab/ab801/abdebugtoolsapi/ABDebugToolsAPI")
public class WsiAuthenticationException_Exception
    extends Exception
{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
     *     returns fault bean: gwclockservice.abdebugtoolsapi.WsiAuthenticationException
     */
    public WsiAuthenticationException getFaultInfo() {
        return faultInfo;
    }

}
