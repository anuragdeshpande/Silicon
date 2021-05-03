
package framework.integrations.gwServices.maintenanceToolsAPI.cc;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "_return"
})
@XmlRootElement(name = "isBatchProcessNameValidResponse", namespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI")
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:26:07-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class IsBatchProcessNameValidResponse {

    @XmlElement(name = "return", namespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:26:07-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected boolean _return;

    /**
     * Gets the value of the return property.
     * 
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:26:07-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public boolean isReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:26:07-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setReturn(boolean value) {
        this._return = value;
    }

}
