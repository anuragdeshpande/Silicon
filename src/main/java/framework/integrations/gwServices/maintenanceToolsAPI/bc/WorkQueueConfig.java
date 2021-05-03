
package framework.integrations.gwServices.maintenanceToolsAPI.bc;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WorkQueueConfig complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WorkQueueConfig">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BatchSize" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Instances" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="MaxPollInterval" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="ThrottleInterval" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WorkQueueConfig", namespace = "http://guidewire.com/gw/api/webservice/maintenanceTools", propOrder = {
    "batchSize",
    "instances",
    "maxPollInterval",
    "throttleInterval"
})
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:28:08-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class WorkQueueConfig {

    @XmlElement(name = "BatchSize", namespace = "http://guidewire.com/gw/api/webservice/maintenanceTools")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:28:08-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected Integer batchSize;
    @XmlElement(name = "Instances", namespace = "http://guidewire.com/gw/api/webservice/maintenanceTools")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:28:08-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected Integer instances;
    @XmlElement(name = "MaxPollInterval", namespace = "http://guidewire.com/gw/api/webservice/maintenanceTools")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:28:08-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected Long maxPollInterval;
    @XmlElement(name = "ThrottleInterval", namespace = "http://guidewire.com/gw/api/webservice/maintenanceTools")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:28:08-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected Long throttleInterval;

    /**
     * Gets the value of the batchSize property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:28:08-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public Integer getBatchSize() {
        return batchSize;
    }

    /**
     * Sets the value of the batchSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:28:08-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setBatchSize(Integer value) {
        this.batchSize = value;
    }

    /**
     * Gets the value of the instances property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:28:08-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public Integer getInstances() {
        return instances;
    }

    /**
     * Sets the value of the instances property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:28:08-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setInstances(Integer value) {
        this.instances = value;
    }

    /**
     * Gets the value of the maxPollInterval property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:28:08-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public Long getMaxPollInterval() {
        return maxPollInterval;
    }

    /**
     * Sets the value of the maxPollInterval property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:28:08-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setMaxPollInterval(Long value) {
        this.maxPollInterval = value;
    }

    /**
     * Gets the value of the throttleInterval property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:28:08-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public Long getThrottleInterval() {
        return throttleInterval;
    }

    /**
     * Sets the value of the throttleInterval property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:28:08-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setThrottleInterval(Long value) {
        this.throttleInterval = value;
    }

}
