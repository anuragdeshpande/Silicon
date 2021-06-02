
package framework.integrations.gwServices.debugToolsAPI.pc;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 3.3.4
 * 2021-06-01T17:53:30.668-06:00
 * Generated source version: 3.3.4
 */

@WebFault(name = "WsiAuthenticationException", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/pcdebugtools/PCDebugToolsAPI")
public class WsiAuthenticationException_Exception extends Exception {

    private framework.integrations.gwServices.debugToolsAPI.pc.WsiAuthenticationException wsiAuthenticationException;

    public WsiAuthenticationException_Exception() {
        super();
    }

    public WsiAuthenticationException_Exception(String message) {
        super(message);
    }

    public WsiAuthenticationException_Exception(String message, java.lang.Throwable cause) {
        super(message, cause);
    }

    public WsiAuthenticationException_Exception(String message, framework.integrations.gwServices.debugToolsAPI.pc.WsiAuthenticationException wsiAuthenticationException) {
        super(message);
        this.wsiAuthenticationException = wsiAuthenticationException;
    }

    public WsiAuthenticationException_Exception(String message, framework.integrations.gwServices.debugToolsAPI.pc.WsiAuthenticationException wsiAuthenticationException, java.lang.Throwable cause) {
        super(message, cause);
        this.wsiAuthenticationException = wsiAuthenticationException;
    }

    public framework.integrations.gwServices.debugToolsAPI.pc.WsiAuthenticationException getFaultInfo() {
        return this.wsiAuthenticationException;
    }
}
