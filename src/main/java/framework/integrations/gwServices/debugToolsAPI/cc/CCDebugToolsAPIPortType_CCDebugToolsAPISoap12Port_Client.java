
package framework.integrations.gwServices.debugToolsAPI.cc;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import javax.xml.namespace.QName;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * This class was generated by Apache CXF 3.3.4
 * 2021-06-03T13:54:41.253-06:00
 * Generated source version: 3.3.4
 *
 */
public final class CCDebugToolsAPIPortType_CCDebugToolsAPISoap12Port_Client {

    private static final QName SERVICE_NAME = new QName("http://guidewire.com/cc/ws/gw/webservice/cc/cc1000/ccdebugtools/CCDebugToolsAPI", "CCDebugToolsAPI");

    private CCDebugToolsAPIPortType_CCDebugToolsAPISoap12Port_Client() {
    }

    public static void main(String args[]) throws java.lang.Exception {
        URL wsdlURL = CCDebugToolsAPI.WSDL_LOCATION;
        if (args.length > 0 && args[0] != null && !"".equals(args[0])) {
            File wsdlFile = new File(args[0]);
            try {
                if (wsdlFile.exists()) {
                    wsdlURL = wsdlFile.toURI().toURL();
                } else {
                    wsdlURL = new URL(args[0]);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        CCDebugToolsAPI ss = new CCDebugToolsAPI(wsdlURL, SERVICE_NAME);
        CCDebugToolsAPIPortType port = ss.getCCDebugToolsAPISoap12Port();

        {
        System.out.println("Invoking addDays...");
        int _addDays_days = 0;
        try {
            port.addDays(_addDays_days);

        } catch (WsiAuthenticationException_Exception e) {
            System.out.println("Expected exception: WsiAuthenticationException has occurred.");
            System.out.println(e.toString());
        }

            }
        {
        System.out.println("Invoking runFinancialEsc...");
        try {
            port.runFinancialEsc();

        } catch (WsiAuthenticationException_Exception e) {
            System.out.println("Expected exception: WsiAuthenticationException has occurred.");
            System.out.println(e.toString());
        }

            }
        {
        System.out.println("Invoking moveClockTo...");
        javax.xml.datatype.XMLGregorianCalendar _moveClockTo_date = null;
        try {
            port.moveClockTo(_moveClockTo_date);

        } catch (WsiAuthenticationException_Exception e) {
            System.out.println("Expected exception: WsiAuthenticationException has occurred.");
            System.out.println(e.toString());
        }

            }
        {
        System.out.println("Invoking getCurrentTime...");
        try {
            javax.xml.datatype.XMLGregorianCalendar _getCurrentTime__return = port.getCurrentTime();
            System.out.println("getCurrentTime.result=" + _getCurrentTime__return);

        } catch (WsiAuthenticationException_Exception e) {
            System.out.println("Expected exception: WsiAuthenticationException has occurred.");
            System.out.println(e.toString());
        }

            }
        {
        System.out.println("Invoking addMonths...");
        int _addMonths_months = 0;
        try {
            port.addMonths(_addMonths_months);

        } catch (WsiAuthenticationException_Exception e) {
            System.out.println("Expected exception: WsiAuthenticationException has occurred.");
            System.out.println(e.toString());
        }

            }
        {
        System.out.println("Invoking isFinancialEscRunning...");
        try {
            java.lang.Boolean _isFinancialEscRunning__return = port.isFinancialEscRunning();
            System.out.println("isFinancialEscRunning.result=" + _isFinancialEscRunning__return);

        } catch (WsiAuthenticationException_Exception e) {
            System.out.println("Expected exception: WsiAuthenticationException has occurred.");
            System.out.println(e.toString());
        }

            }

        System.exit(0);
    }

}