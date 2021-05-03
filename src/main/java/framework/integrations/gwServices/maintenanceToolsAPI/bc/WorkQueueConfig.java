
package framework.integrations.gwServices.maintenanceToolsAPI.bc;

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
 * &lt;complexType name="WorkQueueConfig"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="BatchSize" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="Instances" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="MaxPollInterval" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="ThrottleInterval" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
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
public class WorkQueueConfig {

    @XmlElement(name = "BatchSize")
    protected Integer batchSize;
    @XmlElement(name = "Instances")
    protected Integer instances;
    @XmlElement(name = "MaxPollInterval")
    protected Long maxPollInterval;
    @XmlElement(name = "ThrottleInterval")
    protected Long throttleInterval;

    /**
     * Gets the value of the batchSize property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
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
    public void setThrottleInterval(Long value) {
        this.throttleInterval = value;
    }

}
