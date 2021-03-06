
package framework.integrations.gwServices.gwTestRunner.com.waysysweb;

import framework.integrations.gwServices.gwTestRunner.com.example.unittestcase.TestCaseResult;

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
 *         &lt;element name="return" type="{http://example.com/unittestcase}TestCaseResult" minOccurs="0"/>
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
@XmlRootElement(name = "runTestResponse")
public class RunTestResponse {

    @XmlElement(name = "return")
    protected TestCaseResult _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link TestCaseResult }
     *     
     */
    public TestCaseResult getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link TestCaseResult }
     *     
     */
    public void setReturn(TestCaseResult value) {
        this._return = value;
    }

}
