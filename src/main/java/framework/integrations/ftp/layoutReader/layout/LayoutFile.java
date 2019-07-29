
package framework.integrations.ftp.layoutReader.layout;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
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
 *       &lt;choice>
 *         &lt;element name="FixedWidthLayout">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;choice>
 *                   &lt;element name="Line">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence maxOccurs="unbounded">
 *                             &lt;element ref="{https://www.w3schools.com}FixedWidthField"/>
 *                           &lt;/sequence>
 *                           &lt;attribute name="length" use="required" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" />
 *                           &lt;attribute name="mustEndWith" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="mustStartWith" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="MasterDetail">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="master">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence maxOccurs="unbounded">
 *                                       &lt;element ref="{https://www.w3schools.com}FixedWidthField"/>
 *                                     &lt;/sequence>
 *                                     &lt;attribute name="mustStartWith" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                     &lt;attribute name="mustEndWith" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                     &lt;attribute name="length" use="required" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" />
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="detail">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence maxOccurs="unbounded">
 *                                       &lt;element ref="{https://www.w3schools.com}FixedWidthField"/>
 *                                     &lt;/sequence>
 *                                     &lt;attribute name="mustStartWith" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                     &lt;attribute name="mustEndWith" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                     &lt;attribute name="length" use="required" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" />
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/choice>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="DelimitedLayout">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;choice>
 *                   &lt;element name="DelimitedLine">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element ref="{https://www.w3schools.com}DelimitedField"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/choice>
 *                 &lt;attribute name="delimiter" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="mustEndWith" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="mustStartWith" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "fixedWidthLayout",
    "delimitedLayout"
})
@XmlRootElement(name = "LayoutFile", namespace = "https://www.w3schools.com")
public class LayoutFile {

    @XmlElement(name = "FixedWidthLayout", namespace = "https://www.w3schools.com")
    protected LayoutFile.FixedWidthLayout fixedWidthLayout;
    @XmlElement(name = "DelimitedLayout", namespace = "https://www.w3schools.com")
    protected LayoutFile.DelimitedLayout delimitedLayout;

    /**
     * Gets the value of the fixedWidthLayout property.
     * 
     * @return
     *     possible object is
     *     {@link LayoutFile.FixedWidthLayout }
     *     
     */
    public LayoutFile.FixedWidthLayout getFixedWidthLayout() {
        return fixedWidthLayout;
    }

    /**
     * Sets the value of the fixedWidthLayout property.
     * 
     * @param value
     *     allowed object is
     *     {@link LayoutFile.FixedWidthLayout }
     *     
     */
    public void setFixedWidthLayout(LayoutFile.FixedWidthLayout value) {
        this.fixedWidthLayout = value;
    }

    /**
     * Gets the value of the delimitedLayout property.
     * 
     * @return
     *     possible object is
     *     {@link LayoutFile.DelimitedLayout }
     *     
     */
    public LayoutFile.DelimitedLayout getDelimitedLayout() {
        return delimitedLayout;
    }

    /**
     * Sets the value of the delimitedLayout property.
     * 
     * @param value
     *     allowed object is
     *     {@link LayoutFile.DelimitedLayout }
     *     
     */
    public void setDelimitedLayout(LayoutFile.DelimitedLayout value) {
        this.delimitedLayout = value;
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
     *       &lt;choice>
     *         &lt;element name="DelimitedLine">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element ref="{https://www.w3schools.com}DelimitedField"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/choice>
     *       &lt;attribute name="delimiter" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="mustEndWith" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="mustStartWith" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "delimitedLine"
    })
    public static class DelimitedLayout {

        @XmlElement(name = "DelimitedLine", namespace = "https://www.w3schools.com")
        protected LayoutFile.DelimitedLayout.DelimitedLine delimitedLine;
        @XmlAttribute(name = "delimiter", required = true)
        protected String delimiter;
        @XmlAttribute(name = "mustEndWith")
        protected String mustEndWith;
        @XmlAttribute(name = "mustStartWith")
        protected String mustStartWith;

        /**
         * Gets the value of the delimitedLine property.
         * 
         * @return
         *     possible object is
         *     {@link LayoutFile.DelimitedLayout.DelimitedLine }
         *     
         */
        public LayoutFile.DelimitedLayout.DelimitedLine getDelimitedLine() {
            return delimitedLine;
        }

        /**
         * Sets the value of the delimitedLine property.
         * 
         * @param value
         *     allowed object is
         *     {@link LayoutFile.DelimitedLayout.DelimitedLine }
         *     
         */
        public void setDelimitedLine(LayoutFile.DelimitedLayout.DelimitedLine value) {
            this.delimitedLine = value;
        }

        /**
         * Gets the value of the delimiter property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDelimiter() {
            return delimiter;
        }

        /**
         * Sets the value of the delimiter property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDelimiter(String value) {
            this.delimiter = value;
        }

        /**
         * Gets the value of the mustEndWith property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMustEndWith() {
            return mustEndWith;
        }

        /**
         * Sets the value of the mustEndWith property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMustEndWith(String value) {
            this.mustEndWith = value;
        }

        /**
         * Gets the value of the mustStartWith property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMustStartWith() {
            return mustStartWith;
        }

        /**
         * Sets the value of the mustStartWith property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMustStartWith(String value) {
            this.mustStartWith = value;
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
         *         &lt;element ref="{https://www.w3schools.com}DelimitedField"/>
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
            "delimitedField"
        })
        public static class DelimitedLine {

            @XmlElement(name = "DelimitedField", namespace = "https://www.w3schools.com", required = true)
            protected DelimitedField delimitedField;

            /**
             * Gets the value of the delimitedField property.
             * 
             * @return
             *     possible object is
             *     {@link DelimitedField }
             *     
             */
            public DelimitedField getDelimitedField() {
                return delimitedField;
            }

            /**
             * Sets the value of the delimitedField property.
             * 
             * @param value
             *     allowed object is
             *     {@link DelimitedField }
             *     
             */
            public void setDelimitedField(DelimitedField value) {
                this.delimitedField = value;
            }

        }

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
     *       &lt;choice>
     *         &lt;element name="Line">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence maxOccurs="unbounded">
     *                   &lt;element ref="{https://www.w3schools.com}FixedWidthField"/>
     *                 &lt;/sequence>
     *                 &lt;attribute name="length" use="required" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" />
     *                 &lt;attribute name="mustEndWith" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="mustStartWith" type="{http://www.w3.org/2001/XMLSchema}string" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="MasterDetail">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="master">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence maxOccurs="unbounded">
     *                             &lt;element ref="{https://www.w3schools.com}FixedWidthField"/>
     *                           &lt;/sequence>
     *                           &lt;attribute name="mustStartWith" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                           &lt;attribute name="mustEndWith" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                           &lt;attribute name="length" use="required" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" />
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="detail">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence maxOccurs="unbounded">
     *                             &lt;element ref="{https://www.w3schools.com}FixedWidthField"/>
     *                           &lt;/sequence>
     *                           &lt;attribute name="mustStartWith" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                           &lt;attribute name="mustEndWith" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                           &lt;attribute name="length" use="required" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" />
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/choice>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "line",
        "masterDetail"
    })
    public static class FixedWidthLayout {

        @XmlElement(name = "Line", namespace = "https://www.w3schools.com")
        protected LayoutFile.FixedWidthLayout.Line line;
        @XmlElement(name = "MasterDetail", namespace = "https://www.w3schools.com")
        protected LayoutFile.FixedWidthLayout.MasterDetail masterDetail;

        /**
         * Gets the value of the line property.
         * 
         * @return
         *     possible object is
         *     {@link LayoutFile.FixedWidthLayout.Line }
         *     
         */
        public LayoutFile.FixedWidthLayout.Line getLine() {
            return line;
        }

        /**
         * Sets the value of the line property.
         * 
         * @param value
         *     allowed object is
         *     {@link LayoutFile.FixedWidthLayout.Line }
         *     
         */
        public void setLine(LayoutFile.FixedWidthLayout.Line value) {
            this.line = value;
        }

        /**
         * Gets the value of the masterDetail property.
         * 
         * @return
         *     possible object is
         *     {@link LayoutFile.FixedWidthLayout.MasterDetail }
         *     
         */
        public LayoutFile.FixedWidthLayout.MasterDetail getMasterDetail() {
            return masterDetail;
        }

        /**
         * Sets the value of the masterDetail property.
         * 
         * @param value
         *     allowed object is
         *     {@link LayoutFile.FixedWidthLayout.MasterDetail }
         *     
         */
        public void setMasterDetail(LayoutFile.FixedWidthLayout.MasterDetail value) {
            this.masterDetail = value;
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
         *       &lt;sequence maxOccurs="unbounded">
         *         &lt;element ref="{https://www.w3schools.com}FixedWidthField"/>
         *       &lt;/sequence>
         *       &lt;attribute name="length" use="required" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" />
         *       &lt;attribute name="mustEndWith" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="mustStartWith" type="{http://www.w3.org/2001/XMLSchema}string" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "fixedWidthField"
        })
        public static class Line {

            @XmlElement(name = "FixedWidthField", namespace = "https://www.w3schools.com", required = true)
            protected List<FixedWidthField> fixedWidthField;
            @XmlAttribute(name = "length", required = true)
            @XmlSchemaType(name = "positiveInteger")
            protected BigInteger length;
            @XmlAttribute(name = "mustEndWith")
            protected String mustEndWith;
            @XmlAttribute(name = "mustStartWith")
            protected String mustStartWith;

            /**
             * Gets the value of the fixedWidthField property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the fixedWidthField property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getFixedWidthField().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link FixedWidthField }
             * 
             * 
             */
            public List<FixedWidthField> getFixedWidthField() {
                if (fixedWidthField == null) {
                    fixedWidthField = new ArrayList<FixedWidthField>();
                }
                return this.fixedWidthField;
            }

            /**
             * Gets the value of the length property.
             * 
             * @return
             *     possible object is
             *     {@link BigInteger }
             *     
             */
            public BigInteger getLength() {
                return length;
            }

            /**
             * Sets the value of the length property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigInteger }
             *     
             */
            public void setLength(BigInteger value) {
                this.length = value;
            }

            /**
             * Gets the value of the mustEndWith property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getMustEndWith() {
                return mustEndWith;
            }

            /**
             * Sets the value of the mustEndWith property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setMustEndWith(String value) {
                this.mustEndWith = value;
            }

            /**
             * Gets the value of the mustStartWith property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getMustStartWith() {
                return mustStartWith;
            }

            /**
             * Sets the value of the mustStartWith property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setMustStartWith(String value) {
                this.mustStartWith = value;
            }

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
         *         &lt;element name="master">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence maxOccurs="unbounded">
         *                   &lt;element ref="{https://www.w3schools.com}FixedWidthField"/>
         *                 &lt;/sequence>
         *                 &lt;attribute name="mustStartWith" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                 &lt;attribute name="mustEndWith" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                 &lt;attribute name="length" use="required" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" />
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="detail">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence maxOccurs="unbounded">
         *                   &lt;element ref="{https://www.w3schools.com}FixedWidthField"/>
         *                 &lt;/sequence>
         *                 &lt;attribute name="mustStartWith" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                 &lt;attribute name="mustEndWith" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                 &lt;attribute name="length" use="required" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" />
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
            "master",
            "detail"
        })
        public static class MasterDetail {

            @XmlElement(namespace = "https://www.w3schools.com", required = true)
            protected LayoutFile.FixedWidthLayout.MasterDetail.Master master;
            @XmlElement(namespace = "https://www.w3schools.com", required = true)
            protected LayoutFile.FixedWidthLayout.MasterDetail.Detail detail;

            /**
             * Gets the value of the master property.
             * 
             * @return
             *     possible object is
             *     {@link LayoutFile.FixedWidthLayout.MasterDetail.Master }
             *     
             */
            public LayoutFile.FixedWidthLayout.MasterDetail.Master getMaster() {
                return master;
            }

            /**
             * Sets the value of the master property.
             * 
             * @param value
             *     allowed object is
             *     {@link LayoutFile.FixedWidthLayout.MasterDetail.Master }
             *     
             */
            public void setMaster(LayoutFile.FixedWidthLayout.MasterDetail.Master value) {
                this.master = value;
            }

            /**
             * Gets the value of the detail property.
             * 
             * @return
             *     possible object is
             *     {@link LayoutFile.FixedWidthLayout.MasterDetail.Detail }
             *     
             */
            public LayoutFile.FixedWidthLayout.MasterDetail.Detail getDetail() {
                return detail;
            }

            /**
             * Sets the value of the detail property.
             * 
             * @param value
             *     allowed object is
             *     {@link LayoutFile.FixedWidthLayout.MasterDetail.Detail }
             *     
             */
            public void setDetail(LayoutFile.FixedWidthLayout.MasterDetail.Detail value) {
                this.detail = value;
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
             *       &lt;sequence maxOccurs="unbounded">
             *         &lt;element ref="{https://www.w3schools.com}FixedWidthField"/>
             *       &lt;/sequence>
             *       &lt;attribute name="mustStartWith" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
             *       &lt;attribute name="mustEndWith" type="{http://www.w3.org/2001/XMLSchema}string" />
             *       &lt;attribute name="length" use="required" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" />
             *     &lt;/restriction>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "fixedWidthField"
            })
            public static class Detail {

                @XmlElement(name = "FixedWidthField", namespace = "https://www.w3schools.com", required = true)
                protected List<FixedWidthField> fixedWidthField;
                @XmlAttribute(name = "mustStartWith", required = true)
                protected String mustStartWith;
                @XmlAttribute(name = "mustEndWith")
                protected String mustEndWith;
                @XmlAttribute(name = "length", required = true)
                @XmlSchemaType(name = "positiveInteger")
                protected BigInteger length;

                /**
                 * Gets the value of the fixedWidthField property.
                 * 
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the fixedWidthField property.
                 * 
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getFixedWidthField().add(newItem);
                 * </pre>
                 * 
                 * 
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link FixedWidthField }
                 * 
                 * 
                 */
                public List<FixedWidthField> getFixedWidthField() {
                    if (fixedWidthField == null) {
                        fixedWidthField = new ArrayList<FixedWidthField>();
                    }
                    return this.fixedWidthField;
                }

                /**
                 * Gets the value of the mustStartWith property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getMustStartWith() {
                    return mustStartWith;
                }

                /**
                 * Sets the value of the mustStartWith property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setMustStartWith(String value) {
                    this.mustStartWith = value;
                }

                /**
                 * Gets the value of the mustEndWith property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getMustEndWith() {
                    return mustEndWith;
                }

                /**
                 * Sets the value of the mustEndWith property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setMustEndWith(String value) {
                    this.mustEndWith = value;
                }

                /**
                 * Gets the value of the length property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link BigInteger }
                 *     
                 */
                public BigInteger getLength() {
                    return length;
                }

                /**
                 * Sets the value of the length property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link BigInteger }
                 *     
                 */
                public void setLength(BigInteger value) {
                    this.length = value;
                }

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
             *       &lt;sequence maxOccurs="unbounded">
             *         &lt;element ref="{https://www.w3schools.com}FixedWidthField"/>
             *       &lt;/sequence>
             *       &lt;attribute name="mustStartWith" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
             *       &lt;attribute name="mustEndWith" type="{http://www.w3.org/2001/XMLSchema}string" />
             *       &lt;attribute name="length" use="required" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" />
             *     &lt;/restriction>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "fixedWidthField"
            })
            public static class Master {

                @XmlElement(name = "FixedWidthField", namespace = "https://www.w3schools.com", required = true)
                protected List<FixedWidthField> fixedWidthField;
                @XmlAttribute(name = "mustStartWith", required = true)
                protected String mustStartWith;
                @XmlAttribute(name = "mustEndWith")
                protected String mustEndWith;
                @XmlAttribute(name = "length", required = true)
                @XmlSchemaType(name = "positiveInteger")
                protected BigInteger length;

                /**
                 * Gets the value of the fixedWidthField property.
                 * 
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the fixedWidthField property.
                 * 
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getFixedWidthField().add(newItem);
                 * </pre>
                 * 
                 * 
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link FixedWidthField }
                 * 
                 * 
                 */
                public List<FixedWidthField> getFixedWidthField() {
                    if (fixedWidthField == null) {
                        fixedWidthField = new ArrayList<FixedWidthField>();
                    }
                    return this.fixedWidthField;
                }

                /**
                 * Gets the value of the mustStartWith property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getMustStartWith() {
                    return mustStartWith;
                }

                /**
                 * Sets the value of the mustStartWith property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setMustStartWith(String value) {
                    this.mustStartWith = value;
                }

                /**
                 * Gets the value of the mustEndWith property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getMustEndWith() {
                    return mustEndWith;
                }

                /**
                 * Sets the value of the mustEndWith property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setMustEndWith(String value) {
                    this.mustEndWith = value;
                }

                /**
                 * Gets the value of the length property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link BigInteger }
                 *     
                 */
                public BigInteger getLength() {
                    return length;
                }

                /**
                 * Sets the value of the length property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link BigInteger }
                 *     
                 */
                public void setLength(BigInteger value) {
                    this.length = value;
                }

            }

        }

    }

}
