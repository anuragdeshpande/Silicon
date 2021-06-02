
package framework.integrations.gwServices.debugToolsAPI.pc;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the framework.integrations.gwServices.debugToolsAPI.pc package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: framework.integrations.gwServices.debugToolsAPI.pc
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
     * Create an instance of {@link RunWorkFlow }
     * 
     */
    public RunWorkFlow createRunWorkFlow() {
        return new RunWorkFlow();
    }

    /**
     * Create an instance of {@link RunWorkFlowResponse }
     * 
     */
    public RunWorkFlowResponse createRunWorkFlowResponse() {
        return new RunWorkFlowResponse();
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
     * Create an instance of {@link RunRenewal }
     * 
     */
    public RunRenewal createRunRenewal() {
        return new RunRenewal();
    }

    /**
     * Create an instance of {@link RunRenewalResponse }
     * 
     */
    public RunRenewalResponse createRunRenewalResponse() {
        return new RunRenewalResponse();
    }

    /**
     * Create an instance of {@link IsRenewalRunning }
     * 
     */
    public IsRenewalRunning createIsRenewalRunning() {
        return new IsRenewalRunning();
    }

    /**
     * Create an instance of {@link IsRenewalRunningResponse }
     * 
     */
    public IsRenewalRunningResponse createIsRenewalRunningResponse() {
        return new IsRenewalRunningResponse();
    }

    /**
     * Create an instance of {@link RunPrintDocuments }
     * 
     */
    public RunPrintDocuments createRunPrintDocuments() {
        return new RunPrintDocuments();
    }

    /**
     * Create an instance of {@link RunPrintDocumentsResponse }
     * 
     */
    public RunPrintDocumentsResponse createRunPrintDocumentsResponse() {
        return new RunPrintDocumentsResponse();
    }

    /**
     * Create an instance of {@link IsPrintDocumentsRunning }
     * 
     */
    public IsPrintDocumentsRunning createIsPrintDocumentsRunning() {
        return new IsPrintDocumentsRunning();
    }

    /**
     * Create an instance of {@link IsPrintDocumentsRunningResponse }
     * 
     */
    public IsPrintDocumentsRunningResponse createIsPrintDocumentsRunningResponse() {
        return new IsPrintDocumentsRunningResponse();
    }

    /**
     * Create an instance of {@link RunMbreMonthly }
     * 
     */
    public RunMbreMonthly createRunMbreMonthly() {
        return new RunMbreMonthly();
    }

    /**
     * Create an instance of {@link RunMbreMonthlyResponse }
     * 
     */
    public RunMbreMonthlyResponse createRunMbreMonthlyResponse() {
        return new RunMbreMonthlyResponse();
    }

    /**
     * Create an instance of {@link IsMbreMonthlyRunning }
     * 
     */
    public IsMbreMonthlyRunning createIsMbreMonthlyRunning() {
        return new IsMbreMonthlyRunning();
    }

    /**
     * Create an instance of {@link IsMbreMonthlyRunningResponse }
     * 
     */
    public IsMbreMonthlyRunningResponse createIsMbreMonthlyRunningResponse() {
        return new IsMbreMonthlyRunningResponse();
    }

    /**
     * Create an instance of {@link RunMbreQuarterly }
     * 
     */
    public RunMbreQuarterly createRunMbreQuarterly() {
        return new RunMbreQuarterly();
    }

    /**
     * Create an instance of {@link RunMbreQuarterlyResponse }
     * 
     */
    public RunMbreQuarterlyResponse createRunMbreQuarterlyResponse() {
        return new RunMbreQuarterlyResponse();
    }

    /**
     * Create an instance of {@link IsMbreQuarterlyRunning }
     * 
     */
    public IsMbreQuarterlyRunning createIsMbreQuarterlyRunning() {
        return new IsMbreQuarterlyRunning();
    }

    /**
     * Create an instance of {@link IsMbreQuarterlyRunningResponse }
     * 
     */
    public IsMbreQuarterlyRunningResponse createIsMbreQuarterlyRunningResponse() {
        return new IsMbreQuarterlyRunningResponse();
    }

    /**
     * Create an instance of {@link RunCountyOfficeUpdate }
     * 
     */
    public RunCountyOfficeUpdate createRunCountyOfficeUpdate() {
        return new RunCountyOfficeUpdate();
    }

    /**
     * Create an instance of {@link RunCountyOfficeUpdateResponse }
     * 
     */
    public RunCountyOfficeUpdateResponse createRunCountyOfficeUpdateResponse() {
        return new RunCountyOfficeUpdateResponse();
    }

    /**
     * Create an instance of {@link IsCountyOfficeUpdateRunning }
     * 
     */
    public IsCountyOfficeUpdateRunning createIsCountyOfficeUpdateRunning() {
        return new IsCountyOfficeUpdateRunning();
    }

    /**
     * Create an instance of {@link IsCountyOfficeUpdateRunningResponse }
     * 
     */
    public IsCountyOfficeUpdateRunningResponse createIsCountyOfficeUpdateRunningResponse() {
        return new IsCountyOfficeUpdateRunningResponse();
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
