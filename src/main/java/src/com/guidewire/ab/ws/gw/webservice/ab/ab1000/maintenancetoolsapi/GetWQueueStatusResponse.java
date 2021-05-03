
package com.guidewire.ab.ws.gw.webservice.ab.ab1000.maintenancetoolsapi;

import com.guidewire.gw.api.webservice.maintenancetools.WQueueStatus;

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
 *         &lt;element name="return" type="{http://guidewire.com/gw/api/webservice/maintenanceTools}WQueueStatus" minOccurs="0"/>
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
@XmlRootElement(name = "getWQueueStatusResponse")
public class GetWQueueStatusResponse {

    @XmlElement(name = "return")
    protected WQueueStatus _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link WQueueStatus }
     *     
     */
    public WQueueStatus getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link WQueueStatus }
     *     
     */
    public void setReturn(WQueueStatus value) {
        this._return = value;
    }

}
