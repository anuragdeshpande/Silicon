
package framework.integrations.gwServices.debugToolsAPI.bc;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the framework.integrations.gwServices.debugToolsAPI.bc package. 
 * &lt;p&gt;An ObjectFactory allows you to programatically 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: framework.integrations.gwServices.debugToolsAPI.bc
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
     * Create an instance of {@link GetCurrentDate }
     * 
     */
    public GetCurrentDate createGetCurrentDate() {
        return new GetCurrentDate();
    }

    /**
     * Create an instance of {@link GetCurrentDateResponse }
     * 
     */
    public GetCurrentDateResponse createGetCurrentDateResponse() {
        return new GetCurrentDateResponse();
    }

    /**
     * Create an instance of {@link RunInvoice }
     * 
     */
    public RunInvoice createRunInvoice() {
        return new RunInvoice();
    }

    /**
     * Create an instance of {@link RunInvoiceResponse }
     * 
     */
    public RunInvoiceResponse createRunInvoiceResponse() {
        return new RunInvoiceResponse();
    }

    /**
     * Create an instance of {@link IsInvoiceRunning }
     * 
     */
    public IsInvoiceRunning createIsInvoiceRunning() {
        return new IsInvoiceRunning();
    }

    /**
     * Create an instance of {@link IsInvoiceRunningResponse }
     * 
     */
    public IsInvoiceRunningResponse createIsInvoiceRunningResponse() {
        return new IsInvoiceRunningResponse();
    }

    /**
     * Create an instance of {@link RunInvoiceDue }
     * 
     */
    public RunInvoiceDue createRunInvoiceDue() {
        return new RunInvoiceDue();
    }

    /**
     * Create an instance of {@link RunInvoiceDueResponse }
     * 
     */
    public RunInvoiceDueResponse createRunInvoiceDueResponse() {
        return new RunInvoiceDueResponse();
    }

    /**
     * Create an instance of {@link IsInvoiceDueRunning }
     * 
     */
    public IsInvoiceDueRunning createIsInvoiceDueRunning() {
        return new IsInvoiceDueRunning();
    }

    /**
     * Create an instance of {@link IsInvoiceDueRunningResponse }
     * 
     */
    public IsInvoiceDueRunningResponse createIsInvoiceDueRunningResponse() {
        return new IsInvoiceDueRunningResponse();
    }

    /**
     * Create an instance of {@link RunPaymentRequest }
     * 
     */
    public RunPaymentRequest createRunPaymentRequest() {
        return new RunPaymentRequest();
    }

    /**
     * Create an instance of {@link RunPaymentRequestResponse }
     * 
     */
    public RunPaymentRequestResponse createRunPaymentRequestResponse() {
        return new RunPaymentRequestResponse();
    }

    /**
     * Create an instance of {@link IsPaymentRequestRunning }
     * 
     */
    public IsPaymentRequestRunning createIsPaymentRequestRunning() {
        return new IsPaymentRequestRunning();
    }

    /**
     * Create an instance of {@link IsPaymentRequestRunningResponse }
     * 
     */
    public IsPaymentRequestRunningResponse createIsPaymentRequestRunningResponse() {
        return new IsPaymentRequestRunningResponse();
    }

    /**
     * Create an instance of {@link RunWorkflow }
     * 
     */
    public RunWorkflow createRunWorkflow() {
        return new RunWorkflow();
    }

    /**
     * Create an instance of {@link RunWorkflowResponse }
     * 
     */
    public RunWorkflowResponse createRunWorkflowResponse() {
        return new RunWorkflowResponse();
    }

    /**
     * Create an instance of {@link IsWorkflowRunning }
     * 
     */
    public IsWorkflowRunning createIsWorkflowRunning() {
        return new IsWorkflowRunning();
    }

    /**
     * Create an instance of {@link IsWorkflowRunningResponse }
     * 
     */
    public IsWorkflowRunningResponse createIsWorkflowRunningResponse() {
        return new IsWorkflowRunningResponse();
    }

    /**
     * Create an instance of {@link RunReceivableAging }
     * 
     */
    public RunReceivableAging createRunReceivableAging() {
        return new RunReceivableAging();
    }

    /**
     * Create an instance of {@link RunReceivableAgingResponse }
     * 
     */
    public RunReceivableAgingResponse createRunReceivableAgingResponse() {
        return new RunReceivableAgingResponse();
    }

    /**
     * Create an instance of {@link IsReceivableAgingRunning }
     * 
     */
    public IsReceivableAgingRunning createIsReceivableAgingRunning() {
        return new IsReceivableAgingRunning();
    }

    /**
     * Create an instance of {@link IsReceivableAgingRunningResponse }
     * 
     */
    public IsReceivableAgingRunningResponse createIsReceivableAgingRunningResponse() {
        return new IsReceivableAgingRunningResponse();
    }

    /**
     * Create an instance of {@link RunCaptureDailyBalances }
     * 
     */
    public RunCaptureDailyBalances createRunCaptureDailyBalances() {
        return new RunCaptureDailyBalances();
    }

    /**
     * Create an instance of {@link RunCaptureDailyBalancesResponse }
     * 
     */
    public RunCaptureDailyBalancesResponse createRunCaptureDailyBalancesResponse() {
        return new RunCaptureDailyBalancesResponse();
    }

    /**
     * Create an instance of {@link IsCaptureDailyBalancesRunning }
     * 
     */
    public IsCaptureDailyBalancesRunning createIsCaptureDailyBalancesRunning() {
        return new IsCaptureDailyBalancesRunning();
    }

    /**
     * Create an instance of {@link IsCaptureDailyBalancesRunningResponse }
     * 
     */
    public IsCaptureDailyBalancesRunningResponse createIsCaptureDailyBalancesRunningResponse() {
        return new IsCaptureDailyBalancesRunningResponse();
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
