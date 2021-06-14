
package framework.integrations.gwServices.messagingToolsAPI.pc;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MessageProcessingDirection.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="MessageProcessingDirection"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="inbound"/&gt;
 *     &lt;enumeration value="outbound"/&gt;
 *     &lt;enumeration value="both"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "MessageProcessingDirection", namespace = "http://guidewire.com/gw/api/messaging")
@XmlEnum
public enum MessageProcessingDirection {

    @XmlEnumValue("inbound")
    INBOUND("inbound"),
    @XmlEnumValue("outbound")
    OUTBOUND("outbound"),
    @XmlEnumValue("both")
    BOTH("both");
    private final String value;

    MessageProcessingDirection(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static MessageProcessingDirection fromValue(String v) {
        for (MessageProcessingDirection c: MessageProcessingDirection.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new java.lang.IllegalArgumentException(v);
    }

}
