
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
 *         &lt;element name="accountPublicID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "accountPublicID"
})
@XmlRootElement(name = "resyncAccount")
public class ResyncAccount {

    protected int destID;
    protected String accountPublicID;

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
     * Gets the value of the accountPublicID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountPublicID() {
        return accountPublicID;
    }

    /**
     * Sets the value of the accountPublicID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountPublicID(String value) {
        this.accountPublicID = value;
    }

}
