
package framework.integrations.gwServices.messagingToolsAPI.pc;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 3.3.4
 * 2021-06-14T13:59:44.079-06:00
 * Generated source version: 3.3.4
 */

@WebFault(name = "SOAPSenderException", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MessagingToolsAPI")
public class SOAPSenderException_Exception extends Exception {

    private framework.integrations.gwServices.messagingToolsAPI.pc.SOAPSenderException soapSenderException;

    public SOAPSenderException_Exception() {
        super();
    }

    public SOAPSenderException_Exception(String message) {
        super(message);
    }

    public SOAPSenderException_Exception(String message, java.lang.Throwable cause) {
        super(message, cause);
    }

    public SOAPSenderException_Exception(String message, framework.integrations.gwServices.messagingToolsAPI.pc.SOAPSenderException soapSenderException) {
        super(message);
        this.soapSenderException = soapSenderException;
    }

    public SOAPSenderException_Exception(String message, framework.integrations.gwServices.messagingToolsAPI.pc.SOAPSenderException soapSenderException, java.lang.Throwable cause) {
        super(message, cause);
        this.soapSenderException = soapSenderException;
    }

    public framework.integrations.gwServices.messagingToolsAPI.pc.SOAPSenderException getFaultInfo() {
        return this.soapSenderException;
    }
}
