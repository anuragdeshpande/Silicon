
package framework.integrations.gwServices.messagingToolsAPI.bc;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 3.3.4
 * 2021-06-14T14:02:48.525-06:00
 * Generated source version: 3.3.4
 */

@WebFault(name = "IllegalArgumentException", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI")
public class IllegalArgumentException_Exception extends Exception {

    private framework.integrations.gwServices.messagingToolsAPI.bc.IllegalArgumentException illegalArgumentException;

    public IllegalArgumentException_Exception() {
        super();
    }

    public IllegalArgumentException_Exception(String message) {
        super(message);
    }

    public IllegalArgumentException_Exception(String message, java.lang.Throwable cause) {
        super(message, cause);
    }

    public IllegalArgumentException_Exception(String message, framework.integrations.gwServices.messagingToolsAPI.bc.IllegalArgumentException illegalArgumentException) {
        super(message);
        this.illegalArgumentException = illegalArgumentException;
    }

    public IllegalArgumentException_Exception(String message, framework.integrations.gwServices.messagingToolsAPI.bc.IllegalArgumentException illegalArgumentException, java.lang.Throwable cause) {
        super(message, cause);
        this.illegalArgumentException = illegalArgumentException;
    }

    public framework.integrations.gwServices.messagingToolsAPI.bc.IllegalArgumentException getFaultInfo() {
        return this.illegalArgumentException;
    }
}
