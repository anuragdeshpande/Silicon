package framework.reports.models.junit;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the framework.reports.models package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _SystemErr_QNAME = new QName("", "system-err");
    private final static QName _Skipped_QNAME = new QName("", "skipped");
    private final static QName _SystemOut_QNAME = new QName("", "system-out");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: framework.reports.models
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Testsuite }
     */
    public Testsuite createTestsuite() {
        return new Testsuite();
    }

    /**
     * Create an instance of {@link Properties }
     */
    public Properties createProperties() {
        return new Properties();
    }

    /**
     * Create an instance of {@link Property }
     */
    public Property createProperty() {
        return new Property();
    }

    /**
     * Create an instance of {@link Testcase }
     */
    public Testcase createTestcase() {
        return new Testcase();
    }

    /**
     * Create an instance of {@link Error }
     */
    public Error createError() {
        return new Error();
    }

    /**
     * Create an instance of {@link Failure }
     */
    public Failure createFailure() {
        return new Failure();
    }

    /**
     * Create an instance of {@link Testsuites }
     */
    public Testsuites createTestsuites() {
        return new Testsuites();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     */
    @XmlElementDecl(namespace = "", name = "system-err")
    public JAXBElement<String> createSystemErr(final String value) {
        return new JAXBElement<String>(_SystemErr_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     */
    @XmlElementDecl(namespace = "", name = "skipped")
    public JAXBElement<String> createSkipped(final String value) {
        return new JAXBElement<String>(_Skipped_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     */
    @XmlElementDecl(namespace = "", name = "system-out")
    public JAXBElement<String> createSystemOut(final String value) {
        return new JAXBElement<String>(_SystemOut_QNAME, String.class, null, value);
    }

}
