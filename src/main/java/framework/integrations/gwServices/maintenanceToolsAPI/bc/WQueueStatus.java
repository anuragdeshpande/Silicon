
package framework.integrations.gwServices.maintenanceToolsAPI.bc;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WQueueStatus complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WQueueStatus">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Executors" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Entry" type="{http://guidewire.com/gw/api/webservice/maintenanceTools}WQueueExecutorDetails" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="NumActiveExecutors" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="NumActiveWorkItems" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="QueueName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WQueueStatus", namespace = "http://guidewire.com/gw/api/webservice/maintenanceTools", propOrder = {
    "executors",
    "numActiveExecutors",
    "numActiveWorkItems",
    "queueName"
})
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:28:08-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class WQueueStatus {

    @XmlElement(name = "Executors", namespace = "http://guidewire.com/gw/api/webservice/maintenanceTools")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:28:08-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected WQueueStatus.Executors executors;
    @XmlElement(name = "NumActiveExecutors", namespace = "http://guidewire.com/gw/api/webservice/maintenanceTools")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:28:08-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected Integer numActiveExecutors;
    @XmlElement(name = "NumActiveWorkItems", namespace = "http://guidewire.com/gw/api/webservice/maintenanceTools")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:28:08-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected Integer numActiveWorkItems;
    @XmlElement(name = "QueueName", namespace = "http://guidewire.com/gw/api/webservice/maintenanceTools")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:28:08-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String queueName;

    /**
     * Gets the value of the executors property.
     * 
     * @return
     *     possible object is
     *     {@link WQueueStatus.Executors }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:28:08-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public WQueueStatus.Executors getExecutors() {
        return executors;
    }

    /**
     * Sets the value of the executors property.
     * 
     * @param value
     *     allowed object is
     *     {@link WQueueStatus.Executors }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:28:08-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setExecutors(WQueueStatus.Executors value) {
        this.executors = value;
    }

    /**
     * Gets the value of the numActiveExecutors property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:28:08-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public Integer getNumActiveExecutors() {
        return numActiveExecutors;
    }

    /**
     * Sets the value of the numActiveExecutors property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:28:08-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setNumActiveExecutors(Integer value) {
        this.numActiveExecutors = value;
    }

    /**
     * Gets the value of the numActiveWorkItems property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:28:08-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public Integer getNumActiveWorkItems() {
        return numActiveWorkItems;
    }

    /**
     * Sets the value of the numActiveWorkItems property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:28:08-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setNumActiveWorkItems(Integer value) {
        this.numActiveWorkItems = value;
    }

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
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="Entry" type="{http://guidewire.com/gw/api/webservice/maintenanceTools}WQueueExecutorDetails" maxOccurs="unbounded" minOccurs="0"/>
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
        "entry"
    })
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:28:08-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public static class Executors {

        @XmlElement(name = "Entry", namespace = "http://guidewire.com/gw/api/webservice/maintenanceTools", nillable = true)
        @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:28:08-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
        protected List<WQueueExecutorDetails> entry;

        /**
         * Gets the value of the entry property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the entry property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getEntry().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link WQueueExecutorDetails }
         * 
         * 
         */
        @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:28:08-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
        public List<WQueueExecutorDetails> getEntry() {
            if (entry == null) {
                entry = new ArrayList<WQueueExecutorDetails>();
            }
            return this.entry;
        }

    }

}
