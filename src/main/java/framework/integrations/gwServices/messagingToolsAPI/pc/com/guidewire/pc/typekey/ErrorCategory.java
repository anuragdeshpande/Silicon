
package framework.integrations.gwServices.messagingToolsAPI.pc.com.guidewire.pc.typekey;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ErrorCategory.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ErrorCategory">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="contact_error"/>
 *     &lt;enumeration value="contact_unsynced"/>
 *     &lt;enumeration value="system_error"/>
 *     &lt;enumeration value="system_timeout"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ErrorCategory", namespace = "http://guidewire.com/pc/typekey")
@XmlEnum
public enum ErrorCategory {

    @XmlEnumValue("contact_error")
    CONTACT_ERROR("contact_error"),
    @XmlEnumValue("contact_unsynced")
    CONTACT_UNSYNCED("contact_unsynced"),
    @XmlEnumValue("system_error")
    SYSTEM_ERROR("system_error"),
    @XmlEnumValue("system_timeout")
    SYSTEM_TIMEOUT("system_timeout");
    private final String value;

    ErrorCategory(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ErrorCategory fromValue(String v) {
        for (ErrorCategory c: ErrorCategory.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
