
package framework.integrations.gwServices.maintenanceToolsAPI.ab;

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
 *         &lt;element name="return" type="{http://guidewire.com/gw/api/webservice/maintenanceTools}WorkQueueConfig" minOccurs="0"/>
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
@XmlRootElement(name = "getWorkQueueConfigResponse", namespace = "http://guidewire.com/ab/ws/gw/webservice/ab/ab1000/MaintenanceToolsAPI")
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:25:33-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class GetWorkQueueConfigResponse {

    @XmlElement(name = "return", namespace = "http://guidewire.com/ab/ws/gw/webservice/ab/ab1000/MaintenanceToolsAPI")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:25:33-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected WorkQueueConfig _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link WorkQueueConfig }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:25:33-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public WorkQueueConfig getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link WorkQueueConfig }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:25:33-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setReturn(WorkQueueConfig value) {
        this._return = value;
    }

}
