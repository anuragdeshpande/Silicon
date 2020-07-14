
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
 *         &lt;element name="retryLimit" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "retryLimit"
})
@XmlRootElement(name = "retryRetryableSomeErrorMessages")
public class RetryRetryableSomeErrorMessages {

    protected int destID;
    protected int retryLimit;

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
     * Gets the value of the retryLimit property.
     * 
     */
    public int getRetryLimit() {
        return retryLimit;
    }

    /**
     * Sets the value of the retryLimit property.
     * 
     */
    public void setRetryLimit(int value) {
        this.retryLimit = value;
    }

}