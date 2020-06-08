
package framework.integrations.gwServices.maintenanceToolsAPI.cc.com.guidewire.cc.ws.gw.webservice.cc.maintenancetoolsapi;

import framework.integrations.gwServices.maintenanceToolsAPI.cc.com.guidewire.gw.api.tools.ProcessID;
import framework.integrations.gwServices.maintenanceToolsAPI.cc.com.guidewire.gw.api.webservice.maintenancetools.ProcessStatus;
import framework.integrations.gwServices.maintenanceToolsAPI.cc.com.guidewire.gw.api.webservice.maintenancetools.WQueueStatus;
import framework.integrations.gwServices.maintenanceToolsAPI.cc.com.guidewire.gw.api.webservice.maintenancetools.WorkQueueConfig;

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
@WebService(name = "MaintenanceToolsAPIPortType", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI")
@XmlSeeAlso({
    framework.integrations.gwServices.maintenanceToolsAPI.cc.com.guidewire.cc.ws.gw.webservice.cc.maintenancetoolsapi.ObjectFactory.class,
    framework.integrations.gwServices.maintenanceToolsAPI.cc.com.guidewire.gw.api.webservice.maintenancetools.ObjectFactory.class,
    framework.integrations.gwServices.maintenanceToolsAPI.cc.com.guidewire.gw.api.tools.ObjectFactory.class,
    framework.integrations.gwServices.soapheaders.ObjectFactory.class
})
public interface MaintenanceToolsAPIPortType {


    /**
     * 
     * @return
     *     returns framework.integrations.gwServices.maintenanceToolsAPI.cc.com.guidewire.cc.ws.gw.webservice.cc.maintenancetoolsapi.GetValidBatchProcessNamesResponse.Return
     * @throws WsiAuthenticationException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI")
    @RequestWrapper(localName = "getValidBatchProcessNames", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.cc.com.guidewire.cc.ws.gw.webservice.cc.maintenancetoolsapi.GetValidBatchProcessNames")
    @ResponseWrapper(localName = "getValidBatchProcessNamesResponse", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.cc.com.guidewire.cc.ws.gw.webservice.cc.maintenancetoolsapi.GetValidBatchProcessNamesResponse")
    public framework.integrations.gwServices.maintenanceToolsAPI.cc.com.guidewire.cc.ws.gw.webservice.cc.maintenancetoolsapi.GetValidBatchProcessNamesResponse.Return getValidBatchProcessNames()
        throws WsiAuthenticationException_Exception
    ;

    /**
     * 
     * @param processName
     * @return
     *     returns boolean
     * @throws WsiAuthenticationException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI")
    @RequestWrapper(localName = "isBatchProcessNameValid", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.cc.com.guidewire.cc.ws.gw.webservice.cc.maintenancetoolsapi.IsBatchProcessNameValid")
    @ResponseWrapper(localName = "isBatchProcessNameValidResponse", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.cc.com.guidewire.cc.ws.gw.webservice.cc.maintenancetoolsapi.IsBatchProcessNameValidResponse")
    public boolean isBatchProcessNameValid(
        @WebParam(name = "processName", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI")
        String processName)
        throws WsiAuthenticationException_Exception
    ;

    /**
     * 
     * @param processName
     * @return
     *     returns framework.integrations.gwServices.maintenanceToolsAPI.cc.com.guidewire.gw.api.tools.ProcessID
     * @throws WsiAuthenticationException_Exception
     * @throws IllegalArgumentException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI")
    @RequestWrapper(localName = "startBatchProcess", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.cc.com.guidewire.cc.ws.gw.webservice.cc.maintenancetoolsapi.StartBatchProcess")
    @ResponseWrapper(localName = "startBatchProcessResponse", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.cc.com.guidewire.cc.ws.gw.webservice.cc.maintenancetoolsapi.StartBatchProcessResponse")
    public ProcessID startBatchProcess(
        @WebParam(name = "processName", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI")
        String processName)
        throws IllegalArgumentException_Exception, WsiAuthenticationException_Exception
    ;

    /**
     * 
     * @return
     *     returns javax.xml.datatype.XMLGregorianCalendar
     * @throws SOAPException_Exception
     * @throws WsiAuthenticationException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI")
    @RequestWrapper(localName = "whenStatsCalculated", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.cc.com.guidewire.cc.ws.gw.webservice.cc.maintenancetoolsapi.WhenStatsCalculated")
    @ResponseWrapper(localName = "whenStatsCalculatedResponse", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.cc.com.guidewire.cc.ws.gw.webservice.cc.maintenancetoolsapi.WhenStatsCalculatedResponse")
    public XMLGregorianCalendar whenStatsCalculated()
        throws SOAPException_Exception, WsiAuthenticationException_Exception
    ;

    /**
     * 
     * @param claimNumbers
     * @return
     *     returns java.lang.String
     * @throws SOAPException_Exception
     * @throws WsiAuthenticationException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI")
    @RequestWrapper(localName = "scheduleAggLimitRebuildOfClaims", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.cc.com.guidewire.cc.ws.gw.webservice.cc.maintenancetoolsapi.ScheduleAggLimitRebuildOfClaims")
    @ResponseWrapper(localName = "scheduleAggLimitRebuildOfClaimsResponse", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.cc.com.guidewire.cc.ws.gw.webservice.cc.maintenancetoolsapi.ScheduleAggLimitRebuildOfClaimsResponse")
    public String scheduleAggLimitRebuildOfClaims(
        @WebParam(name = "claimNumbers", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI")
        framework.integrations.gwServices.maintenanceToolsAPI.cc.com.guidewire.cc.ws.gw.webservice.cc.maintenancetoolsapi.ScheduleAggLimitRebuildOfClaims.ClaimNumbers claimNumbers)
        throws SOAPException_Exception, WsiAuthenticationException_Exception
    ;

    /**
     * 
     * @param policyNumbers
     * @return
     *     returns java.lang.String
     * @throws SOAPException_Exception
     * @throws WsiAuthenticationException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI")
    @RequestWrapper(localName = "scheduleAggLimitRebuildOfPolicies", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.cc.com.guidewire.cc.ws.gw.webservice.cc.maintenancetoolsapi.ScheduleAggLimitRebuildOfPolicies")
    @ResponseWrapper(localName = "scheduleAggLimitRebuildOfPoliciesResponse", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.cc.com.guidewire.cc.ws.gw.webservice.cc.maintenancetoolsapi.ScheduleAggLimitRebuildOfPoliciesResponse")
    public String scheduleAggLimitRebuildOfPolicies(
        @WebParam(name = "policyNumbers", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI")
        framework.integrations.gwServices.maintenanceToolsAPI.cc.com.guidewire.cc.ws.gw.webservice.cc.maintenancetoolsapi.ScheduleAggLimitRebuildOfPolicies.PolicyNumbers policyNumbers)
        throws SOAPException_Exception, WsiAuthenticationException_Exception
    ;

    /**
     * 
     * @return
     *     returns java.lang.String
     * @throws SOAPException_Exception
     * @throws WsiAuthenticationException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI")
    @RequestWrapper(localName = "scheduleAggLimitRebuildAllLimits", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.cc.com.guidewire.cc.ws.gw.webservice.cc.maintenancetoolsapi.ScheduleAggLimitRebuildAllLimits")
    @ResponseWrapper(localName = "scheduleAggLimitRebuildAllLimitsResponse", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.cc.com.guidewire.cc.ws.gw.webservice.cc.maintenancetoolsapi.ScheduleAggLimitRebuildAllLimitsResponse")
    public String scheduleAggLimitRebuildAllLimits()
        throws SOAPException_Exception, WsiAuthenticationException_Exception
    ;

    /**
     * 
     * @return
     *     returns java.lang.String
     * @throws SOAPException_Exception
     * @throws WsiAuthenticationException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI")
    @RequestWrapper(localName = "scheduleAggLimitRebuildInvalidLimits", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.cc.com.guidewire.cc.ws.gw.webservice.cc.maintenancetoolsapi.ScheduleAggLimitRebuildInvalidLimits")
    @ResponseWrapper(localName = "scheduleAggLimitRebuildInvalidLimitsResponse", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.cc.com.guidewire.cc.ws.gw.webservice.cc.maintenancetoolsapi.ScheduleAggLimitRebuildInvalidLimitsResponse")
    public String scheduleAggLimitRebuildInvalidLimits()
        throws SOAPException_Exception, WsiAuthenticationException_Exception
    ;

    /**
     * 
     * @param claimNumbers
     * @param purgeFromAggregateLimit
     * @return
     *     returns java.lang.String
     * @throws SOAPException_Exception
     * @throws WsiAuthenticationException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI")
    @RequestWrapper(localName = "markPurgeReady", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.cc.com.guidewire.cc.ws.gw.webservice.cc.maintenancetoolsapi.MarkPurgeReady")
    @ResponseWrapper(localName = "markPurgeReadyResponse", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.cc.com.guidewire.cc.ws.gw.webservice.cc.maintenancetoolsapi.MarkPurgeReadyResponse")
    public String markPurgeReady(
        @WebParam(name = "claimNumbers", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI")
        framework.integrations.gwServices.maintenanceToolsAPI.cc.com.guidewire.cc.ws.gw.webservice.cc.maintenancetoolsapi.MarkPurgeReady.ClaimNumbers claimNumbers,
        @WebParam(name = "purgeFromAggregateLimit", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI")
        boolean purgeFromAggregateLimit)
        throws SOAPException_Exception, WsiAuthenticationException_Exception
    ;

    /**
     * 
     * @param claimNumbers
     * @return
     *     returns java.lang.String
     * @throws SOAPException_Exception
     * @throws WsiAuthenticationException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI")
    @RequestWrapper(localName = "scheduleForArchive", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.cc.com.guidewire.cc.ws.gw.webservice.cc.maintenancetoolsapi.ScheduleForArchive")
    @ResponseWrapper(localName = "scheduleForArchiveResponse", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.cc.com.guidewire.cc.ws.gw.webservice.cc.maintenancetoolsapi.ScheduleForArchiveResponse")
    public String scheduleForArchive(
        @WebParam(name = "claimNumbers", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI")
        framework.integrations.gwServices.maintenanceToolsAPI.cc.com.guidewire.cc.ws.gw.webservice.cc.maintenancetoolsapi.ScheduleForArchive.ClaimNumbers claimNumbers)
        throws SOAPException_Exception, WsiAuthenticationException_Exception
    ;

    /**
     * 
     * @param claimNumbers
     * @param comment
     * @return
     *     returns java.lang.String
     * @throws SOAPException_Exception
     * @throws WsiAuthenticationException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI")
    @RequestWrapper(localName = "restore", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.cc.com.guidewire.cc.ws.gw.webservice.cc.maintenancetoolsapi.Restore")
    @ResponseWrapper(localName = "restoreResponse", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.cc.com.guidewire.cc.ws.gw.webservice.cc.maintenancetoolsapi.RestoreResponse")
    public String restore(
        @WebParam(name = "claimNumbers", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI")
        framework.integrations.gwServices.maintenanceToolsAPI.cc.com.guidewire.cc.ws.gw.webservice.cc.maintenancetoolsapi.Restore.ClaimNumbers claimNumbers,
        @WebParam(name = "comment", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI")
        String comment)
        throws SOAPException_Exception, WsiAuthenticationException_Exception
    ;

    /**
     * 
     * @param processName
     * @return
     *     returns boolean
     * @throws WsiAuthenticationException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI")
    @RequestWrapper(localName = "requestTerminationOfBatchProcessByName", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.cc.com.guidewire.cc.ws.gw.webservice.cc.maintenancetoolsapi.RequestTerminationOfBatchProcessByName")
    @ResponseWrapper(localName = "requestTerminationOfBatchProcessByNameResponse", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.cc.com.guidewire.cc.ws.gw.webservice.cc.maintenancetoolsapi.RequestTerminationOfBatchProcessByNameResponse")
    public boolean requestTerminationOfBatchProcessByName(
        @WebParam(name = "processName", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI")
        String processName)
        throws WsiAuthenticationException_Exception
    ;

    /**
     * 
     * @param pid
     * @return
     *     returns boolean
     * @throws WsiAuthenticationException_Exception
     * @throws IllegalArgumentException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI")
    @RequestWrapper(localName = "requestTerminationOfBatchProcessByID", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.cc.com.guidewire.cc.ws.gw.webservice.cc.maintenancetoolsapi.RequestTerminationOfBatchProcessByID")
    @ResponseWrapper(localName = "requestTerminationOfBatchProcessByIDResponse", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.cc.com.guidewire.cc.ws.gw.webservice.cc.maintenancetoolsapi.RequestTerminationOfBatchProcessByIDResponse")
    public boolean requestTerminationOfBatchProcessByID(
        @WebParam(name = "pid", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI")
        ProcessID pid)
        throws IllegalArgumentException_Exception, WsiAuthenticationException_Exception
    ;

    /**
     * 
     * @param processName
     * @return
     *     returns framework.integrations.gwServices.maintenanceToolsAPI.cc.com.guidewire.gw.api.webservice.maintenancetools.ProcessStatus
     * @throws WsiAuthenticationException_Exception
     * @throws IllegalArgumentException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI")
    @RequestWrapper(localName = "batchProcessStatusByName", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.cc.com.guidewire.cc.ws.gw.webservice.cc.maintenancetoolsapi.BatchProcessStatusByName")
    @ResponseWrapper(localName = "batchProcessStatusByNameResponse", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.cc.com.guidewire.cc.ws.gw.webservice.cc.maintenancetoolsapi.BatchProcessStatusByNameResponse")
    public ProcessStatus batchProcessStatusByName(
        @WebParam(name = "processName", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI")
        String processName)
        throws IllegalArgumentException_Exception, WsiAuthenticationException_Exception
    ;

    /**
     * 
     * @param pid
     * @return
     *     returns framework.integrations.gwServices.maintenanceToolsAPI.cc.com.guidewire.gw.api.webservice.maintenancetools.ProcessStatus
     * @throws WsiAuthenticationException_Exception
     * @throws IllegalArgumentException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI")
    @RequestWrapper(localName = "batchProcessStatusByID", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.cc.com.guidewire.cc.ws.gw.webservice.cc.maintenancetoolsapi.BatchProcessStatusByID")
    @ResponseWrapper(localName = "batchProcessStatusByIDResponse", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.cc.com.guidewire.cc.ws.gw.webservice.cc.maintenancetoolsapi.BatchProcessStatusByIDResponse")
    public ProcessStatus batchProcessStatusByID(
        @WebParam(name = "pid", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI")
        ProcessID pid)
        throws IllegalArgumentException_Exception, WsiAuthenticationException_Exception
    ;

    /**
     * 
     * @param queueName
     * @return
     *     returns framework.integrations.gwServices.maintenanceToolsAPI.cc.com.guidewire.gw.api.webservice.maintenancetools.WorkQueueConfig
     * @throws WsiAuthenticationException_Exception
     * @throws IllegalArgumentException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI")
    @RequestWrapper(localName = "getWorkQueueConfig", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.cc.com.guidewire.cc.ws.gw.webservice.cc.maintenancetoolsapi.GetWorkQueueConfig")
    @ResponseWrapper(localName = "getWorkQueueConfigResponse", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.cc.com.guidewire.cc.ws.gw.webservice.cc.maintenancetoolsapi.GetWorkQueueConfigResponse")
    public WorkQueueConfig getWorkQueueConfig(
        @WebParam(name = "queueName", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI")
        String queueName)
        throws IllegalArgumentException_Exception, WsiAuthenticationException_Exception
    ;

    /**
     * 
     * @param config
     * @param queueName
     * @throws WsiAuthenticationException_Exception
     * @throws IllegalArgumentException_Exception
     */
    @WebMethod
    @RequestWrapper(localName = "setWorkQueueConfig", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.cc.com.guidewire.cc.ws.gw.webservice.cc.maintenancetoolsapi.SetWorkQueueConfig")
    @ResponseWrapper(localName = "setWorkQueueConfigResponse", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.cc.com.guidewire.cc.ws.gw.webservice.cc.maintenancetoolsapi.SetWorkQueueConfigResponse")
    public void setWorkQueueConfig(
        @WebParam(name = "queueName", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI")
        String queueName,
        @WebParam(name = "config", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI")
        WorkQueueConfig config)
        throws IllegalArgumentException_Exception, WsiAuthenticationException_Exception
    ;

    /**
     * 
     * @return
     *     returns framework.integrations.gwServices.maintenanceToolsAPI.cc.com.guidewire.cc.ws.gw.webservice.cc.maintenancetoolsapi.GetWorkQueueNamesResponse.Return
     * @throws WsiAuthenticationException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI")
    @RequestWrapper(localName = "getWorkQueueNames", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.cc.com.guidewire.cc.ws.gw.webservice.cc.maintenancetoolsapi.GetWorkQueueNames")
    @ResponseWrapper(localName = "getWorkQueueNamesResponse", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.cc.com.guidewire.cc.ws.gw.webservice.cc.maintenancetoolsapi.GetWorkQueueNamesResponse")
    public framework.integrations.gwServices.maintenanceToolsAPI.cc.com.guidewire.cc.ws.gw.webservice.cc.maintenancetoolsapi.GetWorkQueueNamesResponse.Return getWorkQueueNames()
        throws WsiAuthenticationException_Exception
    ;

    /**
     * 
     * @param queueName
     * @throws WsiAuthenticationException_Exception
     * @throws IllegalArgumentException_Exception
     */
    @WebMethod
    @RequestWrapper(localName = "notifyQueueWorkers", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.cc.com.guidewire.cc.ws.gw.webservice.cc.maintenancetoolsapi.NotifyQueueWorkers")
    @ResponseWrapper(localName = "notifyQueueWorkersResponse", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.cc.com.guidewire.cc.ws.gw.webservice.cc.maintenancetoolsapi.NotifyQueueWorkersResponse")
    public void notifyQueueWorkers(
        @WebParam(name = "queueName", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI")
        String queueName)
        throws IllegalArgumentException_Exception, WsiAuthenticationException_Exception
    ;

    /**
     * 
     * @param queueName
     * @throws WsiAuthenticationException_Exception
     * @throws IllegalArgumentException_Exception
     */
    @WebMethod
    @RequestWrapper(localName = "stopWorkQueueWorkers", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.cc.com.guidewire.cc.ws.gw.webservice.cc.maintenancetoolsapi.StopWorkQueueWorkers")
    @ResponseWrapper(localName = "stopWorkQueueWorkersResponse", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.cc.com.guidewire.cc.ws.gw.webservice.cc.maintenancetoolsapi.StopWorkQueueWorkersResponse")
    public void stopWorkQueueWorkers(
        @WebParam(name = "queueName", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI")
        String queueName)
        throws IllegalArgumentException_Exception, WsiAuthenticationException_Exception
    ;

    /**
     * 
     * @param queueName
     * @throws WsiAuthenticationException_Exception
     * @throws IllegalArgumentException_Exception
     */
    @WebMethod
    @RequestWrapper(localName = "startWorkQueueWorkers", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.cc.com.guidewire.cc.ws.gw.webservice.cc.maintenancetoolsapi.StartWorkQueueWorkers")
    @ResponseWrapper(localName = "startWorkQueueWorkersResponse", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.cc.com.guidewire.cc.ws.gw.webservice.cc.maintenancetoolsapi.StartWorkQueueWorkersResponse")
    public void startWorkQueueWorkers(
        @WebParam(name = "queueName", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI")
        String queueName)
        throws IllegalArgumentException_Exception, WsiAuthenticationException_Exception
    ;

    /**
     * 
     * @param queueName
     * @return
     *     returns framework.integrations.gwServices.maintenanceToolsAPI.cc.com.guidewire.gw.api.webservice.maintenancetools.WQueueStatus
     * @throws WsiAuthenticationException_Exception
     * @throws IllegalArgumentException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI")
    @RequestWrapper(localName = "getWQueueStatus", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.cc.com.guidewire.cc.ws.gw.webservice.cc.maintenancetoolsapi.GetWQueueStatus")
    @ResponseWrapper(localName = "getWQueueStatusResponse", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.cc.com.guidewire.cc.ws.gw.webservice.cc.maintenancetoolsapi.GetWQueueStatusResponse")
    public WQueueStatus getWQueueStatus(
        @WebParam(name = "queueName", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI")
        String queueName)
        throws IllegalArgumentException_Exception, WsiAuthenticationException_Exception
    ;

    /**
     * 
     * @param queueName
     * @return
     *     returns int
     * @throws WsiAuthenticationException_Exception
     * @throws IllegalArgumentException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI")
    @RequestWrapper(localName = "getNumActiveWorkItems", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.cc.com.guidewire.cc.ws.gw.webservice.cc.maintenancetoolsapi.GetNumActiveWorkItems")
    @ResponseWrapper(localName = "getNumActiveWorkItemsResponse", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.cc.com.guidewire.cc.ws.gw.webservice.cc.maintenancetoolsapi.GetNumActiveWorkItemsResponse")
    public int getNumActiveWorkItems(
        @WebParam(name = "queueName", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI")
        String queueName)
        throws IllegalArgumentException_Exception, WsiAuthenticationException_Exception
    ;

    /**
     * 
     * @param queueName
     * @return
     *     returns boolean
     * @throws WsiAuthenticationException_Exception
     * @throws IllegalArgumentException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI")
    @RequestWrapper(localName = "waitOnActiveWorkItems", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.cc.com.guidewire.cc.ws.gw.webservice.cc.maintenancetoolsapi.WaitOnActiveWorkItems")
    @ResponseWrapper(localName = "waitOnActiveWorkItemsResponse", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.cc.com.guidewire.cc.ws.gw.webservice.cc.maintenancetoolsapi.WaitOnActiveWorkItemsResponse")
    public boolean waitOnActiveWorkItems(
        @WebParam(name = "queueName", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI")
        String queueName)
        throws IllegalArgumentException_Exception, WsiAuthenticationException_Exception
    ;

    /**
     * 
     * @param pluginName
     * @return
     *     returns boolean
     * @throws WsiAuthenticationException_Exception
     * @throws IllegalArgumentException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI")
    @RequestWrapper(localName = "isPluginStarted", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.cc.com.guidewire.cc.ws.gw.webservice.cc.maintenancetoolsapi.IsPluginStarted")
    @ResponseWrapper(localName = "isPluginStartedResponse", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.cc.com.guidewire.cc.ws.gw.webservice.cc.maintenancetoolsapi.IsPluginStartedResponse")
    public boolean isPluginStarted(
        @WebParam(name = "pluginName", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI")
        String pluginName)
        throws IllegalArgumentException_Exception, WsiAuthenticationException_Exception
    ;

    /**
     * 
     * @param pluginName
     * @throws WsiAuthenticationException_Exception
     * @throws IllegalArgumentException_Exception
     */
    @WebMethod
    @RequestWrapper(localName = "startPlugin", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.cc.com.guidewire.cc.ws.gw.webservice.cc.maintenancetoolsapi.StartPlugin")
    @ResponseWrapper(localName = "startPluginResponse", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.cc.com.guidewire.cc.ws.gw.webservice.cc.maintenancetoolsapi.StartPluginResponse")
    public void startPlugin(
        @WebParam(name = "pluginName", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI")
        String pluginName)
        throws IllegalArgumentException_Exception, WsiAuthenticationException_Exception
    ;

    /**
     * 
     * @param pluginName
     * @throws WsiAuthenticationException_Exception
     * @throws IllegalArgumentException_Exception
     */
    @WebMethod
    @RequestWrapper(localName = "stopPlugin", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.cc.com.guidewire.cc.ws.gw.webservice.cc.maintenancetoolsapi.StopPlugin")
    @ResponseWrapper(localName = "stopPluginResponse", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.cc.com.guidewire.cc.ws.gw.webservice.cc.maintenancetoolsapi.StopPluginResponse")
    public void stopPlugin(
        @WebParam(name = "pluginName", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI")
        String pluginName)
        throws IllegalArgumentException_Exception, WsiAuthenticationException_Exception
    ;

    /**
     * 
     * @param publicID
     * @param targetType
     * @throws WebServiceException_Exception
     * @throws WsiAuthenticationException_Exception
     * @throws IllegalArgumentException_Exception
     */
    @WebMethod
    @RequestWrapper(localName = "changeSubtype", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.cc.com.guidewire.cc.ws.gw.webservice.cc.maintenancetoolsapi.ChangeSubtype")
    @ResponseWrapper(localName = "changeSubtypeResponse", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.cc.com.guidewire.cc.ws.gw.webservice.cc.maintenancetoolsapi.ChangeSubtypeResponse")
    public void changeSubtype(
        @WebParam(name = "publicID", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI")
        String publicID,
        @WebParam(name = "targetType", targetNamespace = "http://guidewire.com/cc/ws/gw/webservice/cc/MaintenanceToolsAPI")
        String targetType)
        throws IllegalArgumentException_Exception, WebServiceException_Exception, WsiAuthenticationException_Exception
    ;

}