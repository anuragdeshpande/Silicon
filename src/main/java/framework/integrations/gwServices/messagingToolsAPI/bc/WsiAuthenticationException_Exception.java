
package framework.integrations.gwServices.messagingToolsAPI.bc;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 3.3.4
 * 2021-06-14T14:02:48.518-06:00
 * Generated source version: 3.3.4
 */

@WebFault(name = "WsiAuthenticationException", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI")
public class WsiAuthenticationException_Exception extends Exception {

    private framework.integrations.gwServices.messagingToolsAPI.bc.WsiAuthenticationException wsiAuthenticationException;

    public WsiAuthenticationException_Exception() {
        super();
    }

    public WsiAuthenticationException_Exception(String message) {
        super(message);
    }

    public WsiAuthenticationException_Exception(String message, java.lang.Throwable cause) {
        super(message, cause);
    }

    public WsiAuthenticationException_Exception(String message, framework.integrations.gwServices.messagingToolsAPI.bc.WsiAuthenticationException wsiAuthenticationException) {
        super(message);
        this.wsiAuthenticationException = wsiAuthenticationException;
    }

    public WsiAuthenticationException_Exception(String message, framework.integrations.gwServices.messagingToolsAPI.bc.WsiAuthenticationException wsiAuthenticationException, java.lang.Throwable cause) {
        super(message, cause);
        this.wsiAuthenticationException = wsiAuthenticationException;
    }

    public framework.integrations.gwServices.messagingToolsAPI.bc.WsiAuthenticationException getFaultInfo() {
        return this.wsiAuthenticationException;
    }
}
