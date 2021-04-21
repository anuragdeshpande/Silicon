
package framework.integrations.gwServices.messagingToolsAPI.pc.com.guidewire.pc.ws.gw.webservice.pc.pc800.messagingtoolsapi;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;


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
 *         &lt;element name="cutoff" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
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
    "cutoff"
})
@XmlRootElement(name = "purgeCompletedMessages")
public class PurgeCompletedMessages {

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar cutoff;

    /**
     * Gets the value of the cutoff property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCutoff() {
        return cutoff;
    }

    /**
     * Sets the value of the cutoff property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCutoff(XMLGregorianCalendar value) {
        this.cutoff = value;
    }

}
