
package framework.integrations.gwServices.messagingToolsAPI.pc.com.guidewire.pc.ws.gw.webservice.pc.pc800.messagingtoolsapi;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import framework.integrations.gwServices.messagingToolsAPI.pc.com.guidewire.gw.api.messaging.ExternalDestinationConfig;
import framework.integrations.gwServices.messagingToolsAPI.pc.com.guidewire.gw.api.messaging.MessageProcessingDirection;
import framework.integrations.gwServices.messagingToolsAPI.pc.com.guidewire.gw.api.webservice.messagingtools.Acknowledgement;
import framework.integrations.gwServices.messagingToolsAPI.pc.com.guidewire.gw.api.webservice.messagingtools.MessageStatisticsData;
import framework.integrations.gwServices.messagingToolsAPI.pc.com.guidewire.pc.typekey.ErrorCategory;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "MessagingToolsAPIPortType", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI")
@XmlSeeAlso({
    framework.integrations.gwServices.messagingToolsAPI.pc.com.guidewire.gw.api.webservice.messagingtools.ObjectFactory.class,
    framework.integrations.gwServices.messagingToolsAPI.pc.com.guidewire.gw.api.messaging.ObjectFactory.class,
    framework.integrations.gwServices.messagingToolsAPI.pc.com.guidewire.pc.ws.gw.webservice.pc.pc800.messagingtoolsapi.ObjectFactory.class,
    framework.integrations.gwServices.soapheaders.ObjectFactory.class,
    framework.integrations.gwServices.messagingToolsAPI.pc.com.guidewire.pc.typekey.ObjectFactory.class
})
public interface MessagingToolsAPIPortType {


    /**
     * 
     * @param cutoff
     * @throws WsiAuthenticationException_Exception
     * @throws RequiredFieldException_Exception
     */
    @WebMethod
    @RequestWrapper(localName = "purgeCompletedMessages", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.pc.com.guidewire.pc.ws.gw.webservice.pc.pc800.messagingtoolsapi.PurgeCompletedMessages")
    @ResponseWrapper(localName = "purgeCompletedMessagesResponse", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.pc.com.guidewire.pc.ws.gw.webservice.pc.pc800.messagingtoolsapi.PurgeCompletedMessagesResponse")
    public void purgeCompletedMessages(
        @WebParam(name = "cutoff", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI")
        XMLGregorianCalendar cutoff)
        throws RequiredFieldException_Exception, WsiAuthenticationException_Exception
    ;

    /**
     * 
     * @param destID
     * @throws WsiAuthenticationException_Exception
     * @throws BatchProcessException_Exception
     * @throws IllegalArgumentException_Exception
     */
    @WebMethod
    @RequestWrapper(localName = "suspendDestinationBothDirections", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.pc.com.guidewire.pc.ws.gw.webservice.pc.pc800.messagingtoolsapi.SuspendDestinationBothDirections")
    @ResponseWrapper(localName = "suspendDestinationBothDirectionsResponse", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.pc.com.guidewire.pc.ws.gw.webservice.pc.pc800.messagingtoolsapi.SuspendDestinationBothDirectionsResponse")
    public void suspendDestinationBothDirections(
        @WebParam(name = "destID", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI")
        int destID)
        throws BatchProcessException_Exception, IllegalArgumentException_Exception, WsiAuthenticationException_Exception
    ;

    /**
     * 
     * @param destID
     * @throws WsiAuthenticationException_Exception
     * @throws BatchProcessException_Exception
     * @throws IllegalArgumentException_Exception
     */
    @WebMethod
    @RequestWrapper(localName = "resumeDestinationBothDirections", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.pc.com.guidewire.pc.ws.gw.webservice.pc.pc800.messagingtoolsapi.ResumeDestinationBothDirections")
    @ResponseWrapper(localName = "resumeDestinationBothDirectionsResponse", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.pc.com.guidewire.pc.ws.gw.webservice.pc.pc800.messagingtoolsapi.ResumeDestinationBothDirectionsResponse")
    public void resumeDestinationBothDirections(
        @WebParam(name = "destID", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI")
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
     * @throws WsiAuthenticationException_Exception
     * @throws BatchProcessException_Exception
     * @throws IllegalArgumentException_Exception
     */
    @WebMethod
    @RequestWrapper(localName = "configureDestination", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.pc.com.guidewire.pc.ws.gw.webservice.pc.pc800.messagingtoolsapi.ConfigureDestination")
    @ResponseWrapper(localName = "configureDestinationResponse", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.pc.com.guidewire.pc.ws.gw.webservice.pc.pc800.messagingtoolsapi.ConfigureDestinationResponse")
    public void configureDestination(
        @WebParam(name = "destID", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI")
        int destID,
        @WebParam(name = "timeToWaitInSec", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI")
        int timeToWaitInSec,
        @WebParam(name = "maxretries", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI")
        Integer maxretries,
        @WebParam(name = "initialretryinterval", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI")
        Long initialretryinterval,
        @WebParam(name = "retrybackoffmultiplier", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI")
        Integer retrybackoffmultiplier,
        @WebParam(name = "pollinterval", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI")
        Integer pollinterval,
        @WebParam(name = "numsenderthreads", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI")
        Integer numsenderthreads,
        @WebParam(name = "chunksize", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI")
        Integer chunksize)
        throws BatchProcessException_Exception, IllegalArgumentException_Exception, WsiAuthenticationException_Exception
    ;

    /**
     * 
     * @param destID
     * @return
     *     returns framework.integrations.gwServices.messagingToolsAPI.pc.com.guidewire.gw.api.messaging.ExternalDestinationConfig
     * @throws WsiAuthenticationException_Exception
     * @throws BatchProcessException_Exception
     * @throws IllegalArgumentException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI")
    @RequestWrapper(localName = "getConfiguration", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.pc.com.guidewire.pc.ws.gw.webservice.pc.pc800.messagingtoolsapi.GetConfiguration")
    @ResponseWrapper(localName = "getConfigurationResponse", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.pc.com.guidewire.pc.ws.gw.webservice.pc.pc800.messagingtoolsapi.GetConfigurationResponse")
    public ExternalDestinationConfig getConfiguration(
        @WebParam(name = "destID", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI")
        int destID)
        throws BatchProcessException_Exception, IllegalArgumentException_Exception, WsiAuthenticationException_Exception
    ;

    /**
     * 
     * @param senderRefID
     * @param destID
     * @return
     *     returns java.lang.Long
     * @throws WsiAuthenticationException_Exception
     * @throws RequiredFieldException_Exception
     * @throws BatchProcessException_Exception
     * @throws SOAPSenderException_Exception
     * @throws IllegalArgumentException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI")
    @RequestWrapper(localName = "getMessageIdBySenderRefId", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.pc.com.guidewire.pc.ws.gw.webservice.pc.pc800.messagingtoolsapi.GetMessageIdBySenderRefId")
    @ResponseWrapper(localName = "getMessageIdBySenderRefIdResponse", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.pc.com.guidewire.pc.ws.gw.webservice.pc.pc800.messagingtoolsapi.GetMessageIdBySenderRefIdResponse")
    public Long getMessageIdBySenderRefId(
        @WebParam(name = "senderRefID", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI")
        String senderRefID,
        @WebParam(name = "destID", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI")
        int destID)
        throws BatchProcessException_Exception, IllegalArgumentException_Exception, RequiredFieldException_Exception, SOAPSenderException_Exception, WsiAuthenticationException_Exception
    ;

    /**
     * 
     * @param ack
     * @return
     *     returns boolean
     * @throws WsiAuthenticationException_Exception
     * @throws SOAPException_Exception
     * @throws RequiredFieldException_Exception
     * @throws SOAPSenderException_Exception
     * @throws IllegalArgumentException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI")
    @RequestWrapper(localName = "ackMessage", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.pc.com.guidewire.pc.ws.gw.webservice.pc.pc800.messagingtoolsapi.AckMessage")
    @ResponseWrapper(localName = "ackMessageResponse", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.pc.com.guidewire.pc.ws.gw.webservice.pc.pc800.messagingtoolsapi.AckMessageResponse")
    public boolean ackMessage(
        @WebParam(name = "ack", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI")
        Acknowledgement ack)
        throws IllegalArgumentException_Exception, RequiredFieldException_Exception, SOAPException_Exception, SOAPSenderException_Exception, WsiAuthenticationException_Exception
    ;

    /**
     * 
     * @param messageID
     * @return
     *     returns boolean
     * @throws WsiAuthenticationException_Exception
     * @throws IllegalArgumentException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI")
    @RequestWrapper(localName = "retryMessage", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.pc.com.guidewire.pc.ws.gw.webservice.pc.pc800.messagingtoolsapi.RetryMessage")
    @ResponseWrapper(localName = "retryMessageResponse", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.pc.com.guidewire.pc.ws.gw.webservice.pc.pc800.messagingtoolsapi.RetryMessageResponse")
    public boolean retryMessage(
        @WebParam(name = "messageID", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI")
        long messageID)
        throws IllegalArgumentException_Exception, WsiAuthenticationException_Exception
    ;

    /**
     * 
     * @param messageID
     * @return
     *     returns boolean
     * @throws WsiAuthenticationException_Exception
     * @throws BatchProcessException_Exception
     * @throws IllegalArgumentException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI")
    @RequestWrapper(localName = "skipMessage", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.pc.com.guidewire.pc.ws.gw.webservice.pc.pc800.messagingtoolsapi.SkipMessage")
    @ResponseWrapper(localName = "skipMessageResponse", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.pc.com.guidewire.pc.ws.gw.webservice.pc.pc800.messagingtoolsapi.SkipMessageResponse")
    public boolean skipMessage(
        @WebParam(name = "messageID", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI")
        long messageID)
        throws BatchProcessException_Exception, IllegalArgumentException_Exception, WsiAuthenticationException_Exception
    ;

    /**
     * 
     * @param destID
     * @return
     *     returns boolean
     * @throws WsiAuthenticationException_Exception
     * @throws IllegalArgumentException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI")
    @RequestWrapper(localName = "retryRetryableErrorMessages", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.pc.com.guidewire.pc.ws.gw.webservice.pc.pc800.messagingtoolsapi.RetryRetryableErrorMessages")
    @ResponseWrapper(localName = "retryRetryableErrorMessagesResponse", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.pc.com.guidewire.pc.ws.gw.webservice.pc.pc800.messagingtoolsapi.RetryRetryableErrorMessagesResponse")
    public boolean retryRetryableErrorMessages(
        @WebParam(name = "destID", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI")
        int destID)
        throws IllegalArgumentException_Exception, WsiAuthenticationException_Exception
    ;

    /**
     * 
     * @param category
     * @param destID
     * @return
     *     returns boolean
     * @throws WsiAuthenticationException_Exception
     * @throws IllegalArgumentException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI")
    @RequestWrapper(localName = "retryRetryableErrorMessagesForCategory", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.pc.com.guidewire.pc.ws.gw.webservice.pc.pc800.messagingtoolsapi.RetryRetryableErrorMessagesForCategory")
    @ResponseWrapper(localName = "retryRetryableErrorMessagesForCategoryResponse", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.pc.com.guidewire.pc.ws.gw.webservice.pc.pc800.messagingtoolsapi.RetryRetryableErrorMessagesForCategoryResponse")
    public boolean retryRetryableErrorMessagesForCategory(
        @WebParam(name = "destID", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI")
        int destID,
        @WebParam(name = "category", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI")
        ErrorCategory category)
        throws IllegalArgumentException_Exception, WsiAuthenticationException_Exception
    ;

    /**
     * 
     * @param retryLimit
     * @param destID
     * @return
     *     returns boolean
     * @throws WsiAuthenticationException_Exception
     * @throws IllegalArgumentException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI")
    @RequestWrapper(localName = "retryRetryableSomeErrorMessages", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.pc.com.guidewire.pc.ws.gw.webservice.pc.pc800.messagingtoolsapi.RetryRetryableSomeErrorMessages")
    @ResponseWrapper(localName = "retryRetryableSomeErrorMessagesResponse", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.pc.com.guidewire.pc.ws.gw.webservice.pc.pc800.messagingtoolsapi.RetryRetryableSomeErrorMessagesResponse")
    public boolean retryRetryableSomeErrorMessages(
        @WebParam(name = "destID", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI")
        int destID,
        @WebParam(name = "retryLimit", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI")
        int retryLimit)
        throws IllegalArgumentException_Exception, WsiAuthenticationException_Exception
    ;

    /**
     * 
     * @param destID
     * @param safeOrderedObjectId
     * @return
     *     returns framework.integrations.gwServices.messagingToolsAPI.pc.com.guidewire.gw.api.webservice.messagingtools.MessageStatisticsData
     * @throws WsiAuthenticationException_Exception
     * @throws RequiredFieldException_Exception
     * @throws IllegalArgumentException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI")
    @RequestWrapper(localName = "getMessageStatisticsForSafeOrderedObject", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.pc.com.guidewire.pc.ws.gw.webservice.pc.pc800.messagingtoolsapi.GetMessageStatisticsForSafeOrderedObject")
    @ResponseWrapper(localName = "getMessageStatisticsForSafeOrderedObjectResponse", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.pc.com.guidewire.pc.ws.gw.webservice.pc.pc800.messagingtoolsapi.GetMessageStatisticsForSafeOrderedObjectResponse")
    public MessageStatisticsData getMessageStatisticsForSafeOrderedObject(
        @WebParam(name = "destID", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI")
        int destID,
        @WebParam(name = "safeOrderedObjectId", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI")
        String safeOrderedObjectId)
        throws IllegalArgumentException_Exception, RequiredFieldException_Exception, WsiAuthenticationException_Exception
    ;

    /**
     * 
     * @param destID
     * @return
     *     returns framework.integrations.gwServices.messagingToolsAPI.pc.com.guidewire.gw.api.webservice.messagingtools.MessageStatisticsData
     * @throws WsiAuthenticationException_Exception
     * @throws IllegalArgumentException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI")
    @RequestWrapper(localName = "getTotalStatistics", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.pc.com.guidewire.pc.ws.gw.webservice.pc.pc800.messagingtoolsapi.GetTotalStatistics")
    @ResponseWrapper(localName = "getTotalStatisticsResponse", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.pc.com.guidewire.pc.ws.gw.webservice.pc.pc800.messagingtoolsapi.GetTotalStatisticsResponse")
    public MessageStatisticsData getTotalStatistics(
        @WebParam(name = "destID", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI")
        int destID)
        throws IllegalArgumentException_Exception, WsiAuthenticationException_Exception
    ;

    /**
     * 
     * @param direction
     * @param destID
     * @return
     *     returns boolean
     * @throws WsiAuthenticationException_Exception
     * @throws SOAPException_Exception
     * @throws RequiredFieldException_Exception
     * @throws BatchProcessException_Exception
     * @throws IllegalArgumentException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI")
    @RequestWrapper(localName = "suspendDestination", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.pc.com.guidewire.pc.ws.gw.webservice.pc.pc800.messagingtoolsapi.SuspendDestination")
    @ResponseWrapper(localName = "suspendDestinationResponse", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.pc.com.guidewire.pc.ws.gw.webservice.pc.pc800.messagingtoolsapi.SuspendDestinationResponse")
    public boolean suspendDestination(
        @WebParam(name = "destID", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI")
        int destID,
        @WebParam(name = "direction", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI")
        MessageProcessingDirection direction)
        throws BatchProcessException_Exception, IllegalArgumentException_Exception, RequiredFieldException_Exception, SOAPException_Exception, WsiAuthenticationException_Exception
    ;

    /**
     * 
     * @param direction
     * @param destID
     * @return
     *     returns boolean
     * @throws WsiAuthenticationException_Exception
     * @throws SOAPException_Exception
     * @throws RequiredFieldException_Exception
     * @throws SOAPSenderException_Exception
     * @throws BatchProcessException_Exception
     * @throws IllegalArgumentException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI")
    @RequestWrapper(localName = "resumeDestination", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.pc.com.guidewire.pc.ws.gw.webservice.pc.pc800.messagingtoolsapi.ResumeDestination")
    @ResponseWrapper(localName = "resumeDestinationResponse", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.pc.com.guidewire.pc.ws.gw.webservice.pc.pc800.messagingtoolsapi.ResumeDestinationResponse")
    public boolean resumeDestination(
        @WebParam(name = "destID", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI")
        int destID,
        @WebParam(name = "direction", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI")
        MessageProcessingDirection direction)
        throws BatchProcessException_Exception, IllegalArgumentException_Exception, RequiredFieldException_Exception, SOAPException_Exception, SOAPSenderException_Exception, WsiAuthenticationException_Exception
    ;

    /**
     * 
     * @param direction
     * @param destID
     * @return
     *     returns boolean
     * @throws WsiAuthenticationException_Exception
     * @throws RequiredFieldException_Exception
     * @throws BatchProcessException_Exception
     * @throws SOAPSenderException_Exception
     * @throws IllegalArgumentException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI")
    @RequestWrapper(localName = "isSuspended", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.pc.com.guidewire.pc.ws.gw.webservice.pc.pc800.messagingtoolsapi.IsSuspended")
    @ResponseWrapper(localName = "isSuspendedResponse", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.pc.com.guidewire.pc.ws.gw.webservice.pc.pc800.messagingtoolsapi.IsSuspendedResponse")
    public boolean isSuspended(
        @WebParam(name = "destID", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI")
        int destID,
        @WebParam(name = "direction", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI")
        MessageProcessingDirection direction)
        throws BatchProcessException_Exception, IllegalArgumentException_Exception, RequiredFieldException_Exception, SOAPSenderException_Exception, WsiAuthenticationException_Exception
    ;

    /**
     * 
     * @param direction
     * @param destID
     * @return
     *     returns boolean
     * @throws WsiAuthenticationException_Exception
     * @throws RequiredFieldException_Exception
     * @throws BatchProcessException_Exception
     * @throws IllegalArgumentException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI")
    @RequestWrapper(localName = "isResumed", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.pc.com.guidewire.pc.ws.gw.webservice.pc.pc800.messagingtoolsapi.IsResumed")
    @ResponseWrapper(localName = "isResumedResponse", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.pc.com.guidewire.pc.ws.gw.webservice.pc.pc800.messagingtoolsapi.IsResumedResponse")
    public boolean isResumed(
        @WebParam(name = "destID", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI")
        int destID,
        @WebParam(name = "direction", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI")
        MessageProcessingDirection direction)
        throws BatchProcessException_Exception, IllegalArgumentException_Exception, RequiredFieldException_Exception, WsiAuthenticationException_Exception
    ;

    /**
     * 
     * @param destID
     * @return
     *     returns java.lang.String
     * @throws WsiAuthenticationException_Exception
     * @throws BatchProcessException_Exception
     * @throws IllegalArgumentException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI")
    @RequestWrapper(localName = "getDestinationStatus", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.pc.com.guidewire.pc.ws.gw.webservice.pc.pc800.messagingtoolsapi.GetDestinationStatus")
    @ResponseWrapper(localName = "getDestinationStatusResponse", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.pc.com.guidewire.pc.ws.gw.webservice.pc.pc800.messagingtoolsapi.GetDestinationStatusResponse")
    public String getDestinationStatus(
        @WebParam(name = "destID", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI")
        int destID)
        throws BatchProcessException_Exception, IllegalArgumentException_Exception, WsiAuthenticationException_Exception
    ;

    /**
     * 
     * @param accountNumber
     * @param destID
     * @throws WsiAuthenticationException_Exception
     * @throws BatchProcessException_Exception
     * @throws BadIdentifierException_Exception
     * @throws IllegalArgumentException_Exception
     */
    @WebMethod
    @RequestWrapper(localName = "resyncAccount", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.pc.com.guidewire.pc.ws.gw.webservice.pc.pc800.messagingtoolsapi.ResyncAccount")
    @ResponseWrapper(localName = "resyncAccountResponse", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI", className = "framework.integrations.gwServices.messagingToolsAPI.pc.com.guidewire.pc.ws.gw.webservice.pc.pc800.messagingtoolsapi.ResyncAccountResponse")
    public void resyncAccount(
        @WebParam(name = "destID", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI")
        int destID,
        @WebParam(name = "accountNumber", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc800/MessagingToolsAPI")
        String accountNumber)
        throws BadIdentifierException_Exception, BatchProcessException_Exception, IllegalArgumentException_Exception, WsiAuthenticationException_Exception
    ;

}