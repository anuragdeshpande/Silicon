
package com.guidewire.gw.api.webservice.maintenancetools;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for WQTaskDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WQTaskDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Active" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="ConsecutiveExceptions" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="EndTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="Exceptions" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Hostname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="InstanceId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="ItemsCheckedOut" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="ItemsFailed" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="ItemsProcessed" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="ItemsSkipped" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="OrphansAdopted" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="StartTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WQTaskDetails", propOrder = {
    "active",
    "consecutiveExceptions",
    "endTime",
    "exceptions",
    "hostname",
    "instanceId",
    "itemsCheckedOut",
    "itemsFailed",
    "itemsProcessed",
    "itemsSkipped",
    "orphansAdopted",
    "startTime"
})
public class WQTaskDetails {

    @XmlElement(name = "Active")
    protected Boolean active;
    @XmlElement(name = "ConsecutiveExceptions")
    protected Integer consecutiveExceptions;
    @XmlElement(name = "EndTime")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar endTime;
    @XmlElement(name = "Exceptions")
    protected Integer exceptions;
    @XmlElement(name = "Hostname")
    protected String hostname;
    @XmlElement(name = "InstanceId")
    protected Integer instanceId;
    @XmlElement(name = "ItemsCheckedOut")
    protected Integer itemsCheckedOut;
    @XmlElement(name = "ItemsFailed")
    protected Integer itemsFailed;
    @XmlElement(name = "ItemsProcessed")
    protected Integer itemsProcessed;
    @XmlElement(name = "ItemsSkipped")
    protected Integer itemsSkipped;
    @XmlElement(name = "OrphansAdopted")
    protected Integer orphansAdopted;
    @XmlElement(name = "StartTime")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar startTime;

    /**
     * Gets the value of the active property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
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
    public void setActive(Boolean value) {
        this.active = value;
    }

    /**
     * Gets the value of the consecutiveExceptions property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getConsecutiveExceptions() {
        return consecutiveExceptions;
    }

    /**
     * Sets the value of the consecutiveExceptions property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setConsecutiveExceptions(Integer value) {
        this.consecutiveExceptions = value;
    }

    /**
     * Gets the value of the endTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
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
    public void setEndTime(XMLGregorianCalendar value) {
        this.endTime = value;
    }

    /**
     * Gets the value of the exceptions property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getExceptions() {
        return exceptions;
    }

    /**
     * Sets the value of the exceptions property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setExceptions(Integer value) {
        this.exceptions = value;
    }

    /**
     * Gets the value of the hostname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
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
    public void setHostname(String value) {
        this.hostname = value;
    }

    /**
     * Gets the value of the instanceId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getInstanceId() {
        return instanceId;
    }

    /**
     * Sets the value of the instanceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setInstanceId(Integer value) {
        this.instanceId = value;
    }

    /**
     * Gets the value of the itemsCheckedOut property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getItemsCheckedOut() {
        return itemsCheckedOut;
    }

    /**
     * Sets the value of the itemsCheckedOut property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setItemsCheckedOut(Integer value) {
        this.itemsCheckedOut = value;
    }

    /**
     * Gets the value of the itemsFailed property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getItemsFailed() {
        return itemsFailed;
    }

    /**
     * Sets the value of the itemsFailed property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setItemsFailed(Integer value) {
        this.itemsFailed = value;
    }

    /**
     * Gets the value of the itemsProcessed property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getItemsProcessed() {
        return itemsProcessed;
    }

    /**
     * Sets the value of the itemsProcessed property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setItemsProcessed(Integer value) {
        this.itemsProcessed = value;
    }

    /**
     * Gets the value of the itemsSkipped property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getItemsSkipped() {
        return itemsSkipped;
    }

    /**
     * Sets the value of the itemsSkipped property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setItemsSkipped(Integer value) {
        this.itemsSkipped = value;
    }

    /**
     * Gets the value of the orphansAdopted property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getOrphansAdopted() {
        return orphansAdopted;
    }

    /**
     * Sets the value of the orphansAdopted property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setOrphansAdopted(Integer value) {
        this.orphansAdopted = value;
    }

    /**
     * Gets the value of the startTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
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
    public void setStartTime(XMLGregorianCalendar value) {
        this.startTime = value;
    }

}
