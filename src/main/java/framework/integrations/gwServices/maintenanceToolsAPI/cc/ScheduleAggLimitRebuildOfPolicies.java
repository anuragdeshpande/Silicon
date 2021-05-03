
package framework.integrations.gwServices.maintenanceToolsAPI.cc;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element name="policyNumbers" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Entry" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "", propOrder = {
    "policyNumbers"
})
@XmlRootElement(name = "scheduleAggLimitRebuildOfPolicies", namespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI")
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:26:07-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class ScheduleAggLimitRebuildOfPolicies {

    @XmlElement(namespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:26:07-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected ScheduleAggLimitRebuildOfPolicies.PolicyNumbers policyNumbers;

    /**
     * Gets the value of the policyNumbers property.
     * 
     * @return
     *     possible object is
     *     {@link ScheduleAggLimitRebuildOfPolicies.PolicyNumbers }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:26:07-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public ScheduleAggLimitRebuildOfPolicies.PolicyNumbers getPolicyNumbers() {
        return policyNumbers;
    }

    /**
     * Sets the value of the policyNumbers property.
     * 
     * @param value
     *     allowed object is
     *     {@link ScheduleAggLimitRebuildOfPolicies.PolicyNumbers }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:26:07-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setPolicyNumbers(ScheduleAggLimitRebuildOfPolicies.PolicyNumbers value) {
        this.policyNumbers = value;
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
     *         &lt;element name="Entry" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
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
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:26:07-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public static class PolicyNumbers {

        @XmlElement(name = "Entry", namespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI", nillable = true)
        @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:26:07-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
        protected List<String> entry;

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
         * {@link String }
         * 
         * 
         */
        @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:26:07-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
        public List<String> getEntry() {
            if (entry == null) {
                entry = new ArrayList<String>();
            }
            return this.entry;
        }

    }

}
