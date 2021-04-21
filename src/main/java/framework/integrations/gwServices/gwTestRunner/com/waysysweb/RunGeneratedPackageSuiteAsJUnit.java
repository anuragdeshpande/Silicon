
package framework.integrations.gwServices.gwTestRunner.com.waysysweb;

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
 *         &lt;element name="suiteName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tests" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Entry" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="packageStr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "suiteName",
    "tests",
    "packageStr"
})
@XmlRootElement(name = "runGeneratedPackageSuiteAsJUnit")
public class RunGeneratedPackageSuiteAsJUnit {

    protected String suiteName;
    protected RunGeneratedPackageSuiteAsJUnit.Tests tests;
    protected String packageStr;

    /**
     * Gets the value of the suiteName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSuiteName() {
        return suiteName;
    }

    /**
     * Sets the value of the suiteName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSuiteName(String value) {
        this.suiteName = value;
    }

    /**
     * Gets the value of the tests property.
     * 
     * @return
     *     possible object is
     *     {@link RunGeneratedPackageSuiteAsJUnit.Tests }
     *     
     */
    public RunGeneratedPackageSuiteAsJUnit.Tests getTests() {
        return tests;
    }

    /**
     * Sets the value of the tests property.
     * 
     * @param value
     *     allowed object is
     *     {@link RunGeneratedPackageSuiteAsJUnit.Tests }
     *     
     */
    public void setTests(RunGeneratedPackageSuiteAsJUnit.Tests value) {
        this.tests = value;
    }

    /**
     * Gets the value of the packageStr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPackageStr() {
        return packageStr;
    }

    /**
     * Sets the value of the packageStr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPackageStr(String value) {
        this.packageStr = value;
    }


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
     *         &lt;element name="Entry" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
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
        "entry"
    })
    public static class Tests {

        @XmlElement(name = "Entry", nillable = true)
        protected List<String> entry;

        /**
         * Gets the value of the entry property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the entry property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getEntry().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getEntry() {
            if (entry == null) {
                entry = new ArrayList<String>();
            }
            return this.entry;
        }

    }

}
