
package framework.integrations.gwServices.messagingToolsAPI.ab.com.guidewire.ab.ws.gw.webservice.ab.ab801.messagingtoolsapi;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "SOAPException", targetNamespace = "http://guidewire.com/ab/ws/gw/webservice/ab/ab801/MessagingToolsAPI")
public class SOAPException_Exception
    extends Exception
{

    /**
	 * 
	 */
	private static final long serialVersionUID = -4028359935065363937L;
	/**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private SOAPException faultInfo;

    /**
     * 
     * @param message
     * @param faultInfo
     */
    public SOAPException_Exception(String message, SOAPException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param message
     * @param faultInfo
     * @param cause
     */
    public SOAPException_Exception(String message, SOAPException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: framework.integrations.gwServices.messagingToolsAPI.ab.com.guidewire.ab.ws.gw.webservice.ab.ab801.messagingtoolsapi.SOAPException
     */
    public SOAPException getFaultInfo() {
        return faultInfo;
    }

}