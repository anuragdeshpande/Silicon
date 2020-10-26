
package framework.integrations.gwServices.messagingToolsAPI.pc.com.guidewire.pc.ws.gw.webservice.pc.pc800.messagingtoolsapi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import framework.integrations.gwServices.messagingToolsAPI.pc.com.guidewire.gw.api.messaging.MessageProcessingDirection;


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
 *         &lt;element name="direction" type="{http://guidewire.com/gw/api/messaging}MessageProcessingDirection" minOccurs="0"/>
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
    "direction"
})
@XmlRootElement(name = "suspendDestination")
public class SuspendDestination {

    protected int destID;
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
