
package framework.integrations.gwServices.messagingToolsAPI.bc.com.guidewire.bc.ws.gw.webservice.bc.messagingtoolsapi;

import framework.integrations.gwServices.messagingToolsAPI.bc.com.guidewire.gw.api.messaging.ExternalDestinationConfig;
import framework.integrations.gwServices.messagingToolsAPI.bc.com.guidewire.gw.api.messaging.MessageProcessingDirection;
import framework.integrations.gwServices.messagingToolsAPI.bc.com.guidewire.gw.api.webservice.messagingtools.Acknowledgement;
import framework.integrations.gwServices.messagingToolsAPI.bc.com.guidewire.gw.api.webservice.messagingtools.MessageStatisticsData;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "MessagingToolsAPIPortType", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI")
@XmlSeeAlso({
    framework.integrations.gwServices.messagingToolsAPI.bc.com.guidewire.gw.api.webservice.messagingtools.ObjectFactory.class,
    framework.integrations.gwServices.messagingToolsAPI.bc.com.guidewire.bc.ws.gw.webservice.bc.messagingtoolsapi.ObjectFactory.class,
    framework.integrations.gwServices.messagingToolsAPI.bc.com.guidewire.gw.api.messaging.ObjectFactory.class,
    framework.integrations.gwServices.soapheaders.ObjectFactory.class,

})
public interface MessagingToolsAPIPortType {


    /**
     * 
     * @param accountPublicID
     * @param destID
     * @throws BadIdentifierException_Exception
     * @throws RequiredFieldException_Exception
     * @throws WsiAuthenticationException_Exception
     */
    @WebMethod
    @RequestWrapper(localName = "resyncAccount", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.bc.com.guidewire.bc.ws.gw.webservice.bc.messagingtoolsapi.ResyncAccount")
    @ResponseWrapper(localName = "resyncAccountResponse", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.bc.com.guidewire.bc.ws.gw.webservice.bc.messagingtoolsapi.ResyncAccountResponse")
    public void resyncAccount(
        @WebParam(name = "destID", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI")
        int destID,
        @WebParam(name = "accountPublicID", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI")
        String accountPublicID)
        throws BadIdentifierException_Exception, RequiredFieldException_Exception, WsiAuthenticationException_Exception
    ;

    /**
     * 
     * @param producerPublicID
     * @param destID
     * @throws BadIdentifierException_Exception
     * @throws RequiredFieldException_Exception
     * @throws WsiAuthenticationException_Exception
     */
    @WebMethod
    @RequestWrapper(localName = "resyncProducer", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.bc.com.guidewire.bc.ws.gw.webservice.bc.messagingtoolsapi.ResyncProducer")
    @ResponseWrapper(localName = "resyncProducerResponse", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.bc.com.guidewire.bc.ws.gw.webservice.bc.messagingtoolsapi.ResyncProducerResponse")
    public void resyncProducer(
        @WebParam(name = "destID", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI")
        int destID,
        @WebParam(name = "producerPublicID", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI")
        String producerPublicID)
        throws BadIdentifierException_Exception, RequiredFieldException_Exception, WsiAuthenticationException_Exception
    ;

    /**
     * 
     * @param policyPeriodPublicID
     * @param destID
     * @throws BadIdentifierException_Exception
     * @throws RequiredFieldException_Exception
     * @throws WsiAuthenticationException_Exception
     */
    @WebMethod
    @RequestWrapper(localName = "resyncPolicyPeriod", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.bc.com.guidewire.bc.ws.gw.webservice.bc.messagingtoolsapi.ResyncPolicyPeriod")
    @ResponseWrapper(localName = "resyncPolicyPeriodResponse", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.bc.com.guidewire.bc.ws.gw.webservice.bc.messagingtoolsapi.ResyncPolicyPeriodResponse")
    public void resyncPolicyPeriod(
        @WebParam(name = "destID", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI")
        int destID,
        @WebParam(name = "policyPeriodPublicID", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI")
        String policyPeriodPublicID)
        throws BadIdentifierException_Exception, RequiredFieldException_Exception, WsiAuthenticationException_Exception
    ;

    /**
     * 
     * @param cutoff
     * @throws IllegalArgumentException_Exception
     * @throws WsiAuthenticationException_Exception
     */
    @WebMethod
    @RequestWrapper(localName = "purgeCompletedMessages", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.bc.com.guidewire.bc.ws.gw.webservice.bc.messagingtoolsapi.PurgeCompletedMessages")
    @ResponseWrapper(localName = "purgeCompletedMessagesResponse", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.bc.com.guidewire.bc.ws.gw.webservice.bc.messagingtoolsapi.PurgeCompletedMessagesResponse")
    public void purgeCompletedMessages(
        @WebParam(name = "cutoff", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI")
        XMLGregorianCalendar cutoff)
        throws IllegalArgumentException_Exception, WsiAuthenticationException_Exception
    ;

    /**
     * 
     * @param destID
     * @throws BatchProcessException_Exception
     * @throws IllegalArgumentException_Exception
     * @throws WsiAuthenticationException_Exception
     */
    @WebMethod
    @RequestWrapper(localName = "suspendDestinationBothDirections", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.bc.com.guidewire.bc.ws.gw.webservice.bc.messagingtoolsapi.SuspendDestinationBothDirections")
    @ResponseWrapper(localName = "suspendDestinationBothDirectionsResponse", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.bc.com.guidewire.bc.ws.gw.webservice.bc.messagingtoolsapi.SuspendDestinationBothDirectionsResponse")
    public void suspendDestinationBothDirections(
        @WebParam(name = "destID", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI")
        int destID)
        throws BatchProcessException_Exception, IllegalArgumentException_Exception, WsiAuthenticationException_Exception
    ;

    /**
     * 
     * @param destID
     * @throws BatchProcessException_Exception
     * @throws IllegalArgumentException_Exception
     * @throws WsiAuthenticationException_Exception
     */
    @WebMethod
    @RequestWrapper(localName = "resumeDestinationBothDirections", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.bc.com.guidewire.bc.ws.gw.webservice.bc.messagingtoolsapi.ResumeDestinationBothDirections")
    @ResponseWrapper(localName = "resumeDestinationBothDirectionsResponse", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.bc.com.guidewire.bc.ws.gw.webservice.bc.messagingtoolsapi.ResumeDestinationBothDirectionsResponse")
    public void resumeDestinationBothDirections(
        @WebParam(name = "destID", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI")
        int destID)
        throws BatchProcessException_Exception, IllegalArgumentException_Exception, WsiAuthenticationException_Exception
    ;

    /**
     * 
     * @param numsenderthreads
     * @param retrybackoffmultiplier
     * @param chunksize
     * @param destID
     * @param pollinterval
     * @param maxretries
     * @param timeToWaitInSec
     * @param initialretryinterval
     * @throws BatchProcessException_Exception
     * @throws IllegalArgumentException_Exception
     * @throws WsiAuthenticationException_Exception
     */
    @WebMethod
    @RequestWrapper(localName = "configureDestination", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.bc.com.guidewire.bc.ws.gw.webservice.bc.messagingtoolsapi.ConfigureDestination")
    @ResponseWrapper(localName = "configureDestinationResponse", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.bc.com.guidewire.bc.ws.gw.webservice.bc.messagingtoolsapi.ConfigureDestinationResponse")
    public void configureDestination(
        @WebParam(name = "destID", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI")
        int destID,
        @WebParam(name = "timeToWaitInSec", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI")
        int timeToWaitInSec,
        @WebParam(name = "maxretries", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI")
        Integer maxretries,
        @WebParam(name = "initialretryinterval", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI")
        Long initialretryinterval,
        @WebParam(name = "retrybackoffmultiplier", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI")
        Integer retrybackoffmultiplier,
        @WebParam(name = "pollinterval", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI")
        Integer pollinterval,
        @WebParam(name = "numsenderthreads", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI")
        Integer numsenderthreads,
        @WebParam(name = "chunksize", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI")
        Integer chunksize)
        throws BatchProcessException_Exception, IllegalArgumentException_Exception, WsiAuthenticationException_Exception
    ;

    /**
     * 
     * @param destID
     * @return
     *     returns framework.integrations.gwServices.messagingToolsAPI.bc.com.guidewire.gw.api.messaging.ExternalDestinationConfig
     * @throws BatchProcessException_Exception
     * @throws IllegalArgumentException_Exception
     * @throws WsiAuthenticationException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI")
    @RequestWrapper(localName = "getConfiguration", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.bc.com.guidewire.bc.ws.gw.webservice.bc.messagingtoolsapi.GetConfiguration")
    @ResponseWrapper(localName = "getConfigurationResponse", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.bc.com.guidewire.bc.ws.gw.webservice.bc.messagingtoolsapi.GetConfigurationResponse")
    public ExternalDestinationConfig getConfiguration(
        @WebParam(name = "destID", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI")
        int destID)
        throws BatchProcessException_Exception, IllegalArgumentException_Exception, WsiAuthenticationException_Exception
    ;

    /**
     * 
     * @param senderRefID
     * @param destID
     * @return
     *     returns java.lang.Long
     * @throws BatchProcessException_Exception
     * @throws IllegalArgumentException_Exception
     * @throws WsiAuthenticationException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI")
    @RequestWrapper(localName = "getMessageIdBySenderRefId", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.bc.com.guidewire.bc.ws.gw.webservice.bc.messagingtoolsapi.GetMessageIdBySenderRefId")
    @ResponseWrapper(localName = "getMessageIdBySenderRefIdResponse", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.bc.com.guidewire.bc.ws.gw.webservice.bc.messagingtoolsapi.GetMessageIdBySenderRefIdResponse")
    public Long getMessageIdBySenderRefId(
        @WebParam(name = "senderRefID", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI")
        String senderRefID,
        @WebParam(name = "destID", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI")
        int destID)
        throws BatchProcessException_Exception, IllegalArgumentException_Exception, WsiAuthenticationException_Exception
    ;

    /**
     * 
     * @param ack
     * @return
     *     returns boolean
     * @throws SOAPSenderException_Exception
     * @throws SOAPException_Exception
     * @throws IllegalArgumentException_Exception
     * @throws WsiAuthenticationException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI")
    @RequestWrapper(localName = "ackMessage", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.bc.com.guidewire.bc.ws.gw.webservice.bc.messagingtoolsapi.AckMessage")
    @ResponseWrapper(localName = "ackMessageResponse", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.bc.com.guidewire.bc.ws.gw.webservice.bc.messagingtoolsapi.AckMessageResponse")
    public boolean ackMessage(
        @WebParam(name = "ack", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI")
        Acknowledgement ack)
        throws IllegalArgumentException_Exception, SOAPException_Exception, SOAPSenderException_Exception, WsiAuthenticationException_Exception
    ;

    /**
     * 
     * @param messageID
     * @return
     *     returns boolean
     * @throws WsiAuthenticationException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI")
    @RequestWrapper(localName = "retryMessage", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.bc.com.guidewire.bc.ws.gw.webservice.bc.messagingtoolsapi.RetryMessage")
    @ResponseWrapper(localName = "retryMessageResponse", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.bc.com.guidewire.bc.ws.gw.webservice.bc.messagingtoolsapi.RetryMessageResponse")
    public boolean retryMessage(
        @WebParam(name = "messageID", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI")
        long messageID)
        throws WsiAuthenticationException_Exception
    ;

    /**
     * 
     * @param messageID
     * @return
     *     returns boolean
     * @throws WsiAuthenticationException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI")
    @RequestWrapper(localName = "skipMessage", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.bc.com.guidewire.bc.ws.gw.webservice.bc.messagingtoolsapi.SkipMessage")
    @ResponseWrapper(localName = "skipMessageResponse", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.bc.com.guidewire.bc.ws.gw.webservice.bc.messagingtoolsapi.SkipMessageResponse")
    public boolean skipMessage(
        @WebParam(name = "messageID", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI")
        long messageID)
        throws WsiAuthenticationException_Exception
    ;

    /**
     * 
     * @param destID
     * @return
     *     returns boolean
     * @throws IllegalArgumentException_Exception
     * @throws WsiAuthenticationException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI")
    @RequestWrapper(localName = "retryRetryableErrorMessages", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.bc.com.guidewire.bc.ws.gw.webservice.bc.messagingtoolsapi.RetryRetryableErrorMessages")
    @ResponseWrapper(localName = "retryRetryableErrorMessagesResponse", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.bc.com.guidewire.bc.ws.gw.webservice.bc.messagingtoolsapi.RetryRetryableErrorMessagesResponse")
    public boolean retryRetryableErrorMessages(
        @WebParam(name = "destID", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI")
        int destID)
        throws IllegalArgumentException_Exception, WsiAuthenticationException_Exception
    ;

    /**
     * 
     * @param retryLimit
     * @param destID
     * @return
     *     returns boolean
     * @throws IllegalArgumentException_Exception
     * @throws WsiAuthenticationException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI")
    @RequestWrapper(localName = "retryRetryableSomeErrorMessages", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.bc.com.guidewire.bc.ws.gw.webservice.bc.messagingtoolsapi.RetryRetryableSomeErrorMessages")
    @ResponseWrapper(localName = "retryRetryableSomeErrorMessagesResponse", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.bc.com.guidewire.bc.ws.gw.webservice.bc.messagingtoolsapi.RetryRetryableSomeErrorMessagesResponse")
    public boolean retryRetryableSomeErrorMessages(
        @WebParam(name = "destID", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI")
        int destID,
        @WebParam(name = "retryLimit", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI")
        int retryLimit)
        throws IllegalArgumentException_Exception, WsiAuthenticationException_Exception
    ;

    /**
     * 
     * @param category
     * @param destID
     * @return
     *     returns boolean
     * @throws IllegalArgumentException_Exception
     * @throws WsiAuthenticationException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI")
    @RequestWrapper(localName = "retryRetryableErrorMessagesForCategory", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.bc.com.guidewire.bc.ws.gw.webservice.bc.messagingtoolsapi.RetryRetryableErrorMessagesForCategory")
    @ResponseWrapper(localName = "retryRetryableErrorMessagesForCategoryResponse", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.bc.com.guidewire.bc.ws.gw.webservice.bc.messagingtoolsapi.RetryRetryableErrorMessagesForCategoryResponse")
    public boolean retryRetryableErrorMessagesForCategory(
        @WebParam(name = "destID", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI")
        int destID,
        @WebParam(name = "category", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI")
        String category)
        throws IllegalArgumentException_Exception, WsiAuthenticationException_Exception
    ;

    /**
     * 
     * @param destID
     * @param safeOrderedObjectId
     * @return
     *     returns framework.integrations.gwServices.messagingToolsAPI.bc.com.guidewire.gw.api.webservice.messagingtools.MessageStatisticsData
     * @throws IllegalArgumentException_Exception
     * @throws WsiAuthenticationException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI")
    @RequestWrapper(localName = "getMessageStatisticsForSafeOrderedObject", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.bc.com.guidewire.bc.ws.gw.webservice.bc.messagingtoolsapi.GetMessageStatisticsForSafeOrderedObject")
    @ResponseWrapper(localName = "getMessageStatisticsForSafeOrderedObjectResponse", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.bc.com.guidewire.bc.ws.gw.webservice.bc.messagingtoolsapi.GetMessageStatisticsForSafeOrderedObjectResponse")
    public MessageStatisticsData getMessageStatisticsForSafeOrderedObject(
        @WebParam(name = "destID", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI")
        int destID,
        @WebParam(name = "safeOrderedObjectId", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI")
        String safeOrderedObjectId)
        throws IllegalArgumentException_Exception, WsiAuthenticationException_Exception
    ;

    /**
     * 
     * @param destID
     * @return
     *     returns framework.integrations.gwServices.messagingToolsAPI.bc.com.guidewire.gw.api.webservice.messagingtools.MessageStatisticsData
     * @throws IllegalArgumentException_Exception
     * @throws WsiAuthenticationException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI")
    @RequestWrapper(localName = "getTotalStatistics", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.bc.com.guidewire.bc.ws.gw.webservice.bc.messagingtoolsapi.GetTotalStatistics")
    @ResponseWrapper(localName = "getTotalStatisticsResponse", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.bc.com.guidewire.bc.ws.gw.webservice.bc.messagingtoolsapi.GetTotalStatisticsResponse")
    public MessageStatisticsData getTotalStatistics(
        @WebParam(name = "destID", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI")
        int destID)
        throws IllegalArgumentException_Exception, WsiAuthenticationException_Exception
    ;

    /**
     * 
     * @param direction
     * @param destID
     * @return
     *     returns boolean
     * @throws SOAPSenderException_Exception
     * @throws SOAPException_Exception
     * @throws IllegalArgumentException_Exception
     * @throws WsiAuthenticationException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI")
    @RequestWrapper(localName = "suspendDestination", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.bc.com.guidewire.bc.ws.gw.webservice.bc.messagingtoolsapi.SuspendDestination")
    @ResponseWrapper(localName = "suspendDestinationResponse", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.bc.com.guidewire.bc.ws.gw.webservice.bc.messagingtoolsapi.SuspendDestinationResponse")
    public boolean suspendDestination(
        @WebParam(name = "destID", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI")
        int destID,
        @WebParam(name = "direction", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI")
        MessageProcessingDirection direction)
        throws IllegalArgumentException_Exception, SOAPException_Exception, SOAPSenderException_Exception, WsiAuthenticationException_Exception
    ;

    /**
     * 
     * @param direction
     * @param destID
     * @return
     *     returns boolean
     * @throws SOAPSenderException_Exception
     * @throws SOAPException_Exception
     * @throws IllegalArgumentException_Exception
     * @throws WsiAuthenticationException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI")
    @RequestWrapper(localName = "resumeDestination", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.bc.com.guidewire.bc.ws.gw.webservice.bc.messagingtoolsapi.ResumeDestination")
    @ResponseWrapper(localName = "resumeDestinationResponse", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.bc.com.guidewire.bc.ws.gw.webservice.bc.messagingtoolsapi.ResumeDestinationResponse")
    public boolean resumeDestination(
        @WebParam(name = "destID", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI")
        int destID,
        @WebParam(name = "direction", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI")
        MessageProcessingDirection direction)
        throws IllegalArgumentException_Exception, SOAPException_Exception, SOAPSenderException_Exception, WsiAuthenticationException_Exception
    ;

    /**
     * 
     * @param direction
     * @param destID
     * @return
     *     returns boolean
     * @throws IllegalArgumentException_Exception
     * @throws WsiAuthenticationException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI")
    @RequestWrapper(localName = "isSuspended", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.bc.com.guidewire.bc.ws.gw.webservice.bc.messagingtoolsapi.IsSuspended")
    @ResponseWrapper(localName = "isSuspendedResponse", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.bc.com.guidewire.bc.ws.gw.webservice.bc.messagingtoolsapi.IsSuspendedResponse")
    public boolean isSuspended(
        @WebParam(name = "destID", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI")
        int destID,
        @WebParam(name = "direction", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI")
        MessageProcessingDirection direction)
        throws IllegalArgumentException_Exception, WsiAuthenticationException_Exception
    ;

    /**
     * 
     * @param direction
     * @param destID
     * @return
     *     returns boolean
     * @throws IllegalArgumentException_Exception
     * @throws WsiAuthenticationException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI")
    @RequestWrapper(localName = "isResumed", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.bc.com.guidewire.bc.ws.gw.webservice.bc.messagingtoolsapi.IsResumed")
    @ResponseWrapper(localName = "isResumedResponse", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.bc.com.guidewire.bc.ws.gw.webservice.bc.messagingtoolsapi.IsResumedResponse")
    public boolean isResumed(
        @WebParam(name = "destID", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI")
        int destID,
        @WebParam(name = "direction", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI")
        MessageProcessingDirection direction)
        throws IllegalArgumentException_Exception, WsiAuthenticationException_Exception
    ;

    /**
     * 
     * @param destID
     * @return
     *     returns java.lang.String
     * @throws IllegalArgumentException_Exception
     * @throws WsiAuthenticationException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI")
    @RequestWrapper(localName = "getDestinationStatus", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.bc.com.guidewire.bc.ws.gw.webservice.bc.messagingtoolsapi.GetDestinationStatus")
    @ResponseWrapper(localName = "getDestinationStatusResponse", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.bc.com.guidewire.bc.ws.gw.webservice.bc.messagingtoolsapi.GetDestinationStatusResponse")
    public String getDestinationStatus(
        @WebParam(name = "destID", targetNamespace = "http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI")
        int destID)
        throws IllegalArgumentException_Exception, WsiAuthenticationException_Exception
    ;

}
