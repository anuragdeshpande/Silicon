
package framework.integrations.gwServices.maintenanceToolsAPI.ab;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the framework.integrations.gwServices.maintenanceToolsAPI.ab package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: framework.integrations.gwServices.maintenanceToolsAPI.ab
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetValidBatchProcessNamesResponse }
     * 
     */
    public GetValidBatchProcessNamesResponse createGetValidBatchProcessNamesResponse() {
        return new GetValidBatchProcessNamesResponse();
    }

    /**
     * Create an instance of {@link StartBatchProcessWithArguments }
     * 
     */
    public StartBatchProcessWithArguments createStartBatchProcessWithArguments() {
        return new StartBatchProcessWithArguments();
    }

    /**
     * Create an instance of {@link GetWorkQueueNamesResponse }
     * 
     */
    public GetWorkQueueNamesResponse createGetWorkQueueNamesResponse() {
        return new GetWorkQueueNamesResponse();
    }

    /**
     * Create an instance of {@link WQueueExecutorDetails }
     * 
     */
    public WQueueExecutorDetails createWQueueExecutorDetails() {
        return new WQueueExecutorDetails();
    }

    /**
     * Create an instance of {@link WQueueStatus }
     * 
     */
    public WQueueStatus createWQueueStatus() {
        return new WQueueStatus();
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
     * Create an instance of {@link GetValidBatchProcessNames }
     * 
     */
    public GetValidBatchProcessNames createGetValidBatchProcessNames() {
        return new GetValidBatchProcessNames();
    }

    /**
     * Create an instance of {@link GetValidBatchProcessNamesResponse.Return }
     * 
     */
    public GetValidBatchProcessNamesResponse.Return createGetValidBatchProcessNamesResponseReturn() {
        return new GetValidBatchProcessNamesResponse.Return();
    }

    /**
     * Create an instance of {@link WsiAuthenticationException }
     * 
     */
    public WsiAuthenticationException createWsiAuthenticationException() {
        return new WsiAuthenticationException();
    }

    /**
     * Create an instance of {@link IsBatchProcessNameValid }
     * 
     */
    public IsBatchProcessNameValid createIsBatchProcessNameValid() {
        return new IsBatchProcessNameValid();
    }

    /**
     * Create an instance of {@link IsBatchProcessNameValidResponse }
     * 
     */
    public IsBatchProcessNameValidResponse createIsBatchProcessNameValidResponse() {
        return new IsBatchProcessNameValidResponse();
    }

    /**
     * Create an instance of {@link StartBatchProcess }
     * 
     */
    public StartBatchProcess createStartBatchProcess() {
        return new StartBatchProcess();
    }

    /**
     * Create an instance of {@link StartBatchProcessResponse }
     * 
     */
    public StartBatchProcessResponse createStartBatchProcessResponse() {
        return new StartBatchProcessResponse();
    }

    /**
     * Create an instance of {@link ProcessID }
     * 
     */
    public ProcessID createProcessID() {
        return new ProcessID();
    }

    /**
     * Create an instance of {@link IllegalArgumentException }
     * 
     */
    public IllegalArgumentException createIllegalArgumentException() {
        return new IllegalArgumentException();
    }

    /**
     * Create an instance of {@link StartBatchProcessWithArguments.Arguments }
     * 
     */
    public StartBatchProcessWithArguments.Arguments createStartBatchProcessWithArgumentsArguments() {
        return new StartBatchProcessWithArguments.Arguments();
    }

    /**
     * Create an instance of {@link StartBatchProcessWithArgumentsResponse }
     * 
     */
    public StartBatchProcessWithArgumentsResponse createStartBatchProcessWithArgumentsResponse() {
        return new StartBatchProcessWithArgumentsResponse();
    }

    /**
     * Create an instance of {@link RequestTerminationOfBatchProcessByName }
     * 
     */
    public RequestTerminationOfBatchProcessByName createRequestTerminationOfBatchProcessByName() {
        return new RequestTerminationOfBatchProcessByName();
    }

    /**
     * Create an instance of {@link RequestTerminationOfBatchProcessByNameResponse }
     * 
     */
    public RequestTerminationOfBatchProcessByNameResponse createRequestTerminationOfBatchProcessByNameResponse() {
        return new RequestTerminationOfBatchProcessByNameResponse();
    }

    /**
     * Create an instance of {@link RequestTerminationOfBatchProcessByID }
     * 
     */
    public RequestTerminationOfBatchProcessByID createRequestTerminationOfBatchProcessByID() {
        return new RequestTerminationOfBatchProcessByID();
    }

    /**
     * Create an instance of {@link RequestTerminationOfBatchProcessByIDResponse }
     * 
     */
    public RequestTerminationOfBatchProcessByIDResponse createRequestTerminationOfBatchProcessByIDResponse() {
        return new RequestTerminationOfBatchProcessByIDResponse();
    }

    /**
     * Create an instance of {@link BatchProcessStatusByName }
     * 
     */
    public BatchProcessStatusByName createBatchProcessStatusByName() {
        return new BatchProcessStatusByName();
    }

    /**
     * Create an instance of {@link BatchProcessStatusByNameResponse }
     * 
     */
    public BatchProcessStatusByNameResponse createBatchProcessStatusByNameResponse() {
        return new BatchProcessStatusByNameResponse();
    }

    /**
     * Create an instance of {@link ProcessStatus }
     * 
     */
    public ProcessStatus createProcessStatus() {
        return new ProcessStatus();
    }

    /**
     * Create an instance of {@link BatchProcessStatusByID }
     * 
     */
    public BatchProcessStatusByID createBatchProcessStatusByID() {
        return new BatchProcessStatusByID();
    }

    /**
     * Create an instance of {@link BatchProcessStatusByIDResponse }
     * 
     */
    public BatchProcessStatusByIDResponse createBatchProcessStatusByIDResponse() {
        return new BatchProcessStatusByIDResponse();
    }

    /**
     * Create an instance of {@link GetWorkQueueConfig }
     * 
     */
    public GetWorkQueueConfig createGetWorkQueueConfig() {
        return new GetWorkQueueConfig();
    }

    /**
     * Create an instance of {@link GetWorkQueueConfigResponse }
     * 
     */
    public GetWorkQueueConfigResponse createGetWorkQueueConfigResponse() {
        return new GetWorkQueueConfigResponse();
    }

    /**
     * Create an instance of {@link WorkQueueConfig }
     * 
     */
    public WorkQueueConfig createWorkQueueConfig() {
        return new WorkQueueConfig();
    }

    /**
     * Create an instance of {@link SetWorkQueueConfig }
     * 
     */
    public SetWorkQueueConfig createSetWorkQueueConfig() {
        return new SetWorkQueueConfig();
    }

    /**
     * Create an instance of {@link SetWorkQueueConfigResponse }
     * 
     */
    public SetWorkQueueConfigResponse createSetWorkQueueConfigResponse() {
        return new SetWorkQueueConfigResponse();
    }

    /**
     * Create an instance of {@link GetWorkQueueNames }
     * 
     */
    public GetWorkQueueNames createGetWorkQueueNames() {
        return new GetWorkQueueNames();
    }

    /**
     * Create an instance of {@link GetWorkQueueNamesResponse.Return }
     * 
     */
    public GetWorkQueueNamesResponse.Return createGetWorkQueueNamesResponseReturn() {
        return new GetWorkQueueNamesResponse.Return();
    }

    /**
     * Create an instance of {@link NotifyQueueWorkers }
     * 
     */
    public NotifyQueueWorkers createNotifyQueueWorkers() {
        return new NotifyQueueWorkers();
    }

    /**
     * Create an instance of {@link NotifyQueueWorkersResponse }
     * 
     */
    public NotifyQueueWorkersResponse createNotifyQueueWorkersResponse() {
        return new NotifyQueueWorkersResponse();
    }

    /**
     * Create an instance of {@link StopWorkQueueWorkers }
     * 
     */
    public StopWorkQueueWorkers createStopWorkQueueWorkers() {
        return new StopWorkQueueWorkers();
    }

    /**
     * Create an instance of {@link StopWorkQueueWorkersResponse }
     * 
     */
    public StopWorkQueueWorkersResponse createStopWorkQueueWorkersResponse() {
        return new StopWorkQueueWorkersResponse();
    }

    /**
     * Create an instance of {@link StartWorkQueueWorkers }
     * 
     */
    public StartWorkQueueWorkers createStartWorkQueueWorkers() {
        return new StartWorkQueueWorkers();
    }

    /**
     * Create an instance of {@link StartWorkQueueWorkersResponse }
     * 
     */
    public StartWorkQueueWorkersResponse createStartWorkQueueWorkersResponse() {
        return new StartWorkQueueWorkersResponse();
    }

    /**
     * Create an instance of {@link GetWQueueStatus }
     * 
     */
    public GetWQueueStatus createGetWQueueStatus() {
        return new GetWQueueStatus();
    }

    /**
     * Create an instance of {@link GetWQueueStatusResponse }
     * 
     */
    public GetWQueueStatusResponse createGetWQueueStatusResponse() {
        return new GetWQueueStatusResponse();
    }

    /**
     * Create an instance of {@link GetNumActiveWorkItems }
     * 
     */
    public GetNumActiveWorkItems createGetNumActiveWorkItems() {
        return new GetNumActiveWorkItems();
    }

    /**
     * Create an instance of {@link GetNumActiveWorkItemsResponse }
     * 
     */
    public GetNumActiveWorkItemsResponse createGetNumActiveWorkItemsResponse() {
        return new GetNumActiveWorkItemsResponse();
    }

    /**
     * Create an instance of {@link WaitOnActiveWorkItems }
     * 
     */
    public WaitOnActiveWorkItems createWaitOnActiveWorkItems() {
        return new WaitOnActiveWorkItems();
    }

    /**
     * Create an instance of {@link WaitOnActiveWorkItemsResponse }
     * 
     */
    public WaitOnActiveWorkItemsResponse createWaitOnActiveWorkItemsResponse() {
        return new WaitOnActiveWorkItemsResponse();
    }

    /**
     * Create an instance of {@link IsPluginStarted }
     * 
     */
    public IsPluginStarted createIsPluginStarted() {
        return new IsPluginStarted();
    }

    /**
     * Create an instance of {@link IsPluginStartedResponse }
     * 
     */
    public IsPluginStartedResponse createIsPluginStartedResponse() {
        return new IsPluginStartedResponse();
    }

    /**
     * Create an instance of {@link StartPlugin }
     * 
     */
    public StartPlugin createStartPlugin() {
        return new StartPlugin();
    }

    /**
     * Create an instance of {@link StartPluginResponse }
     * 
     */
    public StartPluginResponse createStartPluginResponse() {
        return new StartPluginResponse();
    }

    /**
     * Create an instance of {@link StartPluginWithTimeout }
     * 
     */
    public StartPluginWithTimeout createStartPluginWithTimeout() {
        return new StartPluginWithTimeout();
    }

    /**
     * Create an instance of {@link StartPluginWithTimeoutResponse }
     * 
     */
    public StartPluginWithTimeoutResponse createStartPluginWithTimeoutResponse() {
        return new StartPluginWithTimeoutResponse();
    }

    /**
     * Create an instance of {@link StopPlugin }
     * 
     */
    public StopPlugin createStopPlugin() {
        return new StopPlugin();
    }

    /**
     * Create an instance of {@link StopPluginResponse }
     * 
     */
    public StopPluginResponse createStopPluginResponse() {
        return new StopPluginResponse();
    }

    /**
     * Create an instance of {@link StopPluginWithTimeout }
     * 
     */
    public StopPluginWithTimeout createStopPluginWithTimeout() {
        return new StopPluginWithTimeout();
    }

    /**
     * Create an instance of {@link StopPluginWithTimeoutResponse }
     * 
     */
    public StopPluginWithTimeoutResponse createStopPluginWithTimeoutResponse() {
        return new StopPluginWithTimeoutResponse();
    }

    /**
     * Create an instance of {@link ChangeSubtype }
     * 
     */
    public ChangeSubtype createChangeSubtype() {
        return new ChangeSubtype();
    }

    /**
     * Create an instance of {@link ChangeSubtypeResponse }
     * 
     */
    public ChangeSubtypeResponse createChangeSubtypeResponse() {
        return new ChangeSubtypeResponse();
    }

    /**
     * Create an instance of {@link WebServiceException }
     * 
     */
    public WebServiceException createWebServiceException() {
        return new WebServiceException();
    }

    /**
     * Create an instance of {@link WQTaskDetails }
     * 
     */
    public WQTaskDetails createWQTaskDetails() {
        return new WQTaskDetails();
    }

    /**
     * Create an instance of {@link WQueueExecutorDetails.Tasks }
     * 
     */
    public WQueueExecutorDetails.Tasks createWQueueExecutorDetailsTasks() {
        return new WQueueExecutorDetails.Tasks();
    }

    /**
     * Create an instance of {@link WQueueStatus.Executors }
     * 
     */
    public WQueueStatus.Executors createWQueueStatusExecutors() {
        return new WQueueStatus.Executors();
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
