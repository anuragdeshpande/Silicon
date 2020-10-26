
package framework.integrations.gwServices.messagingToolsAPI.pc.com.guidewire.pc.ws.gw.webservice.pc.pc800.messagingtoolsapi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import framework.integrations.gwServices.messagingToolsAPI.pc.com.guidewire.gw.api.webservice.messagingtools.MessageStatisticsData;


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
 *         &lt;element name="return" type="{http://guidewire.com/gw/api/webservice/messagingTools}MessageStatisticsData" minOccurs="0"/>
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
