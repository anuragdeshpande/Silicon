
package framework.integrations.gwServices.gwTestRunner.com.waysysweb;

import framework.integrations.gwServices.gwTestRunner.generated.Testsuite;

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
 *         &lt;element name="return" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{}testsuite"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
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
@XmlRootElement(name = "runTestAsJUnitResponse")
public class RunTestAsJUnitResponse {

    @XmlElement(name = "return")
    protected RunTestAsJUnitResponse.Return _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link RunTestAsJUnitResponse.Return }
     *     
     */
    public RunTestAsJUnitResponse.Return getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link RunTestAsJUnitResponse.Return }
     *     
     */
    public void setReturn(RunTestAsJUnitResponse.Return value) {
        this._return = value;
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
     *         &lt;element ref="{}testsuite"/>
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
        "testsuite"
    })
    public static class Return {

        @XmlElement(namespace = "", required = true)
        protected Testsuite testsuite;

        /**
         * Gets the value of the testsuite property.
         * 
         * @return
         *     possible object is
         *     {@link Testsuite }
         *     
         */
        public Testsuite getTestsuite() {
            return testsuite;
        }

        /**
         * Sets the value of the testsuite property.
         * 
         * @param value
         *     allowed object is
         *     {@link Testsuite }
         *     
         */
        public void setTestsuite(Testsuite value) {
            this.testsuite = value;
        }

    }

}
