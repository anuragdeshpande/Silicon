
package framework.integrations.gwServices.maintenanceToolsAPI.ab.com.guidewire.ab.ws.gw.webservice.ab.ab801.maintenancetoolsapi;

import framework.integrations.gwServices.maintenanceToolsAPI.ab.com.guidewire.gw.api.tools.ProcessID;
import framework.integrations.gwServices.maintenanceToolsAPI.ab.com.guidewire.gw.api.webservice.maintenancetools.ProcessStatus;
import framework.integrations.gwServices.maintenanceToolsAPI.ab.com.guidewire.gw.api.webservice.maintenancetools.WQueueStatus;
import framework.integrations.gwServices.maintenanceToolsAPI.ab.com.guidewire.gw.api.webservice.maintenancetools.WorkQueueConfig;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "MaintenanceToolsAPIPortType", targetNamespace = "http://guidewire.com/ab/ws/gw/webservice/ab/ab801/MaintenanceToolsAPI")
@XmlSeeAlso({
    framework.integrations.gwServices.maintenanceToolsAPI.ab.com.guidewire.ab.ws.gw.webservice.ab.ab801.maintenancetoolsapi.ObjectFactory.class,
    framework.integrations.gwServices.maintenanceToolsAPI.ab.com.guidewire.gw.api.webservice.maintenancetools.ObjectFactory.class,
    framework.integrations.gwServices.maintenanceToolsAPI.ab.com.guidewire.gw.api.tools.ObjectFactory.class,
    framework.integrations.gwServices.soapheaders.ObjectFactory.class
})
public interface MaintenanceToolsAPIPortType {


    /**
     * 
     * @return
     *     returns framework.integrations.gwServices.maintenanceToolsAPI.ab.com.guidewire.ab.ws.gw.webservice.ab.ab801.maintenancetoolsapi.GetValidBatchProcessNamesResponse.Return
     * @throws WsiAuthenticationException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "http://guidewire.com/ab/ws/gw/webservice/ab/ab801/MaintenanceToolsAPI")
    @RequestWrapper(localName = "getValidBatchProcessNames", targetNamespace = "http://guidewire.com/ab/ws/gw/webservice/ab/ab801/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.ab.com.guidewire.ab.ws.gw.webservice.ab.ab801.maintenancetoolsapi.GetValidBatchProcessNames")
    @ResponseWrapper(localName = "getValidBatchProcessNamesResponse", targetNamespace = "http://guidewire.com/ab/ws/gw/webservice/ab/ab801/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.ab.com.guidewire.ab.ws.gw.webservice.ab.ab801.maintenancetoolsapi.GetValidBatchProcessNamesResponse")
    public framework.integrations.gwServices.maintenanceToolsAPI.ab.com.guidewire.ab.ws.gw.webservice.ab.ab801.maintenancetoolsapi.GetValidBatchProcessNamesResponse.Return getValidBatchProcessNames()
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
    @WebResult(targetNamespace = "http://guidewire.com/ab/ws/gw/webservice/ab/ab801/MaintenanceToolsAPI")
    @RequestWrapper(localName = "isBatchProcessNameValid", targetNamespace = "http://guidewire.com/ab/ws/gw/webservice/ab/ab801/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.ab.com.guidewire.ab.ws.gw.webservice.ab.ab801.maintenancetoolsapi.IsBatchProcessNameValid")
    @ResponseWrapper(localName = "isBatchProcessNameValidResponse", targetNamespace = "http://guidewire.com/ab/ws/gw/webservice/ab/ab801/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.ab.com.guidewire.ab.ws.gw.webservice.ab.ab801.maintenancetoolsapi.IsBatchProcessNameValidResponse")
    public boolean isBatchProcessNameValid(
        @WebParam(name = "processName", targetNamespace = "http://guidewire.com/ab/ws/gw/webservice/ab/ab801/MaintenanceToolsAPI")
        String processName)
        throws WsiAuthenticationException_Exception
    ;

    /**
     * 
     * @param processName
     * @return
     *     returns framework.integrations.gwServices.maintenanceToolsAPI.ab.com.guidewire.gw.api.tools.ProcessID
     * @throws WsiAuthenticationException_Exception
     * @throws IllegalArgumentException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "http://guidewire.com/ab/ws/gw/webservice/ab/ab801/MaintenanceToolsAPI")
    @RequestWrapper(localName = "startBatchProcess", targetNamespace = "http://guidewire.com/ab/ws/gw/webservice/ab/ab801/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.ab.com.guidewire.ab.ws.gw.webservice.ab.ab801.maintenancetoolsapi.StartBatchProcess")
    @ResponseWrapper(localName = "startBatchProcessResponse", targetNamespace = "http://guidewire.com/ab/ws/gw/webservice/ab/ab801/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.ab.com.guidewire.ab.ws.gw.webservice.ab.ab801.maintenancetoolsapi.StartBatchProcessResponse")
    public ProcessID startBatchProcess(
        @WebParam(name = "processName", targetNamespace = "http://guidewire.com/ab/ws/gw/webservice/ab/ab801/MaintenanceToolsAPI")
        String processName)
        throws framework.integrations.gwServices.maintenanceToolsAPI.ab.com.guidewire.ab.ws.gw.webservice.ab.ab801.maintenancetoolsapi.IllegalArgumentException_Exception, WsiAuthenticationException_Exception
    ;

    /**
     * 
     * @param processName
     * @return
     *     returns boolean
     * @throws WsiAuthenticationException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "http://guidewire.com/ab/ws/gw/webservice/ab/ab801/MaintenanceToolsAPI")
    @RequestWrapper(localName = "requestTerminationOfBatchProcessByName", targetNamespace = "http://guidewire.com/ab/ws/gw/webservice/ab/ab801/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.ab.com.guidewire.ab.ws.gw.webservice.ab.ab801.maintenancetoolsapi.RequestTerminationOfBatchProcessByName")
    @ResponseWrapper(localName = "requestTerminationOfBatchProcessByNameResponse", targetNamespace = "http://guidewire.com/ab/ws/gw/webservice/ab/ab801/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.ab.com.guidewire.ab.ws.gw.webservice.ab.ab801.maintenancetoolsapi.RequestTerminationOfBatchProcessByNameResponse")
    public boolean requestTerminationOfBatchProcessByName(
        @WebParam(name = "processName", targetNamespace = "http://guidewire.com/ab/ws/gw/webservice/ab/ab801/MaintenanceToolsAPI")
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
    @WebResult(targetNamespace = "http://guidewire.com/ab/ws/gw/webservice/ab/ab801/MaintenanceToolsAPI")
    @RequestWrapper(localName = "requestTerminationOfBatchProcessByID", targetNamespace = "http://guidewire.com/ab/ws/gw/webservice/ab/ab801/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.ab.com.guidewire.ab.ws.gw.webservice.ab.ab801.maintenancetoolsapi.RequestTerminationOfBatchProcessByID")
    @ResponseWrapper(localName = "requestTerminationOfBatchProcessByIDResponse", targetNamespace = "http://guidewire.com/ab/ws/gw/webservice/ab/ab801/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.ab.com.guidewire.ab.ws.gw.webservice.ab.ab801.maintenancetoolsapi.RequestTerminationOfBatchProcessByIDResponse")
    public boolean requestTerminationOfBatchProcessByID(
        @WebParam(name = "pid", targetNamespace = "http://guidewire.com/ab/ws/gw/webservice/ab/ab801/MaintenanceToolsAPI")
        ProcessID pid)
        throws framework.integrations.gwServices.maintenanceToolsAPI.ab.com.guidewire.ab.ws.gw.webservice.ab.ab801.maintenancetoolsapi.IllegalArgumentException_Exception, WsiAuthenticationException_Exception
    ;

    /**
     * 
     * @param processName
     * @return
     *     returns framework.integrations.gwServices.maintenanceToolsAPI.ab.com.guidewire.gw.api.webservice.maintenancetools.ProcessStatus
     * @throws WsiAuthenticationException_Exception
     * @throws IllegalArgumentException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "http://guidewire.com/ab/ws/gw/webservice/ab/ab801/MaintenanceToolsAPI")
    @RequestWrapper(localName = "batchProcessStatusByName", targetNamespace = "http://guidewire.com/ab/ws/gw/webservice/ab/ab801/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.ab.com.guidewire.ab.ws.gw.webservice.ab.ab801.maintenancetoolsapi.BatchProcessStatusByName")
    @ResponseWrapper(localName = "batchProcessStatusByNameResponse", targetNamespace = "http://guidewire.com/ab/ws/gw/webservice/ab/ab801/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.ab.com.guidewire.ab.ws.gw.webservice.ab.ab801.maintenancetoolsapi.BatchProcessStatusByNameResponse")
    public ProcessStatus batchProcessStatusByName(
        @WebParam(name = "processName", targetNamespace = "http://guidewire.com/ab/ws/gw/webservice/ab/ab801/MaintenanceToolsAPI")
        String processName)
        throws framework.integrations.gwServices.maintenanceToolsAPI.ab.com.guidewire.ab.ws.gw.webservice.ab.ab801.maintenancetoolsapi.IllegalArgumentException_Exception, WsiAuthenticationException_Exception
    ;

    /**
     * 
     * @param pid
     * @return
     *     returns framework.integrations.gwServices.maintenanceToolsAPI.ab.com.guidewire.gw.api.webservice.maintenancetools.ProcessStatus
     * @throws WsiAuthenticationException_Exception
     * @throws IllegalArgumentException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "http://guidewire.com/ab/ws/gw/webservice/ab/ab801/MaintenanceToolsAPI")
    @RequestWrapper(localName = "batchProcessStatusByID", targetNamespace = "http://guidewire.com/ab/ws/gw/webservice/ab/ab801/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.ab.com.guidewire.ab.ws.gw.webservice.ab.ab801.maintenancetoolsapi.BatchProcessStatusByID")
    @ResponseWrapper(localName = "batchProcessStatusByIDResponse", targetNamespace = "http://guidewire.com/ab/ws/gw/webservice/ab/ab801/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.ab.com.guidewire.ab.ws.gw.webservice.ab.ab801.maintenancetoolsapi.BatchProcessStatusByIDResponse")
    public ProcessStatus batchProcessStatusByID(
        @WebParam(name = "pid", targetNamespace = "http://guidewire.com/ab/ws/gw/webservice/ab/ab801/MaintenanceToolsAPI")
        ProcessID pid)
        throws framework.integrations.gwServices.maintenanceToolsAPI.ab.com.guidewire.ab.ws.gw.webservice.ab.ab801.maintenancetoolsapi.IllegalArgumentException_Exception, WsiAuthenticationException_Exception
    ;

    /**
     * 
     * @param queueName
     * @return
     *     returns framework.integrations.gwServices.maintenanceToolsAPI.ab.com.guidewire.gw.api.webservice.maintenancetools.WorkQueueConfig
     * @throws WsiAuthenticationException_Exception
     * @throws IllegalArgumentException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "http://guidewire.com/ab/ws/gw/webservice/ab/ab801/MaintenanceToolsAPI")
    @RequestWrapper(localName = "getWorkQueueConfig", targetNamespace = "http://guidewire.com/ab/ws/gw/webservice/ab/ab801/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.ab.com.guidewire.ab.ws.gw.webservice.ab.ab801.maintenancetoolsapi.GetWorkQueueConfig")
    @ResponseWrapper(localName = "getWorkQueueConfigResponse", targetNamespace = "http://guidewire.com/ab/ws/gw/webservice/ab/ab801/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.ab.com.guidewire.ab.ws.gw.webservice.ab.ab801.maintenancetoolsapi.GetWorkQueueConfigResponse")
    public WorkQueueConfig getWorkQueueConfig(
        @WebParam(name = "queueName", targetNamespace = "http://guidewire.com/ab/ws/gw/webservice/ab/ab801/MaintenanceToolsAPI")
        String queueName)
        throws framework.integrations.gwServices.maintenanceToolsAPI.ab.com.guidewire.ab.ws.gw.webservice.ab.ab801.maintenancetoolsapi.IllegalArgumentException_Exception, WsiAuthenticationException_Exception
    ;

    /**
     * 
     * @param config
     * @param queueName
     * @throws WsiAuthenticationException_Exception
     * @throws IllegalArgumentException_Exception
     */
    @WebMethod
    @RequestWrapper(localName = "setWorkQueueConfig", targetNamespace = "http://guidewire.com/ab/ws/gw/webservice/ab/ab801/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.ab.com.guidewire.ab.ws.gw.webservice.ab.ab801.maintenancetoolsapi.SetWorkQueueConfig")
    @ResponseWrapper(localName = "setWorkQueueConfigResponse", targetNamespace = "http://guidewire.com/ab/ws/gw/webservice/ab/ab801/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.ab.com.guidewire.ab.ws.gw.webservice.ab.ab801.maintenancetoolsapi.SetWorkQueueConfigResponse")
    public void setWorkQueueConfig(
        @WebParam(name = "queueName", targetNamespace = "http://guidewire.com/ab/ws/gw/webservice/ab/ab801/MaintenanceToolsAPI")
        String queueName,
        @WebParam(name = "config", targetNamespace = "http://guidewire.com/ab/ws/gw/webservice/ab/ab801/MaintenanceToolsAPI")
        WorkQueueConfig config)
        throws framework.integrations.gwServices.maintenanceToolsAPI.ab.com.guidewire.ab.ws.gw.webservice.ab.ab801.maintenancetoolsapi.IllegalArgumentException_Exception, WsiAuthenticationException_Exception
    ;

    /**
     * 
     * @return
     *     returns framework.integrations.gwServices.maintenanceToolsAPI.ab.com.guidewire.ab.ws.gw.webservice.ab.ab801.maintenancetoolsapi.GetWorkQueueNamesResponse.Return
     * @throws WsiAuthenticationException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "http://guidewire.com/ab/ws/gw/webservice/ab/ab801/MaintenanceToolsAPI")
    @RequestWrapper(localName = "getWorkQueueNames", targetNamespace = "http://guidewire.com/ab/ws/gw/webservice/ab/ab801/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.ab.com.guidewire.ab.ws.gw.webservice.ab.ab801.maintenancetoolsapi.GetWorkQueueNames")
    @ResponseWrapper(localName = "getWorkQueueNamesResponse", targetNamespace = "http://guidewire.com/ab/ws/gw/webservice/ab/ab801/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.ab.com.guidewire.ab.ws.gw.webservice.ab.ab801.maintenancetoolsapi.GetWorkQueueNamesResponse")
    public framework.integrations.gwServices.maintenanceToolsAPI.ab.com.guidewire.ab.ws.gw.webservice.ab.ab801.maintenancetoolsapi.GetWorkQueueNamesResponse.Return getWorkQueueNames()
        throws WsiAuthenticationException_Exception
    ;

    /**
     * 
     * @param queueName
     * @throws WsiAuthenticationException_Exception
     * @throws IllegalArgumentException_Exception
     */
    @WebMethod
    @RequestWrapper(localName = "notifyQueueWorkers", targetNamespace = "http://guidewire.com/ab/ws/gw/webservice/ab/ab801/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.ab.com.guidewire.ab.ws.gw.webservice.ab.ab801.maintenancetoolsapi.NotifyQueueWorkers")
    @ResponseWrapper(localName = "notifyQueueWorkersResponse", targetNamespace = "http://guidewire.com/ab/ws/gw/webservice/ab/ab801/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.ab.com.guidewire.ab.ws.gw.webservice.ab.ab801.maintenancetoolsapi.NotifyQueueWorkersResponse")
    public void notifyQueueWorkers(
        @WebParam(name = "queueName", targetNamespace = "http://guidewire.com/ab/ws/gw/webservice/ab/ab801/MaintenanceToolsAPI")
        String queueName)
        throws framework.integrations.gwServices.maintenanceToolsAPI.ab.com.guidewire.ab.ws.gw.webservice.ab.ab801.maintenancetoolsapi.IllegalArgumentException_Exception, WsiAuthenticationException_Exception
    ;

    /**
     * 
     * @param queueName
     * @throws WsiAuthenticationException_Exception
     * @throws IllegalArgumentException_Exception
     */
    @WebMethod
    @RequestWrapper(localName = "stopWorkQueueWorkers", targetNamespace = "http://guidewire.com/ab/ws/gw/webservice/ab/ab801/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.ab.com.guidewire.ab.ws.gw.webservice.ab.ab801.maintenancetoolsapi.StopWorkQueueWorkers")
    @ResponseWrapper(localName = "stopWorkQueueWorkersResponse", targetNamespace = "http://guidewire.com/ab/ws/gw/webservice/ab/ab801/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.ab.com.guidewire.ab.ws.gw.webservice.ab.ab801.maintenancetoolsapi.StopWorkQueueWorkersResponse")
    public void stopWorkQueueWorkers(
        @WebParam(name = "queueName", targetNamespace = "http://guidewire.com/ab/ws/gw/webservice/ab/ab801/MaintenanceToolsAPI")
        String queueName)
        throws framework.integrations.gwServices.maintenanceToolsAPI.ab.com.guidewire.ab.ws.gw.webservice.ab.ab801.maintenancetoolsapi.IllegalArgumentException_Exception, WsiAuthenticationException_Exception
    ;

    /**
     * 
     * @param queueName
     * @throws WsiAuthenticationException_Exception
     * @throws IllegalArgumentException_Exception
     */
    @WebMethod
    @RequestWrapper(localName = "startWorkQueueWorkers", targetNamespace = "http://guidewire.com/ab/ws/gw/webservice/ab/ab801/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.ab.com.guidewire.ab.ws.gw.webservice.ab.ab801.maintenancetoolsapi.StartWorkQueueWorkers")
    @ResponseWrapper(localName = "startWorkQueueWorkersResponse", targetNamespace = "http://guidewire.com/ab/ws/gw/webservice/ab/ab801/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.ab.com.guidewire.ab.ws.gw.webservice.ab.ab801.maintenancetoolsapi.StartWorkQueueWorkersResponse")
    public void startWorkQueueWorkers(
        @WebParam(name = "queueName", targetNamespace = "http://guidewire.com/ab/ws/gw/webservice/ab/ab801/MaintenanceToolsAPI")
        String queueName)
        throws framework.integrations.gwServices.maintenanceToolsAPI.ab.com.guidewire.ab.ws.gw.webservice.ab.ab801.maintenancetoolsapi.IllegalArgumentException_Exception, WsiAuthenticationException_Exception
    ;

    /**
     * 
     * @param queueName
     * @return
     *     returns framework.integrations.gwServices.maintenanceToolsAPI.ab.com.guidewire.gw.api.webservice.maintenancetools.WQueueStatus
     * @throws WsiAuthenticationException_Exception
     * @throws IllegalArgumentException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "http://guidewire.com/ab/ws/gw/webservice/ab/ab801/MaintenanceToolsAPI")
    @RequestWrapper(localName = "getWQueueStatus", targetNamespace = "http://guidewire.com/ab/ws/gw/webservice/ab/ab801/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.ab.com.guidewire.ab.ws.gw.webservice.ab.ab801.maintenancetoolsapi.GetWQueueStatus")
    @ResponseWrapper(localName = "getWQueueStatusResponse", targetNamespace = "http://guidewire.com/ab/ws/gw/webservice/ab/ab801/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.ab.com.guidewire.ab.ws.gw.webservice.ab.ab801.maintenancetoolsapi.GetWQueueStatusResponse")
    public WQueueStatus getWQueueStatus(
        @WebParam(name = "queueName", targetNamespace = "http://guidewire.com/ab/ws/gw/webservice/ab/ab801/MaintenanceToolsAPI")
        String queueName)
        throws framework.integrations.gwServices.maintenanceToolsAPI.ab.com.guidewire.ab.ws.gw.webservice.ab.ab801.maintenancetoolsapi.IllegalArgumentException_Exception, WsiAuthenticationException_Exception
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
    @WebResult(targetNamespace = "http://guidewire.com/ab/ws/gw/webservice/ab/ab801/MaintenanceToolsAPI")
    @RequestWrapper(localName = "getNumActiveWorkItems", targetNamespace = "http://guidewire.com/ab/ws/gw/webservice/ab/ab801/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.ab.com.guidewire.ab.ws.gw.webservice.ab.ab801.maintenancetoolsapi.GetNumActiveWorkItems")
    @ResponseWrapper(localName = "getNumActiveWorkItemsResponse", targetNamespace = "http://guidewire.com/ab/ws/gw/webservice/ab/ab801/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.ab.com.guidewire.ab.ws.gw.webservice.ab.ab801.maintenancetoolsapi.GetNumActiveWorkItemsResponse")
    public int getNumActiveWorkItems(
        @WebParam(name = "queueName", targetNamespace = "http://guidewire.com/ab/ws/gw/webservice/ab/ab801/MaintenanceToolsAPI")
        String queueName)
        throws framework.integrations.gwServices.maintenanceToolsAPI.ab.com.guidewire.ab.ws.gw.webservice.ab.ab801.maintenancetoolsapi.IllegalArgumentException_Exception, WsiAuthenticationException_Exception
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
    @WebResult(targetNamespace = "http://guidewire.com/ab/ws/gw/webservice/ab/ab801/MaintenanceToolsAPI")
    @RequestWrapper(localName = "waitOnActiveWorkItems", targetNamespace = "http://guidewire.com/ab/ws/gw/webservice/ab/ab801/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.ab.com.guidewire.ab.ws.gw.webservice.ab.ab801.maintenancetoolsapi.WaitOnActiveWorkItems")
    @ResponseWrapper(localName = "waitOnActiveWorkItemsResponse", targetNamespace = "http://guidewire.com/ab/ws/gw/webservice/ab/ab801/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.ab.com.guidewire.ab.ws.gw.webservice.ab.ab801.maintenancetoolsapi.WaitOnActiveWorkItemsResponse")
    public boolean waitOnActiveWorkItems(
        @WebParam(name = "queueName", targetNamespace = "http://guidewire.com/ab/ws/gw/webservice/ab/ab801/MaintenanceToolsAPI")
        String queueName)
        throws framework.integrations.gwServices.maintenanceToolsAPI.ab.com.guidewire.ab.ws.gw.webservice.ab.ab801.maintenancetoolsapi.IllegalArgumentException_Exception, WsiAuthenticationException_Exception
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
    @WebResult(targetNamespace = "http://guidewire.com/ab/ws/gw/webservice/ab/ab801/MaintenanceToolsAPI")
    @RequestWrapper(localName = "isPluginStarted", targetNamespace = "http://guidewire.com/ab/ws/gw/webservice/ab/ab801/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.ab.com.guidewire.ab.ws.gw.webservice.ab.ab801.maintenancetoolsapi.IsPluginStarted")
    @ResponseWrapper(localName = "isPluginStartedResponse", targetNamespace = "http://guidewire.com/ab/ws/gw/webservice/ab/ab801/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.ab.com.guidewire.ab.ws.gw.webservice.ab.ab801.maintenancetoolsapi.IsPluginStartedResponse")
    public boolean isPluginStarted(
        @WebParam(name = "pluginName", targetNamespace = "http://guidewire.com/ab/ws/gw/webservice/ab/ab801/MaintenanceToolsAPI")
        String pluginName)
        throws framework.integrations.gwServices.maintenanceToolsAPI.ab.com.guidewire.ab.ws.gw.webservice.ab.ab801.maintenancetoolsapi.IllegalArgumentException_Exception, WsiAuthenticationException_Exception
    ;

    /**
     * 
     * @param pluginName
     * @throws WsiAuthenticationException_Exception
     * @throws IllegalArgumentException_Exception
     */
    @WebMethod
    @RequestWrapper(localName = "startPlugin", targetNamespace = "http://guidewire.com/ab/ws/gw/webservice/ab/ab801/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.ab.com.guidewire.ab.ws.gw.webservice.ab.ab801.maintenancetoolsapi.StartPlugin")
    @ResponseWrapper(localName = "startPluginResponse", targetNamespace = "http://guidewire.com/ab/ws/gw/webservice/ab/ab801/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.ab.com.guidewire.ab.ws.gw.webservice.ab.ab801.maintenancetoolsapi.StartPluginResponse")
    public void startPlugin(
        @WebParam(name = "pluginName", targetNamespace = "http://guidewire.com/ab/ws/gw/webservice/ab/ab801/MaintenanceToolsAPI")
        String pluginName)
        throws framework.integrations.gwServices.maintenanceToolsAPI.ab.com.guidewire.ab.ws.gw.webservice.ab.ab801.maintenancetoolsapi.IllegalArgumentException_Exception, WsiAuthenticationException_Exception
    ;

    /**
     * 
     * @param pluginName
     * @throws WsiAuthenticationException_Exception
     * @throws IllegalArgumentException_Exception
     */
    @WebMethod
    @RequestWrapper(localName = "stopPlugin", targetNamespace = "http://guidewire.com/ab/ws/gw/webservice/ab/ab801/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.ab.com.guidewire.ab.ws.gw.webservice.ab.ab801.maintenancetoolsapi.StopPlugin")
    @ResponseWrapper(localName = "stopPluginResponse", targetNamespace = "http://guidewire.com/ab/ws/gw/webservice/ab/ab801/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.ab.com.guidewire.ab.ws.gw.webservice.ab.ab801.maintenancetoolsapi.StopPluginResponse")
    public void stopPlugin(
        @WebParam(name = "pluginName", targetNamespace = "http://guidewire.com/ab/ws/gw/webservice/ab/ab801/MaintenanceToolsAPI")
        String pluginName)
        throws framework.integrations.gwServices.maintenanceToolsAPI.ab.com.guidewire.ab.ws.gw.webservice.ab.ab801.maintenancetoolsapi.IllegalArgumentException_Exception, WsiAuthenticationException_Exception
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
    @RequestWrapper(localName = "changeSubtype", targetNamespace = "http://guidewire.com/ab/ws/gw/webservice/ab/ab801/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.ab.com.guidewire.ab.ws.gw.webservice.ab.ab801.maintenancetoolsapi.ChangeSubtype")
    @ResponseWrapper(localName = "changeSubtypeResponse", targetNamespace = "http://guidewire.com/ab/ws/gw/webservice/ab/ab801/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.ab.com.guidewire.ab.ws.gw.webservice.ab.ab801.maintenancetoolsapi.ChangeSubtypeResponse")
    public void changeSubtype(
        @WebParam(name = "publicID", targetNamespace = "http://guidewire.com/ab/ws/gw/webservice/ab/ab801/MaintenanceToolsAPI")
        String publicID,
        @WebParam(name = "targetType", targetNamespace = "http://guidewire.com/ab/ws/gw/webservice/ab/ab801/MaintenanceToolsAPI")
        String targetType)
        throws framework.integrations.gwServices.maintenanceToolsAPI.ab.com.guidewire.ab.ws.gw.webservice.ab.ab801.maintenancetoolsapi.IllegalArgumentException_Exception, framework.integrations.gwServices.maintenanceToolsAPI.ab.com.guidewire.ab.ws.gw.webservice.ab.ab801.maintenancetoolsapi.WebServiceException_Exception, WsiAuthenticationException_Exception
    ;

}