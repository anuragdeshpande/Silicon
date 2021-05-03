
package framework.integrations.gwServices.maintenanceToolsAPI.bc;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import javax.xml.namespace.QName;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * This class was generated by Apache CXF 3.3.4
 * 2021-05-03T11:44:55.532-06:00
 * Generated source version: 3.3.4
 *
 */
public final class MaintenanceToolsAPIPortType_MaintenanceToolsAPISoap11Port_Client {

    private static final QName SERVICE_NAME = new QName("http://guidewire.com/bc/ws/gw/webservice/bc/MaintenanceToolsAPI", "MaintenanceToolsAPI");

    private MaintenanceToolsAPIPortType_MaintenanceToolsAPISoap11Port_Client() {
    }

    public static void main(String args[]) throws java.lang.Exception {
        URL wsdlURL = MaintenanceToolsAPI.WSDL_LOCATION;
        if (args.length > 0 && args[0] != null && !"".equals(args[0])) {
            File wsdlFile = new File(args[0]);
            try {
                if (wsdlFile.exists()) {
                    wsdlURL = wsdlFile.toURI().toURL();
                } else {
                    wsdlURL = new URL(args[0]);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        MaintenanceToolsAPI ss = new MaintenanceToolsAPI(wsdlURL, SERVICE_NAME);
        MaintenanceToolsAPIPortType port = ss.getMaintenanceToolsAPISoap11Port();

        {
        System.out.println("Invoking startBatchProcess...");
        java.lang.String _startBatchProcess_processName = "";
        try {
            framework.integrations.gwServices.maintenanceToolsAPI.bc.ProcessID _startBatchProcess__return = port.startBatchProcess(_startBatchProcess_processName);
            System.out.println("startBatchProcess.result=" + _startBatchProcess__return);

        } catch (IllegalArgumentException_Exception e) {
            System.out.println("Expected exception: IllegalArgumentException has occurred.");
            System.out.println(e.toString());
        } catch (WsiAuthenticationException_Exception e) {
            System.out.println("Expected exception: WsiAuthenticationException has occurred.");
            System.out.println(e.toString());
        }

            }
        {
        System.out.println("Invoking isPluginStarted...");
        java.lang.String _isPluginStarted_pluginName = "";
        try {
            boolean _isPluginStarted__return = port.isPluginStarted(_isPluginStarted_pluginName);
            System.out.println("isPluginStarted.result=" + _isPluginStarted__return);

        } catch (IllegalArgumentException_Exception e) {
            System.out.println("Expected exception: IllegalArgumentException has occurred.");
            System.out.println(e.toString());
        } catch (WsiAuthenticationException_Exception e) {
            System.out.println("Expected exception: WsiAuthenticationException has occurred.");
            System.out.println(e.toString());
        }

            }
        {
        System.out.println("Invoking requestTerminationOfBatchProcessByName...");
        java.lang.String _requestTerminationOfBatchProcessByName_processName = "";
        try {
            boolean _requestTerminationOfBatchProcessByName__return = port.requestTerminationOfBatchProcessByName(_requestTerminationOfBatchProcessByName_processName);
            System.out.println("requestTerminationOfBatchProcessByName.result=" + _requestTerminationOfBatchProcessByName__return);

        } catch (WsiAuthenticationException_Exception e) {
            System.out.println("Expected exception: WsiAuthenticationException has occurred.");
            System.out.println(e.toString());
        }

            }
        {
        System.out.println("Invoking getWQueueStatus...");
        java.lang.String _getWQueueStatus_queueName = "";
        try {
            framework.integrations.gwServices.maintenanceToolsAPI.bc.WQueueStatus _getWQueueStatus__return = port.getWQueueStatus(_getWQueueStatus_queueName);
            System.out.println("getWQueueStatus.result=" + _getWQueueStatus__return);

        } catch (IllegalArgumentException_Exception e) {
            System.out.println("Expected exception: IllegalArgumentException has occurred.");
            System.out.println(e.toString());
        } catch (WsiAuthenticationException_Exception e) {
            System.out.println("Expected exception: WsiAuthenticationException has occurred.");
            System.out.println(e.toString());
        }

            }
        {
        System.out.println("Invoking getWorkQueueNames...");
        try {
            framework.integrations.gwServices.maintenanceToolsAPI.bc.GetWorkQueueNamesResponse.Return _getWorkQueueNames__return = port.getWorkQueueNames();
            System.out.println("getWorkQueueNames.result=" + _getWorkQueueNames__return);

        } catch (WsiAuthenticationException_Exception e) {
            System.out.println("Expected exception: WsiAuthenticationException has occurred.");
            System.out.println(e.toString());
        }

            }
        {
        System.out.println("Invoking batchProcessStatusByName...");
        java.lang.String _batchProcessStatusByName_processName = "";
        try {
            framework.integrations.gwServices.maintenanceToolsAPI.bc.ProcessStatus _batchProcessStatusByName__return = port.batchProcessStatusByName(_batchProcessStatusByName_processName);
            System.out.println("batchProcessStatusByName.result=" + _batchProcessStatusByName__return);

        } catch (IllegalArgumentException_Exception e) {
            System.out.println("Expected exception: IllegalArgumentException has occurred.");
            System.out.println(e.toString());
        } catch (WsiAuthenticationException_Exception e) {
            System.out.println("Expected exception: WsiAuthenticationException has occurred.");
            System.out.println(e.toString());
        }

            }
        {
        System.out.println("Invoking startPlugin...");
        java.lang.String _startPlugin_pluginName = "";
        try {
            port.startPlugin(_startPlugin_pluginName);

        } catch (IllegalArgumentException_Exception e) {
            System.out.println("Expected exception: IllegalArgumentException has occurred.");
            System.out.println(e.toString());
        } catch (WsiAuthenticationException_Exception e) {
            System.out.println("Expected exception: WsiAuthenticationException has occurred.");
            System.out.println(e.toString());
        }

            }
        {
        System.out.println("Invoking stopPlugin...");
        java.lang.String _stopPlugin_pluginName = "";
        try {
            port.stopPlugin(_stopPlugin_pluginName);

        } catch (IllegalArgumentException_Exception e) {
            System.out.println("Expected exception: IllegalArgumentException has occurred.");
            System.out.println(e.toString());
        } catch (WsiAuthenticationException_Exception e) {
            System.out.println("Expected exception: WsiAuthenticationException has occurred.");
            System.out.println(e.toString());
        }

            }
        {
        System.out.println("Invoking startBatchProcessWithArguments...");
        java.lang.String _startBatchProcessWithArguments_processName = "";
        framework.integrations.gwServices.maintenanceToolsAPI.bc.StartBatchProcessWithArguments.Arguments _startBatchProcessWithArguments_arguments = null;
        try {
            framework.integrations.gwServices.maintenanceToolsAPI.bc.ProcessID _startBatchProcessWithArguments__return = port.startBatchProcessWithArguments(_startBatchProcessWithArguments_processName, _startBatchProcessWithArguments_arguments);
            System.out.println("startBatchProcessWithArguments.result=" + _startBatchProcessWithArguments__return);

        } catch (IllegalArgumentException_Exception e) {
            System.out.println("Expected exception: IllegalArgumentException has occurred.");
            System.out.println(e.toString());
        } catch (WsiAuthenticationException_Exception e) {
            System.out.println("Expected exception: WsiAuthenticationException has occurred.");
            System.out.println(e.toString());
        }

            }
        {
        System.out.println("Invoking importSampleData...");
        java.lang.String _importSampleData_dataSet = "";
        try {
            port.importSampleData(_importSampleData_dataSet);

        } catch (RequiredFieldException_Exception e) {
            System.out.println("Expected exception: RequiredFieldException has occurred.");
            System.out.println(e.toString());
        } catch (DataConversionException_Exception e) {
            System.out.println("Expected exception: DataConversionException has occurred.");
            System.out.println(e.toString());
        } catch (WsiAuthenticationException_Exception e) {
            System.out.println("Expected exception: WsiAuthenticationException has occurred.");
            System.out.println(e.toString());
        } catch (PermissionException_Exception e) {
            System.out.println("Expected exception: PermissionException has occurred.");
            System.out.println(e.toString());
        }

            }
        {
        System.out.println("Invoking stopWorkQueueWorkers...");
        java.lang.String _stopWorkQueueWorkers_queueName = "";
        try {
            port.stopWorkQueueWorkers(_stopWorkQueueWorkers_queueName);

        } catch (IllegalArgumentException_Exception e) {
            System.out.println("Expected exception: IllegalArgumentException has occurred.");
            System.out.println(e.toString());
        } catch (WsiAuthenticationException_Exception e) {
            System.out.println("Expected exception: WsiAuthenticationException has occurred.");
            System.out.println(e.toString());
        }

            }
        {
        System.out.println("Invoking requestTerminationOfBatchProcessByID...");
        framework.integrations.gwServices.maintenanceToolsAPI.bc.ProcessID _requestTerminationOfBatchProcessByID_pid = new framework.integrations.gwServices.maintenanceToolsAPI.bc.ProcessID();
        try {
            boolean _requestTerminationOfBatchProcessByID__return = port.requestTerminationOfBatchProcessByID(_requestTerminationOfBatchProcessByID_pid);
            System.out.println("requestTerminationOfBatchProcessByID.result=" + _requestTerminationOfBatchProcessByID__return);

        } catch (IllegalArgumentException_Exception e) {
            System.out.println("Expected exception: IllegalArgumentException has occurred.");
            System.out.println(e.toString());
        } catch (WsiAuthenticationException_Exception e) {
            System.out.println("Expected exception: WsiAuthenticationException has occurred.");
            System.out.println(e.toString());
        }

            }
        {
        System.out.println("Invoking getWorkQueueConfig...");
        java.lang.String _getWorkQueueConfig_queueName = "";
        try {
            framework.integrations.gwServices.maintenanceToolsAPI.bc.WorkQueueConfig _getWorkQueueConfig__return = port.getWorkQueueConfig(_getWorkQueueConfig_queueName);
            System.out.println("getWorkQueueConfig.result=" + _getWorkQueueConfig__return);

        } catch (IllegalArgumentException_Exception e) {
            System.out.println("Expected exception: IllegalArgumentException has occurred.");
            System.out.println(e.toString());
        } catch (WsiAuthenticationException_Exception e) {
            System.out.println("Expected exception: WsiAuthenticationException has occurred.");
            System.out.println(e.toString());
        }

            }
        {
        System.out.println("Invoking batchProcessStatusByID...");
        framework.integrations.gwServices.maintenanceToolsAPI.bc.ProcessID _batchProcessStatusByID_pid = new framework.integrations.gwServices.maintenanceToolsAPI.bc.ProcessID();
        try {
            framework.integrations.gwServices.maintenanceToolsAPI.bc.ProcessStatus _batchProcessStatusByID__return = port.batchProcessStatusByID(_batchProcessStatusByID_pid);
            System.out.println("batchProcessStatusByID.result=" + _batchProcessStatusByID__return);

        } catch (IllegalArgumentException_Exception e) {
            System.out.println("Expected exception: IllegalArgumentException has occurred.");
            System.out.println(e.toString());
        } catch (WsiAuthenticationException_Exception e) {
            System.out.println("Expected exception: WsiAuthenticationException has occurred.");
            System.out.println(e.toString());
        }

            }
        {
        System.out.println("Invoking stopPluginWithTimeout...");
        java.lang.String _stopPluginWithTimeout_pluginName = "";
        long _stopPluginWithTimeout_timeout = 0;
        try {
            port.stopPluginWithTimeout(_stopPluginWithTimeout_pluginName, _stopPluginWithTimeout_timeout);

        } catch (IllegalArgumentException_Exception e) {
            System.out.println("Expected exception: IllegalArgumentException has occurred.");
            System.out.println(e.toString());
        } catch (WsiAuthenticationException_Exception e) {
            System.out.println("Expected exception: WsiAuthenticationException has occurred.");
            System.out.println(e.toString());
        }

            }
        {
        System.out.println("Invoking waitOnActiveWorkItems...");
        java.lang.String _waitOnActiveWorkItems_queueName = "";
        try {
            boolean _waitOnActiveWorkItems__return = port.waitOnActiveWorkItems(_waitOnActiveWorkItems_queueName);
            System.out.println("waitOnActiveWorkItems.result=" + _waitOnActiveWorkItems__return);

        } catch (IllegalArgumentException_Exception e) {
            System.out.println("Expected exception: IllegalArgumentException has occurred.");
            System.out.println(e.toString());
        } catch (WsiAuthenticationException_Exception e) {
            System.out.println("Expected exception: WsiAuthenticationException has occurred.");
            System.out.println(e.toString());
        }

            }
        {
        System.out.println("Invoking startWorkQueueWorkers...");
        java.lang.String _startWorkQueueWorkers_queueName = "";
        try {
            port.startWorkQueueWorkers(_startWorkQueueWorkers_queueName);

        } catch (IllegalArgumentException_Exception e) {
            System.out.println("Expected exception: IllegalArgumentException has occurred.");
            System.out.println(e.toString());
        } catch (WsiAuthenticationException_Exception e) {
            System.out.println("Expected exception: WsiAuthenticationException has occurred.");
            System.out.println(e.toString());
        }

            }
        {
        System.out.println("Invoking setWorkQueueConfig...");
        java.lang.String _setWorkQueueConfig_queueName = "";
        framework.integrations.gwServices.maintenanceToolsAPI.bc.WorkQueueConfig _setWorkQueueConfig_config = new framework.integrations.gwServices.maintenanceToolsAPI.bc.WorkQueueConfig();
        try {
            port.setWorkQueueConfig(_setWorkQueueConfig_queueName, _setWorkQueueConfig_config);

        } catch (IllegalArgumentException_Exception e) {
            System.out.println("Expected exception: IllegalArgumentException has occurred.");
            System.out.println(e.toString());
        } catch (WsiAuthenticationException_Exception e) {
            System.out.println("Expected exception: WsiAuthenticationException has occurred.");
            System.out.println(e.toString());
        }

            }
        {
        System.out.println("Invoking isBatchProcessNameValid...");
        java.lang.String _isBatchProcessNameValid_processName = "";
        try {
            boolean _isBatchProcessNameValid__return = port.isBatchProcessNameValid(_isBatchProcessNameValid_processName);
            System.out.println("isBatchProcessNameValid.result=" + _isBatchProcessNameValid__return);

        } catch (WsiAuthenticationException_Exception e) {
            System.out.println("Expected exception: WsiAuthenticationException has occurred.");
            System.out.println(e.toString());
        }

            }
        {
        System.out.println("Invoking startPluginWithTimeout...");
        java.lang.String _startPluginWithTimeout_pluginName = "";
        long _startPluginWithTimeout_timeout = 0;
        try {
            port.startPluginWithTimeout(_startPluginWithTimeout_pluginName, _startPluginWithTimeout_timeout);

        } catch (IllegalArgumentException_Exception e) {
            System.out.println("Expected exception: IllegalArgumentException has occurred.");
            System.out.println(e.toString());
        } catch (WsiAuthenticationException_Exception e) {
            System.out.println("Expected exception: WsiAuthenticationException has occurred.");
            System.out.println(e.toString());
        }

            }
        {
        System.out.println("Invoking getValidBatchProcessNames...");
        try {
            framework.integrations.gwServices.maintenanceToolsAPI.bc.GetValidBatchProcessNamesResponse.Return _getValidBatchProcessNames__return = port.getValidBatchProcessNames();
            System.out.println("getValidBatchProcessNames.result=" + _getValidBatchProcessNames__return);

        } catch (WsiAuthenticationException_Exception e) {
            System.out.println("Expected exception: WsiAuthenticationException has occurred.");
            System.out.println(e.toString());
        }

            }
        {
        System.out.println("Invoking notifyQueueWorkers...");
        java.lang.String _notifyQueueWorkers_queueName = "";
        try {
            port.notifyQueueWorkers(_notifyQueueWorkers_queueName);

        } catch (IllegalArgumentException_Exception e) {
            System.out.println("Expected exception: IllegalArgumentException has occurred.");
            System.out.println(e.toString());
        } catch (WsiAuthenticationException_Exception e) {
            System.out.println("Expected exception: WsiAuthenticationException has occurred.");
            System.out.println(e.toString());
        }

            }
        {
        System.out.println("Invoking getNumActiveWorkItems...");
        java.lang.String _getNumActiveWorkItems_queueName = "";
        try {
            int _getNumActiveWorkItems__return = port.getNumActiveWorkItems(_getNumActiveWorkItems_queueName);
            System.out.println("getNumActiveWorkItems.result=" + _getNumActiveWorkItems__return);

        } catch (IllegalArgumentException_Exception e) {
            System.out.println("Expected exception: IllegalArgumentException has occurred.");
            System.out.println(e.toString());
        } catch (WsiAuthenticationException_Exception e) {
            System.out.println("Expected exception: WsiAuthenticationException has occurred.");
            System.out.println(e.toString());
        }

            }
        {
        System.out.println("Invoking importDefaultSampleData...");
        try {
            port.importDefaultSampleData();

        } catch (DataConversionException_Exception e) {
            System.out.println("Expected exception: DataConversionException has occurred.");
            System.out.println(e.toString());
        } catch (WsiAuthenticationException_Exception e) {
            System.out.println("Expected exception: WsiAuthenticationException has occurred.");
            System.out.println(e.toString());
        } catch (PermissionException_Exception e) {
            System.out.println("Expected exception: PermissionException has occurred.");
            System.out.println(e.toString());
        }

            }

        System.exit(0);
    }

}
