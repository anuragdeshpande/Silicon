
package framework.integrations.gwServices.messagingToolsAPI.cc.com.guidewire.gw.api.webservice.messagingtools;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Acknowledgement complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Acknowledgement">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AckCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CustomEvents" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Entry" type="{http://guidewire.com/gw/api/webservice/messagingTools}CustomEvents" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Duplicate" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="Error" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="ErrorDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FieldChanges" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Entry" type="{http://guidewire.com/gw/api/webservice/messagingTools}FieldChanges" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="MessageID" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="Retryable" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Acknowledgement", propOrder = {
    "ackCode",
    "customEvents",
    "duplicate",
    "error",
    "errorDescription",
    "fieldChanges",
    "messageID",
    "retryable"
})
public class Acknowledgement {

    @XmlElement(name = "AckCode")
    protected String ackCode;
    @XmlElement(name = "CustomEvents")
    protected Acknowledgement.CustomEvents customEvents;
    @XmlElement(name = "Duplicate")
    protected Boolean duplicate;
    @XmlElement(name = "Error")
    protected Boolean error;
    @XmlElement(name = "ErrorDescription")
    protected String errorDescription;
    @XmlElement(name = "FieldChanges")
    protected Acknowledgement.FieldChanges fieldChanges;
    @XmlElement(name = "MessageID")
    protected Long messageID;
    @XmlElement(name = "Retryable")
    protected Boolean retryable;

    /**
     * Gets the value of the ackCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAckCode() {
        return ackCode;
    }

    /**
     * Sets the value of the ackCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAckCode(String value) {
        this.ackCode = value;
    }

    /**
     * Gets the value of the customEvents property.
     * 
     * @return
     *     possible object is
     *     {@link Acknowledgement.CustomEvents }
     *     
     */
    public Acknowledgement.CustomEvents getCustomEvents() {
        return customEvents;
    }

    /**
     * Sets the value of the customEvents property.
     * 
     * @param value
     *     allowed object is
     *     {@link Acknowledgement.CustomEvents }
     *     
     */
    public void setCustomEvents(Acknowledgement.CustomEvents value) {
        this.customEvents = value;
    }

    /**
     * Gets the value of the duplicate property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDuplicate() {
        return duplicate;
    }

    /**
     * Sets the value of the duplicate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDuplicate(Boolean value) {
        this.duplicate = value;
    }

    /**
     * Gets the value of the error property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isError() {
        return error;
    }

    /**
     * Sets the value of the error property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setError(Boolean value) {
        this.error = value;
    }

    /**
     * Gets the value of the errorDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorDescription() {
        return errorDescription;
    }

    /**
     * Sets the value of the errorDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorDescription(String value) {
        this.errorDescription = value;
    }

    /**
     * Gets the value of the fieldChanges property.
     * 
     * @return
     *     possible object is
     *     {@link Acknowledgement.FieldChanges }
     *     
     */
    public Acknowledgement.FieldChanges getFieldChanges() {
        return fieldChanges;
    }

    /**
     * Sets the value of the fieldChanges property.
     * 
     * @param value
     *     allowed object is
     *     {@link Acknowledgement.FieldChanges }
     *     
     */
    public void setFieldChanges(Acknowledgement.FieldChanges value) {
        this.fieldChanges = value;
    }

    /**
     * Gets the value of the messageID property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getMessageID() {
        return messageID;
    }

    /**
     * Sets the value of the messageID property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setMessageID(Long value) {
        this.messageID = value;
    }

    /**
     * Gets the value of the retryable property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRetryable() {
        return retryable;
    }

    /**
     * Sets the value of the retryable property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRetryable(Boolean value) {
        this.retryable = value;
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
     *         &lt;element name="Entry" type="{http://guidewire.com/gw/api/webservice/messagingTools}CustomEvents" maxOccurs="unbounded" minOccurs="0"/>
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
    public static class CustomEvents {

        @XmlElement(name = "Entry", nillable = true)
        protected List<framework.integrations.gwServices.messagingToolsAPI.cc.com.guidewire.gw.api.webservice.messagingtools.CustomEvents> entry;

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
         * {@link framework.integrations.gwServices.messagingToolsAPI.cc.com.guidewire.gw.api.webservice.messagingtools.CustomEvents }
         * 
         * 
         */
        public List<framework.integrations.gwServices.messagingToolsAPI.cc.com.guidewire.gw.api.webservice.messagingtools.CustomEvents> getEntry() {
            if (entry == null) {
                entry = new ArrayList<framework.integrations.gwServices.messagingToolsAPI.cc.com.guidewire.gw.api.webservice.messagingtools.CustomEvents>();
            }
            return this.entry;
        }

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
     *         &lt;element name="Entry" type="{http://guidewire.com/gw/api/webservice/messagingTools}FieldChanges" maxOccurs="unbounded" minOccurs="0"/>
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
    public static class FieldChanges {

        @XmlElement(name = "Entry", nillable = true)
        protected List<framework.integrations.gwServices.messagingToolsAPI.cc.com.guidewire.gw.api.webservice.messagingtools.FieldChanges> entry;

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
         * {@link framework.integrations.gwServices.messagingToolsAPI.cc.com.guidewire.gw.api.webservice.messagingtools.FieldChanges }
         * 
         * 
         */
        public List<framework.integrations.gwServices.messagingToolsAPI.cc.com.guidewire.gw.api.webservice.messagingtools.FieldChanges> getEntry() {
            if (entry == null) {
                entry = new ArrayList<framework.integrations.gwServices.messagingToolsAPI.cc.com.guidewire.gw.api.webservice.messagingtools.FieldChanges>();
            }
            return this.entry;
        }

    }

}
