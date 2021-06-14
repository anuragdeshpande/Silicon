
package framework.integrations.gwServices.messagingToolsAPI.pc;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the framework.integrations.gwServices.messagingToolsAPI.pc package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: framework.integrations.gwServices.messagingToolsAPI.pc
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link framework.integrations.gwServices.messagingToolsAPI.pc.FieldChanges }
     * 
     */
    public framework.integrations.gwServices.messagingToolsAPI.pc.FieldChanges createFieldChanges() {
        return new framework.integrations.gwServices.messagingToolsAPI.pc.FieldChanges();
    }

    /**
     * Create an instance of {@link framework.integrations.gwServices.messagingToolsAPI.pc.CustomEvents }
     * 
     */
    public framework.integrations.gwServices.messagingToolsAPI.pc.CustomEvents createCustomEvents() {
        return new framework.integrations.gwServices.messagingToolsAPI.pc.CustomEvents();
    }

    /**
     * Create an instance of {@link Acknowledgement }
     * 
     */
    public Acknowledgement createAcknowledgement() {
        return new Acknowledgement();
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
     * Create an instance of {@link PurgeCompletedMessages }
     * 
     */
    public PurgeCompletedMessages createPurgeCompletedMessages() {
        return new PurgeCompletedMessages();
    }

    /**
     * Create an instance of {@link PurgeCompletedMessagesResponse }
     * 
     */
    public PurgeCompletedMessagesResponse createPurgeCompletedMessagesResponse() {
        return new PurgeCompletedMessagesResponse();
    }

    /**
     * Create an instance of {@link WsiAuthenticationException }
     * 
     */
    public WsiAuthenticationException createWsiAuthenticationException() {
        return new WsiAuthenticationException();
    }

    /**
     * Create an instance of {@link RequiredFieldException }
     * 
     */
    public RequiredFieldException createRequiredFieldException() {
        return new RequiredFieldException();
    }

    /**
     * Create an instance of {@link SuspendDestinationBothDirections }
     * 
     */
    public SuspendDestinationBothDirections createSuspendDestinationBothDirections() {
        return new SuspendDestinationBothDirections();
    }

    /**
     * Create an instance of {@link SuspendDestinationBothDirectionsResponse }
     * 
     */
    public SuspendDestinationBothDirectionsResponse createSuspendDestinationBothDirectionsResponse() {
        return new SuspendDestinationBothDirectionsResponse();
    }

    /**
     * Create an instance of {@link IllegalArgumentException }
     * 
     */
    public IllegalArgumentException createIllegalArgumentException() {
        return new IllegalArgumentException();
    }

    /**
     * Create an instance of {@link ResumeDestinationBothDirections }
     * 
     */
    public ResumeDestinationBothDirections createResumeDestinationBothDirections() {
        return new ResumeDestinationBothDirections();
    }

    /**
     * Create an instance of {@link ResumeDestinationBothDirectionsResponse }
     * 
     */
    public ResumeDestinationBothDirectionsResponse createResumeDestinationBothDirectionsResponse() {
        return new ResumeDestinationBothDirectionsResponse();
    }

    /**
     * Create an instance of {@link ConfigureDestination }
     * 
     */
    public ConfigureDestination createConfigureDestination() {
        return new ConfigureDestination();
    }

    /**
     * Create an instance of {@link ConfigureDestinationResponse }
     * 
     */
    public ConfigureDestinationResponse createConfigureDestinationResponse() {
        return new ConfigureDestinationResponse();
    }

    /**
     * Create an instance of {@link GetConfiguration }
     * 
     */
    public GetConfiguration createGetConfiguration() {
        return new GetConfiguration();
    }

    /**
     * Create an instance of {@link GetConfigurationResponse }
     * 
     */
    public GetConfigurationResponse createGetConfigurationResponse() {
        return new GetConfigurationResponse();
    }

    /**
     * Create an instance of {@link ExternalDestinationConfig }
     * 
     */
    public ExternalDestinationConfig createExternalDestinationConfig() {
        return new ExternalDestinationConfig();
    }

    /**
     * Create an instance of {@link GetMessageIdBySenderRefId }
     * 
     */
    public GetMessageIdBySenderRefId createGetMessageIdBySenderRefId() {
        return new GetMessageIdBySenderRefId();
    }

    /**
     * Create an instance of {@link GetMessageIdBySenderRefIdResponse }
     * 
     */
    public GetMessageIdBySenderRefIdResponse createGetMessageIdBySenderRefIdResponse() {
        return new GetMessageIdBySenderRefIdResponse();
    }

    /**
     * Create an instance of {@link SOAPSenderException }
     * 
     */
    public SOAPSenderException createSOAPSenderException() {
        return new SOAPSenderException();
    }

    /**
     * Create an instance of {@link AckMessage }
     * 
     */
    public AckMessage createAckMessage() {
        return new AckMessage();
    }

    /**
     * Create an instance of {@link AckMessageResponse }
     * 
     */
    public AckMessageResponse createAckMessageResponse() {
        return new AckMessageResponse();
    }

    /**
     * Create an instance of {@link SOAPException }
     * 
     */
    public SOAPException createSOAPException() {
        return new SOAPException();
    }

    /**
     * Create an instance of {@link RetryMessage }
     * 
     */
    public RetryMessage createRetryMessage() {
        return new RetryMessage();
    }

    /**
     * Create an instance of {@link RetryMessageResponse }
     * 
     */
    public RetryMessageResponse createRetryMessageResponse() {
        return new RetryMessageResponse();
    }

    /**
     * Create an instance of {@link SkipMessage }
     * 
     */
    public SkipMessage createSkipMessage() {
        return new SkipMessage();
    }

    /**
     * Create an instance of {@link SkipMessageResponse }
     * 
     */
    public SkipMessageResponse createSkipMessageResponse() {
        return new SkipMessageResponse();
    }

    /**
     * Create an instance of {@link RetryRetryableErrorMessages }
     * 
     */
    public RetryRetryableErrorMessages createRetryRetryableErrorMessages() {
        return new RetryRetryableErrorMessages();
    }

    /**
     * Create an instance of {@link RetryRetryableErrorMessagesResponse }
     * 
     */
    public RetryRetryableErrorMessagesResponse createRetryRetryableErrorMessagesResponse() {
        return new RetryRetryableErrorMessagesResponse();
    }

    /**
     * Create an instance of {@link RetryRetryableErrorMessagesForCategory }
     * 
     */
    public RetryRetryableErrorMessagesForCategory createRetryRetryableErrorMessagesForCategory() {
        return new RetryRetryableErrorMessagesForCategory();
    }

    /**
     * Create an instance of {@link RetryRetryableErrorMessagesForCategoryResponse }
     * 
     */
    public RetryRetryableErrorMessagesForCategoryResponse createRetryRetryableErrorMessagesForCategoryResponse() {
        return new RetryRetryableErrorMessagesForCategoryResponse();
    }

    /**
     * Create an instance of {@link RetryRetryableSomeErrorMessages }
     * 
     */
    public RetryRetryableSomeErrorMessages createRetryRetryableSomeErrorMessages() {
        return new RetryRetryableSomeErrorMessages();
    }

    /**
     * Create an instance of {@link RetryRetryableSomeErrorMessagesResponse }
     * 
     */
    public RetryRetryableSomeErrorMessagesResponse createRetryRetryableSomeErrorMessagesResponse() {
        return new RetryRetryableSomeErrorMessagesResponse();
    }

    /**
     * Create an instance of {@link GetMessageStatisticsForSafeOrderedObject }
     * 
     */
    public GetMessageStatisticsForSafeOrderedObject createGetMessageStatisticsForSafeOrderedObject() {
        return new GetMessageStatisticsForSafeOrderedObject();
    }

    /**
     * Create an instance of {@link GetMessageStatisticsForSafeOrderedObjectResponse }
     * 
     */
    public GetMessageStatisticsForSafeOrderedObjectResponse createGetMessageStatisticsForSafeOrderedObjectResponse() {
        return new GetMessageStatisticsForSafeOrderedObjectResponse();
    }

    /**
     * Create an instance of {@link MessageStatisticsData }
     * 
     */
    public MessageStatisticsData createMessageStatisticsData() {
        return new MessageStatisticsData();
    }

    /**
     * Create an instance of {@link GetTotalStatistics }
     * 
     */
    public GetTotalStatistics createGetTotalStatistics() {
        return new GetTotalStatistics();
    }

    /**
     * Create an instance of {@link GetTotalStatisticsResponse }
     * 
     */
    public GetTotalStatisticsResponse createGetTotalStatisticsResponse() {
        return new GetTotalStatisticsResponse();
    }

    /**
     * Create an instance of {@link SuspendDestination }
     * 
     */
    public SuspendDestination createSuspendDestination() {
        return new SuspendDestination();
    }

    /**
     * Create an instance of {@link SuspendDestinationResponse }
     * 
     */
    public SuspendDestinationResponse createSuspendDestinationResponse() {
        return new SuspendDestinationResponse();
    }

    /**
     * Create an instance of {@link ResumeDestination }
     * 
     */
    public ResumeDestination createResumeDestination() {
        return new ResumeDestination();
    }

    /**
     * Create an instance of {@link ResumeDestinationResponse }
     * 
     */
    public ResumeDestinationResponse createResumeDestinationResponse() {
        return new ResumeDestinationResponse();
    }

    /**
     * Create an instance of {@link IsSuspended }
     * 
     */
    public IsSuspended createIsSuspended() {
        return new IsSuspended();
    }

    /**
     * Create an instance of {@link IsSuspendedResponse }
     * 
     */
    public IsSuspendedResponse createIsSuspendedResponse() {
        return new IsSuspendedResponse();
    }

    /**
     * Create an instance of {@link IsResumed }
     * 
     */
    public IsResumed createIsResumed() {
        return new IsResumed();
    }

    /**
     * Create an instance of {@link IsResumedResponse }
     * 
     */
    public IsResumedResponse createIsResumedResponse() {
        return new IsResumedResponse();
    }

    /**
     * Create an instance of {@link GetDestinationStatus }
     * 
     */
    public GetDestinationStatus createGetDestinationStatus() {
        return new GetDestinationStatus();
    }

    /**
     * Create an instance of {@link GetDestinationStatusResponse }
     * 
     */
    public GetDestinationStatusResponse createGetDestinationStatusResponse() {
        return new GetDestinationStatusResponse();
    }

    /**
     * Create an instance of {@link ResyncAccount }
     * 
     */
    public ResyncAccount createResyncAccount() {
        return new ResyncAccount();
    }

    /**
     * Create an instance of {@link ResyncAccountResponse }
     * 
     */
    public ResyncAccountResponse createResyncAccountResponse() {
        return new ResyncAccountResponse();
    }

    /**
     * Create an instance of {@link BadIdentifierException }
     * 
     */
    public BadIdentifierException createBadIdentifierException() {
        return new BadIdentifierException();
    }

    /**
     * Create an instance of {@link FieldChangeValue }
     * 
     */
    public FieldChangeValue createFieldChangeValue() {
        return new FieldChangeValue();
    }

    /**
     * Create an instance of {@link framework.integrations.gwServices.messagingToolsAPI.pc.FieldChanges.FieldValues }
     * 
     */
    public framework.integrations.gwServices.messagingToolsAPI.pc.FieldChanges.FieldValues createFieldChangesFieldValues() {
        return new framework.integrations.gwServices.messagingToolsAPI.pc.FieldChanges.FieldValues();
    }

    /**
     * Create an instance of {@link framework.integrations.gwServices.messagingToolsAPI.pc.CustomEvents.Events }
     * 
     */
    public framework.integrations.gwServices.messagingToolsAPI.pc.CustomEvents.Events createCustomEventsEvents() {
        return new framework.integrations.gwServices.messagingToolsAPI.pc.CustomEvents.Events();
    }

    /**
     * Create an instance of {@link Acknowledgement.CustomEvents }
     * 
     */
    public Acknowledgement.CustomEvents createAcknowledgementCustomEvents() {
        return new Acknowledgement.CustomEvents();
    }

    /**
     * Create an instance of {@link Acknowledgement.FieldChanges }
     * 
     */
    public Acknowledgement.FieldChanges createAcknowledgementFieldChanges() {
        return new Acknowledgement.FieldChanges();
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
