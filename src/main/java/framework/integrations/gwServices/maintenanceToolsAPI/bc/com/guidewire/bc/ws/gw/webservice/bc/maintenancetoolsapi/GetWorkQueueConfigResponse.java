
package framework.integrations.gwServices.maintenanceToolsAPI.bc.com.guidewire.bc.ws.gw.webservice.bc.maintenancetoolsapi;

import framework.integrations.gwServices.maintenanceToolsAPI.bc.com.guidewire.gw.api.webservice.maintenancetools.WorkQueueConfig;

import javax.xml.bind.annotation.*;


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
 *         &lt;element name="return" type="{http://guidewire.com/gw/api/webservice/maintenanceTools}WorkQueueConfig" minOccurs="0"/>
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
@XmlRootElement(name = "getWorkQueueConfigResponse")
public class GetWorkQueueConfigResponse {

    @XmlElement(name = "return")
    protected WorkQueueConfig _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link WorkQueueConfig }
     *     
     */
    public WorkQueueConfig getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link WorkQueueConfig }
     *     
     */
    public void setReturn(WorkQueueConfig value) {
        this._return = value;
    }

}
