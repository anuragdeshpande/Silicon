
package framework.integrations.gwServices.messagingToolsAPI.ab;

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
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="destID" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="abcontactID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "destID",
    "abcontactID"
})
@XmlRootElement(name = "resyncABContact")
public class ResyncABContact {

    protected int destID;
    protected String abcontactID;

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
     * Gets the value of the abcontactID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAbcontactID() {
        return abcontactID;
    }

    /**
     * Sets the value of the abcontactID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAbcontactID(String value) {
        this.abcontactID = value;
    }

}
