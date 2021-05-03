
package framework.integrations.gwServices.maintenanceToolsAPI.bc;

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
 *         &lt;element name="queueName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="config" type="{http://guidewire.com/gw/api/webservice/maintenanceTools}WorkQueueConfig" minOccurs="0"/>
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
    "queueName",
    "config"
})
@XmlRootElement(name = "setWorkQueueConfig", namespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MaintenanceToolsAPI")
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:28:08-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class SetWorkQueueConfig {

    @XmlElement(namespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MaintenanceToolsAPI")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:28:08-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String queueName;
    @XmlElement(namespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MaintenanceToolsAPI")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:28:08-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected WorkQueueConfig config;

    /**
     * Gets the value of the queueName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:28:08-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getQueueName() {
        return queueName;
    }

    /**
     * Sets the value of the queueName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:28:08-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setQueueName(String value) {
        this.queueName = value;
    }

    /**
     * Gets the value of the config property.
     * 
     * @return
     *     possible object is
     *     {@link WorkQueueConfig }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:28:08-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public WorkQueueConfig getConfig() {
        return config;
    }

    /**
     * Sets the value of the config property.
     * 
     * @param value
     *     allowed object is
     *     {@link WorkQueueConfig }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:28:08-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setConfig(WorkQueueConfig value) {
        this.config = value;
    }

}
