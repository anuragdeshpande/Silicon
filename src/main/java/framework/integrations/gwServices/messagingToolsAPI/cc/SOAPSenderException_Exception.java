
package framework.integrations.gwServices.messagingToolsAPI.cc;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 3.3.4
 * 2021-04-09T10:41:08.297-06:00
 * Generated source version: 3.3.4
 */

@WebFault(name = "SOAPSenderException", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MessagingToolsAPI")
public class SOAPSenderException_Exception extends Exception {

    private framework.integrations.gwServices.messagingToolsAPI.cc.SOAPSenderException soapSenderException;

    public SOAPSenderException_Exception() {
        super();
    }

    public SOAPSenderException_Exception(String message) {
        super(message);
    }

    public SOAPSenderException_Exception(String message, java.lang.Throwable cause) {
        super(message, cause);
    }

    public SOAPSenderException_Exception(String message, framework.integrations.gwServices.messagingToolsAPI.cc.SOAPSenderException soapSenderException) {
        super(message);
        this.soapSenderException = soapSenderException;
    }

    public SOAPSenderException_Exception(String message, framework.integrations.gwServices.messagingToolsAPI.cc.SOAPSenderException soapSenderException, java.lang.Throwable cause) {
        super(message, cause);
        this.soapSenderException = soapSenderException;
    }

    public framework.integrations.gwServices.messagingToolsAPI.cc.SOAPSenderException getFaultInfo() {
        return this.soapSenderException;
    }
}
