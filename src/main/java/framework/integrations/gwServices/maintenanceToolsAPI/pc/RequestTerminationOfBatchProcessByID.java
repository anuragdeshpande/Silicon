
package framework.integrations.gwServices.maintenanceToolsAPI.pc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 *         &lt;element name="pid" type="{http://guidewire.com/gw/api/tools}ProcessID" minOccurs="0"/>
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
    "pid"
})
@XmlRootElement(name = "requestTerminationOfBatchProcessByID", namespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI")
public class RequestTerminationOfBatchProcessByID {

    @XmlElement(namespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI")
    protected ProcessID pid;

    /**
     * Gets the value of the pid property.
     * 
     * @return
     *     possible object is
     *     {@link ProcessID }
     *     
     */
    public ProcessID getPid() {
        return pid;
    }

    /**
     * Sets the value of the pid property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProcessID }
     *     
     */
    public void setPid(ProcessID value) {
        this.pid = value;
    }

}
