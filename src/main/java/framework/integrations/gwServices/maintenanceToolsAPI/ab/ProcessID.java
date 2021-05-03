
package framework.integrations.gwServices.maintenanceToolsAPI.ab;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ProcessID complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProcessID">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Pid" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProcessID", namespace = "http://guidewire.com/gw/api/tools", propOrder = {
    "pid"
})
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:25:33-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class ProcessID {

    @XmlElement(name = "Pid", namespace = "http://guidewire.com/gw/api/tools")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:25:33-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected Long pid;

    /**
     * Gets the value of the pid property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:25:33-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public Long getPid() {
        return pid;
    }

    /**
     * Sets the value of the pid property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:25:33-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setPid(Long value) {
        this.pid = value;
    }

}
