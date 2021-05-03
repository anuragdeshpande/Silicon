
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
 *         &lt;element name="dataSet" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "dataSet"
})
@XmlRootElement(name = "importSampleData", namespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MaintenanceToolsAPI")
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:28:08-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class ImportSampleData {

    @XmlElement(namespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MaintenanceToolsAPI")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:28:08-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String dataSet;

    /**
     * Gets the value of the dataSet property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:28:08-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getDataSet() {
        return dataSet;
    }

    /**
     * Sets the value of the dataSet property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2021-05-03T11:28:08-06:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setDataSet(String value) {
        this.dataSet = value;
    }

}
