
package framework.integrations.gwServices.maintenanceToolsAPI.bc;

import javax.annotation.Generated;
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
@XmlRootElement(name = "requestTerminationOfBatchProcessByID", namespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MaintenanceToolsAPI")
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:28:08-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class RequestTerminationOfBatchProcessByID {

    @XmlElement(namespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MaintenanceToolsAPI")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:28:08-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected ProcessID pid;

    /**
     * Gets the value of the pid property.
     * 
     * @return
     *     possible object is
     *     {@link ProcessID }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:28:08-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
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
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:28:08-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setPid(ProcessID value) {
        this.pid = value;
    }

}
