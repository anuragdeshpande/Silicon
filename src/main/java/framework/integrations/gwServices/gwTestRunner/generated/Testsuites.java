
package framework.integrations.gwServices.gwTestRunner.generated;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


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
 *         &lt;element ref="{}testsuite" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="time" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="tests" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="failures" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="disabled" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="errors" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "testsuite"
})
@XmlRootElement(name = "testsuites")
public class Testsuites {

    protected List<Testsuite> testsuite;
    @XmlAttribute(name = "name")
    protected String name;
    @XmlAttribute(name = "time")
    protected String time;
    @XmlAttribute(name = "tests")
    protected String tests;
    @XmlAttribute(name = "failures")
    protected String failures;
    @XmlAttribute(name = "disabled")
    protected String disabled;
    @XmlAttribute(name = "errors")
    protected String errors;

    /**
     * Gets the value of the testsuite property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the testsuite property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTestsuite().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Testsuite }
     * 
     * 
     */
    public List<Testsuite> getTestsuite() {
        if (testsuite == null) {
            testsuite = new ArrayList<Testsuite>();
        }
        return this.testsuite;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the time property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTime() {
        return time;
    }

    /**
     * Sets the value of the time property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTime(String value) {
        this.time = value;
    }

    /**
     * Gets the value of the tests property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTests() {
        return tests;
    }

    /**
     * Sets the value of the tests property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTests(String value) {
        this.tests = value;
    }

    /**
     * Gets the value of the failures property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFailures() {
        return failures;
    }

    /**
     * Sets the value of the failures property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFailures(String value) {
        this.failures = value;
    }

    /**
     * Gets the value of the disabled property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDisabled() {
        return disabled;
    }

    /**
     * Sets the value of the disabled property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDisabled(String value) {
        this.disabled = value;
    }

    /**
     * Gets the value of the errors property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrors() {
        return errors;
    }

    /**
     * Sets the value of the errors property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrors(String value) {
        this.errors = value;
    }

}
