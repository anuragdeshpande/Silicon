
package framework.integrations.ftp.layoutReader.layout;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FieldTypes.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="FieldTypes">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ALPHA"/>
 *     &lt;enumeration value="NUMERIC"/>
 *     &lt;enumeration value="ALPHA_NUMERIC"/>
 *     &lt;enumeration value="BLANK"/>
 *     &lt;enumeration value="DATE"/>
 *     &lt;enumeration value="STRING"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "FieldTypes", namespace = "https://www.w3schools.com")
@XmlEnum
public enum FieldTypes {

    ALPHA,
    NUMERIC,
    ALPHA_NUMERIC,
    BLANK,
    DATE,
    STRING;

    public String value() {
        return name();
    }

    public static FieldTypes fromValue(String v) {
        return valueOf(v);
    }

}
