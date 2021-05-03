
package framework.integrations.gwServices.maintenanceToolsAPI.cc;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for ProcessStatus complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProcessStatus">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Complete" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="DateCompleted" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="DateCreated" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="DetailedStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Executing" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="FailedOps" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="FailureReason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OpsCompleted" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="OpsExpected" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Progress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ReturnValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ServerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="StartDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="Starting" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="StartingOrExecuting" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="Success" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="Type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProcessStatus", namespace = "http://guidewire.com/gw/api/webservice/maintenanceTools", propOrder = {
    "complete",
    "dateCompleted",
    "dateCreated",
    "detailedStatus",
    "executing",
    "failedOps",
    "failureReason",
    "opsCompleted",
    "opsExpected",
    "progress",
    "returnValue",
    "serverId",
    "startDate",
    "starting",
    "startingOrExecuting",
    "success",
    "type"
})
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:26:07-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class ProcessStatus {

    @XmlElement(name = "Complete", namespace = "http://guidewire.com/gw/api/webservice/maintenanceTools")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:26:07-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected Boolean complete;
    @XmlElement(name = "DateCompleted", namespace = "http://guidewire.com/gw/api/webservice/maintenanceTools")
    @XmlSchemaType(name = "dateTime")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:26:07-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected XMLGregorianCalendar dateCompleted;
    @XmlElement(name = "DateCreated", namespace = "http://guidewire.com/gw/api/webservice/maintenanceTools")
    @XmlSchemaType(name = "dateTime")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:26:07-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected XMLGregorianCalendar dateCreated;
    @XmlElement(name = "DetailedStatus", namespace = "http://guidewire.com/gw/api/webservice/maintenanceTools")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:26:07-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String detailedStatus;
    @XmlElement(name = "Executing", namespace = "http://guidewire.com/gw/api/webservice/maintenanceTools")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:26:07-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected Boolean executing;
    @XmlElement(name = "FailedOps", namespace = "http://guidewire.com/gw/api/webservice/maintenanceTools")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:26:07-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected Integer failedOps;
    @XmlElement(name = "FailureReason", namespace = "http://guidewire.com/gw/api/webservice/maintenanceTools")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:26:07-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String failureReason;
    @XmlElement(name = "OpsCompleted", namespace = "http://guidewire.com/gw/api/webservice/maintenanceTools")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:26:07-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected Integer opsCompleted;
    @XmlElement(name = "OpsExpected", namespace = "http://guidewire.com/gw/api/webservice/maintenanceTools")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:26:07-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected Integer opsExpected;
    @XmlElement(name = "Progress", namespace = "http://guidewire.com/gw/api/webservice/maintenanceTools")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:26:07-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String progress;
    @XmlElement(name = "ReturnValue", namespace = "http://guidewire.com/gw/api/webservice/maintenanceTools")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:26:07-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String returnValue;
    @XmlElement(name = "ServerId", namespace = "http://guidewire.com/gw/api/webservice/maintenanceTools")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:26:07-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String serverId;
    @XmlElement(name = "StartDate", namespace = "http://guidewire.com/gw/api/webservice/maintenanceTools")
    @XmlSchemaType(name = "dateTime")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:26:07-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected XMLGregorianCalendar startDate;
    @XmlElement(name = "Starting", namespace = "http://guidewire.com/gw/api/webservice/maintenanceTools")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:26:07-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected Boolean starting;
    @XmlElement(name = "StartingOrExecuting", namespace = "http://guidewire.com/gw/api/webservice/maintenanceTools")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:26:07-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected Boolean startingOrExecuting;
    @XmlElement(name = "Success", namespace = "http://guidewire.com/gw/api/webservice/maintenanceTools")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:26:07-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected Boolean success;
    @XmlElement(name = "Type", namespace = "http://guidewire.com/gw/api/webservice/maintenanceTools")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:26:07-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String type;

    /**
     * Gets the value of the complete property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:26:07-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public Boolean isComplete() {
        return complete;
    }

    /**
     * Sets the value of the complete property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:26:07-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setComplete(Boolean value) {
        this.complete = value;
    }

    /**
     * Gets the value of the dateCompleted property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:26:07-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public XMLGregorianCalendar getDateCompleted() {
        return dateCompleted;
    }

    /**
     * Sets the value of the dateCompleted property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:26:07-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setDateCompleted(XMLGregorianCalendar value) {
        this.dateCompleted = value;
    }

    /**
     * Gets the value of the dateCreated property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:26:07-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public XMLGregorianCalendar getDateCreated() {
        return dateCreated;
    }

    /**
     * Sets the value of the dateCreated property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:26:07-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setDateCreated(XMLGregorianCalendar value) {
        this.dateCreated = value;
    }

    /**
     * Gets the value of the detailedStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:26:07-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getDetailedStatus() {
        return detailedStatus;
    }

    /**
     * Sets the value of the detailedStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:26:07-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setDetailedStatus(String value) {
        this.detailedStatus = value;
    }

    /**
     * Gets the value of the executing property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:26:07-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public Boolean isExecuting() {
        return executing;
    }

    /**
     * Sets the value of the executing property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:26:07-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setExecuting(Boolean value) {
        this.executing = value;
    }

    /**
     * Gets the value of the failedOps property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:26:07-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public Integer getFailedOps() {
        return failedOps;
    }

    /**
     * Sets the value of the failedOps property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:26:07-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setFailedOps(Integer value) {
        this.failedOps = value;
    }

    /**
     * Gets the value of the failureReason property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:26:07-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getFailureReason() {
        return failureReason;
    }

    /**
     * Sets the value of the failureReason property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:26:07-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setFailureReason(String value) {
        this.failureReason = value;
    }

    /**
     * Gets the value of the opsCompleted property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:26:07-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public Integer getOpsCompleted() {
        return opsCompleted;
    }

    /**
     * Sets the value of the opsCompleted property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:26:07-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setOpsCompleted(Integer value) {
        this.opsCompleted = value;
    }

    /**
     * Gets the value of the opsExpected property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:26:07-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public Integer getOpsExpected() {
        return opsExpected;
    }

    /**
     * Sets the value of the opsExpected property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:26:07-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setOpsExpected(Integer value) {
        this.opsExpected = value;
    }

    /**
     * Gets the value of the progress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:26:07-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getProgress() {
        return progress;
    }

    /**
     * Sets the value of the progress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:26:07-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setProgress(String value) {
        this.progress = value;
    }

    /**
     * Gets the value of the returnValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:26:07-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getReturnValue() {
        return returnValue;
    }

    /**
     * Sets the value of the returnValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:26:07-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setReturnValue(String value) {
        this.returnValue = value;
    }

    /**
     * Gets the value of the serverId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:26:07-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getServerId() {
        return serverId;
    }

    /**
     * Sets the value of the serverId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:26:07-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setServerId(String value) {
        this.serverId = value;
    }

    /**
     * Gets the value of the startDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:26:07-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public XMLGregorianCalendar getStartDate() {
        return startDate;
    }

    /**
     * Sets the value of the startDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:26:07-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setStartDate(XMLGregorianCalendar value) {
        this.startDate = value;
    }

    /**
     * Gets the value of the starting property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:26:07-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public Boolean isStarting() {
        return starting;
    }

    /**
     * Sets the value of the starting property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:26:07-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setStarting(Boolean value) {
        this.starting = value;
    }

    /**
     * Gets the value of the startingOrExecuting property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:26:07-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public Boolean isStartingOrExecuting() {
        return startingOrExecuting;
    }

    /**
     * Sets the value of the startingOrExecuting property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:26:07-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setStartingOrExecuting(Boolean value) {
        this.startingOrExecuting = value;
    }

    /**
     * Gets the value of the success property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:26:07-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public Boolean isSuccess() {
        return success;
    }

    /**
     * Sets the value of the success property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:26:07-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setSuccess(Boolean value) {
        this.success = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:26:07-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:26:07-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setType(String value) {
        this.type = value;
    }

}
