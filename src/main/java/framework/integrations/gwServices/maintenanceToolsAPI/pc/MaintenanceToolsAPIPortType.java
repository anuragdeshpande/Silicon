package framework.integrations.gwServices.maintenanceToolsAPI.pc;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.3.4
 * 2021-05-03T11:46:38.317-06:00
 * Generated source version: 3.3.4
 *
 */
@WebService(targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI", name = "MaintenanceToolsAPIPortType")
@XmlSeeAlso({ObjectFactory.class})
public interface MaintenanceToolsAPIPortType {

    @WebMethod
    @RequestWrapper(localName = "stopPluginWithTimeout", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.pc.StopPluginWithTimeout")
    @ResponseWrapper(localName = "stopPluginWithTimeoutResponse", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.pc.StopPluginWithTimeoutResponse")
    public void stopPluginWithTimeout(

        @WebParam(name = "pluginName", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI")
        java.lang.String pluginName,
        @WebParam(name = "timeout", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI")
        long timeout
    ) throws WsiAuthenticationException_Exception, IllegalArgumentException_Exception, RequiredFieldException_Exception;

    @WebMethod
    @RequestWrapper(localName = "batchProcessStatusByID", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.pc.BatchProcessStatusByID")
    @ResponseWrapper(localName = "batchProcessStatusByIDResponse", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.pc.BatchProcessStatusByIDResponse")
    @WebResult(name = "return", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI")
    public framework.integrations.gwServices.maintenanceToolsAPI.pc.ProcessStatus batchProcessStatusByID(

        @WebParam(name = "pid", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI")
        framework.integrations.gwServices.maintenanceToolsAPI.pc.ProcessID pid
    ) throws WsiAuthenticationException_Exception, IllegalArgumentException_Exception, RequiredFieldException_Exception;

    @WebMethod
    @RequestWrapper(localName = "requestTerminationOfBatchProcessByID", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.pc.RequestTerminationOfBatchProcessByID")
    @ResponseWrapper(localName = "requestTerminationOfBatchProcessByIDResponse", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.pc.RequestTerminationOfBatchProcessByIDResponse")
    @WebResult(name = "return", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI")
    public boolean requestTerminationOfBatchProcessByID(

        @WebParam(name = "pid", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI")
        framework.integrations.gwServices.maintenanceToolsAPI.pc.ProcessID pid
    ) throws WsiAuthenticationException_Exception, IllegalArgumentException_Exception, RequiredFieldException_Exception;

    @WebMethod
    @RequestWrapper(localName = "getWorkQueueConfig", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.pc.GetWorkQueueConfig")
    @ResponseWrapper(localName = "getWorkQueueConfigResponse", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.pc.GetWorkQueueConfigResponse")
    @WebResult(name = "return", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI")
    public framework.integrations.gwServices.maintenanceToolsAPI.pc.WorkQueueConfig getWorkQueueConfig(

        @WebParam(name = "queueName", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI")
        java.lang.String queueName
    ) throws WsiAuthenticationException_Exception, IllegalArgumentException_Exception, RequiredFieldException_Exception;

    @WebMethod
    @RequestWrapper(localName = "startBatchProcessWithArguments", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.pc.StartBatchProcessWithArguments")
    @ResponseWrapper(localName = "startBatchProcessWithArgumentsResponse", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.pc.StartBatchProcessWithArgumentsResponse")
    @WebResult(name = "return", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI")
    public framework.integrations.gwServices.maintenanceToolsAPI.pc.ProcessID startBatchProcessWithArguments(

        @WebParam(name = "processName", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI")
        java.lang.String processName,
        @WebParam(name = "arguments", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI")
        framework.integrations.gwServices.maintenanceToolsAPI.pc.StartBatchProcessWithArguments.Arguments arguments
    ) throws WsiAuthenticationException_Exception, IllegalArgumentException_Exception;

    @WebMethod
    @RequestWrapper(localName = "stopWorkQueueWorkers", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.pc.StopWorkQueueWorkers")
    @ResponseWrapper(localName = "stopWorkQueueWorkersResponse", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.pc.StopWorkQueueWorkersResponse")
    public void stopWorkQueueWorkers(

        @WebParam(name = "queueName", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI")
        java.lang.String queueName
    ) throws WsiAuthenticationException_Exception, IllegalArgumentException_Exception, RequiredFieldException_Exception;

    @WebMethod
    @RequestWrapper(localName = "stopPlugin", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.pc.StopPlugin")
    @ResponseWrapper(localName = "stopPluginResponse", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.pc.StopPluginResponse")
    public void stopPlugin(

        @WebParam(name = "pluginName", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI")
        java.lang.String pluginName
    ) throws WsiAuthenticationException_Exception, IllegalArgumentException_Exception, RequiredFieldException_Exception;

    @WebMethod
    @RequestWrapper(localName = "startPlugin", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.pc.StartPlugin")
    @ResponseWrapper(localName = "startPluginResponse", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.pc.StartPluginResponse")
    public void startPlugin(

        @WebParam(name = "pluginName", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI")
        java.lang.String pluginName
    ) throws WsiAuthenticationException_Exception, IllegalArgumentException_Exception, RequiredFieldException_Exception;

    @WebMethod
    @RequestWrapper(localName = "whenStatsCalculated", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.pc.WhenStatsCalculated")
    @ResponseWrapper(localName = "whenStatsCalculatedResponse", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.pc.WhenStatsCalculatedResponse")
    @WebResult(name = "return", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI")
    public javax.xml.datatype.XMLGregorianCalendar whenStatsCalculated()
 throws WsiAuthenticationException_Exception;

    @WebMethod
    @RequestWrapper(localName = "startValidateArchiveLinksBatchProcessByDateRange", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.pc.StartValidateArchiveLinksBatchProcessByDateRange")
    @ResponseWrapper(localName = "startValidateArchiveLinksBatchProcessByDateRangeResponse", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.pc.StartValidateArchiveLinksBatchProcessByDateRangeResponse")
    @WebResult(name = "return", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI")
    public framework.integrations.gwServices.maintenanceToolsAPI.pc.ProcessID startValidateArchiveLinksBatchProcessByDateRange(

        @WebParam(name = "startTime", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI")
        javax.xml.datatype.XMLGregorianCalendar startTime,
        @WebParam(name = "endTime", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI")
        javax.xml.datatype.XMLGregorianCalendar endTime
    ) throws WsiAuthenticationException_Exception, RequiredFieldException_Exception;

    @WebMethod
    @RequestWrapper(localName = "batchProcessStatusByName", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.pc.BatchProcessStatusByName")
    @ResponseWrapper(localName = "batchProcessStatusByNameResponse", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.pc.BatchProcessStatusByNameResponse")
    @WebResult(name = "return", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI")
    public framework.integrations.gwServices.maintenanceToolsAPI.pc.ProcessStatus batchProcessStatusByName(

        @WebParam(name = "processName", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI")
        java.lang.String processName
    ) throws WsiAuthenticationException_Exception, IllegalArgumentException_Exception, RequiredFieldException_Exception;

    @WebMethod
    @RequestWrapper(localName = "getWQueueStatus", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.pc.GetWQueueStatus")
    @ResponseWrapper(localName = "getWQueueStatusResponse", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.pc.GetWQueueStatusResponse")
    @WebResult(name = "return", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI")
    public framework.integrations.gwServices.maintenanceToolsAPI.pc.WQueueStatus getWQueueStatus(

        @WebParam(name = "queueName", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI")
        java.lang.String queueName
    ) throws WsiAuthenticationException_Exception, IllegalArgumentException_Exception, RequiredFieldException_Exception;

    @WebMethod
    @RequestWrapper(localName = "getWorkQueueNames", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.pc.GetWorkQueueNames")
    @ResponseWrapper(localName = "getWorkQueueNamesResponse", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.pc.GetWorkQueueNamesResponse")
    @WebResult(name = "return", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI")
    public framework.integrations.gwServices.maintenanceToolsAPI.pc.GetWorkQueueNamesResponse.Return getWorkQueueNames()
 throws WsiAuthenticationException_Exception;

    @WebMethod
    @RequestWrapper(localName = "requestTerminationOfBatchProcessByName", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.pc.RequestTerminationOfBatchProcessByName")
    @ResponseWrapper(localName = "requestTerminationOfBatchProcessByNameResponse", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.pc.RequestTerminationOfBatchProcessByNameResponse")
    @WebResult(name = "return", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI")
    public boolean requestTerminationOfBatchProcessByName(

        @WebParam(name = "processName", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI")
        java.lang.String processName
    ) throws WsiAuthenticationException_Exception, RequiredFieldException_Exception;

    @WebMethod
    @RequestWrapper(localName = "startValidateArchiveLinksBatchProcessAll", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.pc.StartValidateArchiveLinksBatchProcessAll")
    @ResponseWrapper(localName = "startValidateArchiveLinksBatchProcessAllResponse", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.pc.StartValidateArchiveLinksBatchProcessAllResponse")
    @WebResult(name = "return", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI")
    public framework.integrations.gwServices.maintenanceToolsAPI.pc.ProcessID startValidateArchiveLinksBatchProcessAll()
 throws WsiAuthenticationException_Exception;

    @WebMethod
    @RequestWrapper(localName = "startBatchProcess", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.pc.StartBatchProcess")
    @ResponseWrapper(localName = "startBatchProcessResponse", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.pc.StartBatchProcessResponse")
    @WebResult(name = "return", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI")
    public framework.integrations.gwServices.maintenanceToolsAPI.pc.ProcessID startBatchProcess(

        @WebParam(name = "processName", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI")
        java.lang.String processName
    ) throws WsiAuthenticationException_Exception, IllegalArgumentException_Exception, RequiredFieldException_Exception;

    @WebMethod
    @RequestWrapper(localName = "isPluginStarted", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.pc.IsPluginStarted")
    @ResponseWrapper(localName = "isPluginStartedResponse", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.pc.IsPluginStartedResponse")
    @WebResult(name = "return", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI")
    public boolean isPluginStarted(

        @WebParam(name = "pluginName", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI")
        java.lang.String pluginName
    ) throws WsiAuthenticationException_Exception, IllegalArgumentException_Exception, RequiredFieldException_Exception;

    @WebMethod
    @RequestWrapper(localName = "startValidateArchiveLinksBatchProcessByIds", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.pc.StartValidateArchiveLinksBatchProcessByIds")
    @ResponseWrapper(localName = "startValidateArchiveLinksBatchProcessByIdsResponse", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.pc.StartValidateArchiveLinksBatchProcessByIdsResponse")
    @WebResult(name = "return", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI")
    public framework.integrations.gwServices.maintenanceToolsAPI.pc.ProcessID startValidateArchiveLinksBatchProcessByIds(

        @WebParam(name = "ids", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI")
        framework.integrations.gwServices.maintenanceToolsAPI.pc.StartValidateArchiveLinksBatchProcessByIds.Ids ids
    ) throws WsiAuthenticationException_Exception, RequiredFieldException_Exception;

    @WebMethod
    @RequestWrapper(localName = "getNumActiveWorkItems", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.pc.GetNumActiveWorkItems")
    @ResponseWrapper(localName = "getNumActiveWorkItemsResponse", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.pc.GetNumActiveWorkItemsResponse")
    @WebResult(name = "return", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI")
    public int getNumActiveWorkItems(

        @WebParam(name = "queueName", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI")
        java.lang.String queueName
    ) throws WsiAuthenticationException_Exception, IllegalArgumentException_Exception, RequiredFieldException_Exception;

    @WebMethod
    @RequestWrapper(localName = "getValidBatchProcessNames", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.pc.GetValidBatchProcessNames")
    @ResponseWrapper(localName = "getValidBatchProcessNamesResponse", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.pc.GetValidBatchProcessNamesResponse")
    @WebResult(name = "return", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI")
    public framework.integrations.gwServices.maintenanceToolsAPI.pc.GetValidBatchProcessNamesResponse.Return getValidBatchProcessNames()
 throws WsiAuthenticationException_Exception;

    @WebMethod
    @RequestWrapper(localName = "notifyQueueWorkers", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.pc.NotifyQueueWorkers")
    @ResponseWrapper(localName = "notifyQueueWorkersResponse", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.pc.NotifyQueueWorkersResponse")
    public void notifyQueueWorkers(

        @WebParam(name = "queueName", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI")
        java.lang.String queueName
    ) throws WsiAuthenticationException_Exception, IllegalArgumentException_Exception, RequiredFieldException_Exception;

    @WebMethod
    @RequestWrapper(localName = "isBatchProcessNameValid", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.pc.IsBatchProcessNameValid")
    @ResponseWrapper(localName = "isBatchProcessNameValidResponse", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.pc.IsBatchProcessNameValidResponse")
    @WebResult(name = "return", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI")
    public boolean isBatchProcessNameValid(

        @WebParam(name = "processName", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI")
        java.lang.String processName
    ) throws WsiAuthenticationException_Exception, RequiredFieldException_Exception;

    @WebMethod
    @RequestWrapper(localName = "startPluginWithTimeout", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.pc.StartPluginWithTimeout")
    @ResponseWrapper(localName = "startPluginWithTimeoutResponse", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.pc.StartPluginWithTimeoutResponse")
    public void startPluginWithTimeout(

        @WebParam(name = "pluginName", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI")
        java.lang.String pluginName,
        @WebParam(name = "timeout", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI")
        long timeout
    ) throws WsiAuthenticationException_Exception, IllegalArgumentException_Exception, RequiredFieldException_Exception;

    @WebMethod
    @RequestWrapper(localName = "setWorkQueueConfig", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.pc.SetWorkQueueConfig")
    @ResponseWrapper(localName = "setWorkQueueConfigResponse", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.pc.SetWorkQueueConfigResponse")
    public void setWorkQueueConfig(

        @WebParam(name = "queueName", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI")
        java.lang.String queueName,
        @WebParam(name = "config", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI")
        framework.integrations.gwServices.maintenanceToolsAPI.pc.WorkQueueConfig config
    ) throws WsiAuthenticationException_Exception, IllegalArgumentException_Exception, RequiredFieldException_Exception;

    @WebMethod
    @RequestWrapper(localName = "startWorkQueueWorkers", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.pc.StartWorkQueueWorkers")
    @ResponseWrapper(localName = "startWorkQueueWorkersResponse", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.pc.StartWorkQueueWorkersResponse")
    public void startWorkQueueWorkers(

        @WebParam(name = "queueName", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI")
        java.lang.String queueName
    ) throws WsiAuthenticationException_Exception, IllegalArgumentException_Exception, RequiredFieldException_Exception;

    @WebMethod
    @RequestWrapper(localName = "waitOnActiveWorkItems", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.pc.WaitOnActiveWorkItems")
    @ResponseWrapper(localName = "waitOnActiveWorkItemsResponse", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI", className = "framework.integrations.gwServices.maintenanceToolsAPI.pc.WaitOnActiveWorkItemsResponse")
    @WebResult(name = "return", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI")
    public boolean waitOnActiveWorkItems(

        @WebParam(name = "queueName", targetNamespace = "http://guidewire.com/pc/ws/gw/webservice/pc/pc1000/MaintenanceToolsAPI")
        java.lang.String queueName
    ) throws WsiAuthenticationException_Exception, IllegalArgumentException_Exception, RequiredFieldException_Exception;
}
