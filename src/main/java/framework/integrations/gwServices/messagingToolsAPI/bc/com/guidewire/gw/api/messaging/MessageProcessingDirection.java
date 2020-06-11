
package framework.integrations.gwServices.messagingToolsAPI.bc.com.guidewire.gw.api.messaging;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MessageProcessingDirection.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="MessageProcessingDirection">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="inbound"/>
 *     &lt;enumeration value="outbound"/>
 *     &lt;enumeration value="both"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "MessageProcessingDirection")
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
        throw new IllegalArgumentException(v);
    }

}
