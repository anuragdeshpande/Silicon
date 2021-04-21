
package framework.integrations.gwServices.maintenanceToolsAPI.cc;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 3.3.4
 * 2021-04-09T10:21:49.418-06:00
 * Generated source version: 3.3.4
 */

@WebFault(name = "SOAPException", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI")
public class SOAPException_Exception extends Exception {

    private framework.integrations.gwServices.maintenanceToolsAPI.cc.SOAPException soapException;

    public SOAPException_Exception() {
        super();
    }

    public SOAPException_Exception(String message) {
        super(message);
    }

    public SOAPException_Exception(String message, java.lang.Throwable cause) {
        super(message, cause);
    }

    public SOAPException_Exception(String message, framework.integrations.gwServices.maintenanceToolsAPI.cc.SOAPException soapException) {
        super(message);
        this.soapException = soapException;
    }

    public SOAPException_Exception(String message, framework.integrations.gwServices.maintenanceToolsAPI.cc.SOAPException soapException, java.lang.Throwable cause) {
        super(message, cause);
        this.soapException = soapException;
    }

    public framework.integrations.gwServices.maintenanceToolsAPI.cc.SOAPException getFaultInfo() {
        return this.soapException;
    }
}