
package framework.integrations.gwServices.messagingToolsAPI.bc.com.guidewire.bc.ws.gw.webservice.bc.messagingtoolsapi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="destID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="policyPeriodPublicID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "destID",
    "policyPeriodPublicID"
})
@XmlRootElement(name = "resyncPolicyPeriod")
public class ResyncPolicyPeriod {

    protected int destID;
    protected String policyPeriodPublicID;

    /**
     * Gets the value of the destID property.
     * 
     */
    public int getDestID() {
        return destID;
    }

    /**
     * Sets the value of the destID property.
     * 
     */
    public void setDestID(int value) {
        this.destID = value;
    }

    /**
     * Gets the value of the policyPeriodPublicID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPolicyPeriodPublicID() {
        return policyPeriodPublicID;
    }

    /**
     * Sets the value of the policyPeriodPublicID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPolicyPeriodPublicID(String value) {
        this.policyPeriodPublicID = value;
    }

}
