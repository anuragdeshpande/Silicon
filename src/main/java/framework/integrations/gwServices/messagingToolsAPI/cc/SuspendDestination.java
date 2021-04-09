
package framework.integrations.gwServices.messagingToolsAPI.cc;

import javax.xml.bind.annotation.*;


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
 *         &lt;element name="direction" type="{http://guidewire.com/gw/api/messaging}MessageProcessingDirection" minOccurs="0"/&gt;
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
    "direction"
})
@XmlRootElement(name = "suspendDestination")
public class SuspendDestination {

    protected int destID;
    @XmlSchemaType(name = "string")
    protected MessageProcessingDirection direction;

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
     * Gets the value of the direction property.
     * 
     * @return
     *     possible object is
     *     {@link MessageProcessingDirection }
     *     
     */
    public MessageProcessingDirection getDirection() {
        return direction;
    }

    /**
     * Sets the value of the direction property.
     * 
     * @param value
     *     allowed object is
     *     {@link MessageProcessingDirection }
     *     
     */
    public void setDirection(MessageProcessingDirection value) {
        this.direction = value;
    }

}
