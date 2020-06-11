
package framework.integrations.gwServices.messagingToolsAPI.ab.com.guidewire.ab.ws.gw.webservice.ab.ab801.messagingtoolsapi;

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
 *         &lt;element name="senderRefID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="destID" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "senderRefID",
    "destID"
})
@XmlRootElement(name = "getMessageIdBySenderRefId")
public class GetMessageIdBySenderRefId {

    protected String senderRefID;
    protected int destID;

    /**
     * Gets the value of the senderRefID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSenderRefID() {
        return senderRefID;
    }

    /**
     * Sets the value of the senderRefID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSenderRefID(String value) {
        this.senderRefID = value;
    }

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

}
