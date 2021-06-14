
package framework.integrations.gwServices.messagingToolsAPI.pc;

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
 *         &lt;element name="return" type="{http://guidewire.com/gw/api/webservice/messagingTools}MessageStatisticsData" minOccurs="0"/&gt;
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
    "_return"
})
@XmlRootElement(name = "getMessageStatisticsForSafeOrderedObjectResponse")
public class GetMessageStatisticsForSafeOrderedObjectResponse {

    @XmlElement(name = "return")
    protected MessageStatisticsData _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link MessageStatisticsData }
     *     
     */
    public MessageStatisticsData getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link MessageStatisticsData }
     *     
     */
    public void setReturn(MessageStatisticsData value) {
        this._return = value;
    }

}
