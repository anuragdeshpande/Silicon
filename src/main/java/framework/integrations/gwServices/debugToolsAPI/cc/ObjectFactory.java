
package framework.integrations.gwServices.debugToolsAPI.cc;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the framework.integrations.gwServices.debugToolsAPI.cc package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Authentication_QNAME = new QName("http://guidewire.com/ws/soapheaders", "authentication");
    private final static QName _Locale_QNAME = new QName("http://guidewire.com/ws/soapheaders", "locale");
    private final static QName _TransactionId_QNAME = new QName("http://guidewire.com/ws/soapheaders", "transaction_id");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: framework.integrations.gwServices.debugToolsAPI.cc
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Authentication }
     * 
     */
    public Authentication createAuthentication() {
        return new Authentication();
    }

    /**
     * Create an instance of {@link Locale }
     * 
     */
    public Locale createLocale() {
        return new Locale();
    }

    /**
     * Create an instance of {@link TransactionId }
     * 
     */
    public TransactionId createTransactionId() {
        return new TransactionId();
    }

    /**
     * Create an instance of {@link MoveClockTo }
     * 
     */
    public MoveClockTo createMoveClockTo() {
        return new MoveClockTo();
    }

    /**
     * Create an instance of {@link MoveClockToResponse }
     * 
     */
    public MoveClockToResponse createMoveClockToResponse() {
        return new MoveClockToResponse();
    }

    /**
     * Create an instance of {@link WsiAuthenticationException }
     * 
     */
    public WsiAuthenticationException createWsiAuthenticationException() {
        return new WsiAuthenticationException();
    }

    /**
     * Create an instance of {@link AddDays }
     * 
     */
    public AddDays createAddDays() {
        return new AddDays();
    }

    /**
     * Create an instance of {@link AddDaysResponse }
     * 
     */
    public AddDaysResponse createAddDaysResponse() {
        return new AddDaysResponse();
    }

    /**
     * Create an instance of {@link AddMonths }
     * 
     */
    public AddMonths createAddMonths() {
        return new AddMonths();
    }

    /**
     * Create an instance of {@link AddMonthsResponse }
     * 
     */
    public AddMonthsResponse createAddMonthsResponse() {
        return new AddMonthsResponse();
    }

    /**
     * Create an instance of {@link GetCurrentTime }
     * 
     */
    public GetCurrentTime createGetCurrentTime() {
        return new GetCurrentTime();
    }

    /**
     * Create an instance of {@link GetCurrentTimeResponse }
     * 
     */
    public GetCurrentTimeResponse createGetCurrentTimeResponse() {
        return new GetCurrentTimeResponse();
    }

    /**
     * Create an instance of {@link RunFinancialEsc }
     * 
     */
    public RunFinancialEsc createRunFinancialEsc() {
        return new RunFinancialEsc();
    }

    /**
     * Create an instance of {@link RunFinancialEscResponse }
     * 
     */
    public RunFinancialEscResponse createRunFinancialEscResponse() {
        return new RunFinancialEscResponse();
    }

    /**
     * Create an instance of {@link IsFinancialEscRunning }
     * 
     */
    public IsFinancialEscRunning createIsFinancialEscRunning() {
        return new IsFinancialEscRunning();
    }

    /**
     * Create an instance of {@link IsFinancialEscRunningResponse }
     * 
     */
    public IsFinancialEscRunningResponse createIsFinancialEscRunningResponse() {
        return new IsFinancialEscRunningResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Authentication }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Authentication }{@code >}
     */
    @XmlElementDecl(namespace = "http://guidewire.com/ws/soapheaders", name = "authentication")
    public JAXBElement<Authentication> createAuthentication(Authentication value) {
        return new JAXBElement<Authentication>(_Authentication_QNAME, Authentication.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Locale }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Locale }{@code >}
     */
    @XmlElementDecl(namespace = "http://guidewire.com/ws/soapheaders", name = "locale")
    public JAXBElement<Locale> createLocale(Locale value) {
        return new JAXBElement<Locale>(_Locale_QNAME, Locale.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TransactionId }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link TransactionId }{@code >}
     */
    @XmlElementDecl(namespace = "http://guidewire.com/ws/soapheaders", name = "transaction_id")
    public JAXBElement<TransactionId> createTransactionId(TransactionId value) {
        return new JAXBElement<TransactionId>(_TransactionId_QNAME, TransactionId.class, null, value);
    }

}
