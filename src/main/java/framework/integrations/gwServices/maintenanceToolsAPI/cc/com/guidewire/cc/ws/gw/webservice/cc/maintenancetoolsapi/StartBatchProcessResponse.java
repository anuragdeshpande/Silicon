
package framework.integrations.gwServices.maintenanceToolsAPI.cc.com.guidewire.cc.ws.gw.webservice.cc.maintenancetoolsapi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import framework.integrations.gwServices.maintenanceToolsAPI.cc.com.guidewire.gw.api.tools.ProcessID;


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
 *         &lt;element name="return" type="{http://guidewire.com/gw/api/tools}ProcessID" minOccurs="0"/>
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
@XmlRootElement(name = "startBatchProcessResponse")
public class StartBatchProcessResponse {

    @XmlElement(name = "return")
    protected ProcessID _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link ProcessID }
     *     
     */
    public ProcessID getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProcessID }
     *     
     */
    public void setReturn(ProcessID value) {
        this._return = value;
    }

}
