
package framework.integrations.gwServices.maintenanceToolsAPI.ab;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for WQueueExecutorDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WQueueExecutorDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Active" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="EndTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="Hostname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MaxNumOfWorkers" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="StartTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="Tasks" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Entry" type="{http://guidewire.com/gw/api/webservice/maintenanceTools}WQTaskDetails" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WQueueExecutorDetails", namespace = "http://guidewire.com/gw/api/webservice/maintenanceTools", propOrder = {
    "active",
    "endTime",
    "hostname",
    "maxNumOfWorkers",
    "startTime",
    "tasks"
})
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:25:33-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class WQueueExecutorDetails {

    @XmlElement(name = "Active", namespace = "http://guidewire.com/gw/api/webservice/maintenanceTools")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:25:33-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected Boolean active;
    @XmlElement(name = "EndTime", namespace = "http://guidewire.com/gw/api/webservice/maintenanceTools")
    @XmlSchemaType(name = "dateTime")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:25:33-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected XMLGregorianCalendar endTime;
    @XmlElement(name = "Hostname", namespace = "http://guidewire.com/gw/api/webservice/maintenanceTools")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:25:33-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String hostname;
    @XmlElement(name = "MaxNumOfWorkers", namespace = "http://guidewire.com/gw/api/webservice/maintenanceTools")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:25:33-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected Integer maxNumOfWorkers;
    @XmlElement(name = "StartTime", namespace = "http://guidewire.com/gw/api/webservice/maintenanceTools")
    @XmlSchemaType(name = "dateTime")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:25:33-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected XMLGregorianCalendar startTime;
    @XmlElement(name = "Tasks", namespace = "http://guidewire.com/gw/api/webservice/maintenanceTools")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:25:33-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected WQueueExecutorDetails.Tasks tasks;

    /**
     * Gets the value of the active property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:25:33-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public Boolean isActive() {
        return active;
    }

    /**
     * Sets the value of the active property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:25:33-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setActive(Boolean value) {
        this.active = value;
    }

    /**
     * Gets the value of the endTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:25:33-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public XMLGregorianCalendar getEndTime() {
        return endTime;
    }

    /**
     * Sets the value of the endTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:25:33-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setEndTime(XMLGregorianCalendar value) {
        this.endTime = value;
    }

    /**
     * Gets the value of the hostname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:25:33-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getHostname() {
        return hostname;
    }

    /**
     * Sets the value of the hostname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:25:33-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setHostname(String value) {
        this.hostname = value;
    }

    /**
     * Gets the value of the maxNumOfWorkers property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:25:33-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public Integer getMaxNumOfWorkers() {
        return maxNumOfWorkers;
    }

    /**
     * Sets the value of the maxNumOfWorkers property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:25:33-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setMaxNumOfWorkers(Integer value) {
        this.maxNumOfWorkers = value;
    }

    /**
     * Gets the value of the startTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:25:33-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public XMLGregorianCalendar getStartTime() {
        return startTime;
    }

    /**
     * Sets the value of the startTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:25:33-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setStartTime(XMLGregorianCalendar value) {
        this.startTime = value;
    }

    /**
     * Gets the value of the tasks property.
     * 
     * @return
     *     possible object is
     *     {@link WQueueExecutorDetails.Tasks }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:25:33-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public WQueueExecutorDetails.Tasks getTasks() {
        return tasks;
    }

    /**
     * Sets the value of the tasks property.
     * 
     * @param value
     *     allowed object is
     *     {@link WQueueExecutorDetails.Tasks }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:25:33-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setTasks(WQueueExecutorDetails.Tasks value) {
        this.tasks = value;
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
     *         &lt;element name="Entry" type="{http://guidewire.com/gw/api/webservice/maintenanceTools}WQTaskDetails" maxOccurs="unbounded" minOccurs="0"/>
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
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:25:33-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public static class Tasks {

        @XmlElement(name = "Entry", namespace = "http://guidewire.com/gw/api/webservice/maintenanceTools", nillable = true)
        @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:25:33-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
        protected List<WQTaskDetails> entry;

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
         * {@link WQTaskDetails }
         * 
         * 
         */
        @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:25:33-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
        public List<WQTaskDetails> getEntry() {
            if (entry == null) {
                entry = new ArrayList<WQTaskDetails>();
            }
            return this.entry;
        }

    }

}
