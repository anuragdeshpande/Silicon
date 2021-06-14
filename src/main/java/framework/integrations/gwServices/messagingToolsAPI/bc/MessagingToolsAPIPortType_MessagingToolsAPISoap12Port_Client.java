
package framework.integrations.gwServices.messagingToolsAPI.bc;

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
 * 2021-06-14T14:02:48.424-06:00
 * Generated source version: 3.3.4
 *
 */
public final class MessagingToolsAPIPortType_MessagingToolsAPISoap12Port_Client {

    private static final QName SERVICE_NAME = new QName("http://guidewire.com/bc/ws/gw/webservice/bc/MessagingToolsAPI", "MessagingToolsAPI");

    private MessagingToolsAPIPortType_MessagingToolsAPISoap12Port_Client() {
    }

    public static void main(String args[]) throws java.lang.Exception {
        URL wsdlURL = MessagingToolsAPI.WSDL_LOCATION;
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

        MessagingToolsAPI ss = new MessagingToolsAPI(wsdlURL, SERVICE_NAME);
        MessagingToolsAPIPortType port = ss.getMessagingToolsAPISoap12Port();

        {
        System.out.println("Invoking resyncProducer...");
        int _resyncProducer_destID = 0;
        java.lang.String _resyncProducer_producerPublicID = "";
        try {
            port.resyncProducer(_resyncProducer_destID, _resyncProducer_producerPublicID);

        } catch (RequiredFieldException_Exception e) {
            System.out.println("Expected exception: RequiredFieldException has occurred.");
            System.out.println(e.toString());
        } catch (BadIdentifierException_Exception e) {
            System.out.println("Expected exception: BadIdentifierException has occurred.");
            System.out.println(e.toString());
        } catch (WsiAuthenticationException_Exception e) {
            System.out.println("Expected exception: WsiAuthenticationException has occurred.");
            System.out.println(e.toString());
        }

            }
        {
        System.out.println("Invoking retryRetryableErrorMessages...");
        int _retryRetryableErrorMessages_destID = 0;
        try {
            boolean _retryRetryableErrorMessages__return = port.retryRetryableErrorMessages(_retryRetryableErrorMessages_destID);
            System.out.println("retryRetryableErrorMessages.result=" + _retryRetryableErrorMessages__return);

        } catch (IllegalArgumentException_Exception e) {
            System.out.println("Expected exception: IllegalArgumentException has occurred.");
            System.out.println(e.toString());
        } catch (WsiAuthenticationException_Exception e) {
            System.out.println("Expected exception: WsiAuthenticationException has occurred.");
            System.out.println(e.toString());
        }

            }
        {
        System.out.println("Invoking configureDestination...");
        int _configureDestination_destID = 0;
        int _configureDestination_timeToWaitInSec = 0;
        java.lang.Integer _configureDestination_maxretries = null;
        java.lang.Long _configureDestination_initialretryinterval = null;
        java.lang.Integer _configureDestination_retrybackoffmultiplier = null;
        java.lang.Integer _configureDestination_pollinterval = null;
        java.lang.Integer _configureDestination_numsenderthreads = null;
        java.lang.Integer _configureDestination_chunksize = null;
        try {
            port.configureDestination(_configureDestination_destID, _configureDestination_timeToWaitInSec, _configureDestination_maxretries, _configureDestination_initialretryinterval, _configureDestination_retrybackoffmultiplier, _configureDestination_pollinterval, _configureDestination_numsenderthreads, _configureDestination_chunksize);

        } catch (IllegalArgumentException_Exception e) {
            System.out.println("Expected exception: IllegalArgumentException has occurred.");
            System.out.println(e.toString());
        } catch (WsiAuthenticationException_Exception e) {
            System.out.println("Expected exception: WsiAuthenticationException has occurred.");
            System.out.println(e.toString());
        }

            }
        {
        System.out.println("Invoking isSuspended...");
        int _isSuspended_destID = 0;
        framework.integrations.gwServices.messagingToolsAPI.bc.MessageProcessingDirection _isSuspended_direction = null;
        try {
            boolean _isSuspended__return = port.isSuspended(_isSuspended_destID, _isSuspended_direction);
            System.out.println("isSuspended.result=" + _isSuspended__return);

        } catch (IllegalArgumentException_Exception e) {
            System.out.println("Expected exception: IllegalArgumentException has occurred.");
            System.out.println(e.toString());
        } catch (WsiAuthenticationException_Exception e) {
            System.out.println("Expected exception: WsiAuthenticationException has occurred.");
            System.out.println(e.toString());
        }

            }
        {
        System.out.println("Invoking suspendDestination...");
        int _suspendDestination_destID = 0;
        framework.integrations.gwServices.messagingToolsAPI.bc.MessageProcessingDirection _suspendDestination_direction = null;
        try {
            boolean _suspendDestination__return = port.suspendDestination(_suspendDestination_destID, _suspendDestination_direction);
            System.out.println("suspendDestination.result=" + _suspendDestination__return);

        } catch (IllegalArgumentException_Exception e) {
            System.out.println("Expected exception: IllegalArgumentException has occurred.");
            System.out.println(e.toString());
        } catch (SOAPException_Exception e) {
            System.out.println("Expected exception: SOAPException has occurred.");
            System.out.println(e.toString());
        } catch (SOAPSenderException_Exception e) {
            System.out.println("Expected exception: SOAPSenderException has occurred.");
            System.out.println(e.toString());
        } catch (WsiAuthenticationException_Exception e) {
            System.out.println("Expected exception: WsiAuthenticationException has occurred.");
            System.out.println(e.toString());
        }

            }
        {
        System.out.println("Invoking getTotalStatistics...");
        int _getTotalStatistics_destID = 0;
        try {
            framework.integrations.gwServices.messagingToolsAPI.bc.MessageStatisticsData _getTotalStatistics__return = port.getTotalStatistics(_getTotalStatistics_destID);
            System.out.println("getTotalStatistics.result=" + _getTotalStatistics__return);

        } catch (IllegalArgumentException_Exception e) {
            System.out.println("Expected exception: IllegalArgumentException has occurred.");
            System.out.println(e.toString());
        } catch (WsiAuthenticationException_Exception e) {
            System.out.println("Expected exception: WsiAuthenticationException has occurred.");
            System.out.println(e.toString());
        }

            }
        {
        System.out.println("Invoking resumeDestination...");
        int _resumeDestination_destID = 0;
        framework.integrations.gwServices.messagingToolsAPI.bc.MessageProcessingDirection _resumeDestination_direction = null;
        try {
            boolean _resumeDestination__return = port.resumeDestination(_resumeDestination_destID, _resumeDestination_direction);
            System.out.println("resumeDestination.result=" + _resumeDestination__return);

        } catch (IllegalArgumentException_Exception e) {
            System.out.println("Expected exception: IllegalArgumentException has occurred.");
            System.out.println(e.toString());
        } catch (SOAPException_Exception e) {
            System.out.println("Expected exception: SOAPException has occurred.");
            System.out.println(e.toString());
        } catch (SOAPSenderException_Exception e) {
            System.out.println("Expected exception: SOAPSenderException has occurred.");
            System.out.println(e.toString());
        } catch (WsiAuthenticationException_Exception e) {
            System.out.println("Expected exception: WsiAuthenticationException has occurred.");
            System.out.println(e.toString());
        }

            }
        {
        System.out.println("Invoking skipMessage...");
        long _skipMessage_messageID = 0;
        try {
            boolean _skipMessage__return = port.skipMessage(_skipMessage_messageID);
            System.out.println("skipMessage.result=" + _skipMessage__return);

        } catch (WsiAuthenticationException_Exception e) {
            System.out.println("Expected exception: WsiAuthenticationException has occurred.");
            System.out.println(e.toString());
        }

            }
        {
        System.out.println("Invoking retryRetryableSomeErrorMessages...");
        int _retryRetryableSomeErrorMessages_destID = 0;
        int _retryRetryableSomeErrorMessages_retryLimit = 0;
        try {
            boolean _retryRetryableSomeErrorMessages__return = port.retryRetryableSomeErrorMessages(_retryRetryableSomeErrorMessages_destID, _retryRetryableSomeErrorMessages_retryLimit);
            System.out.println("retryRetryableSomeErrorMessages.result=" + _retryRetryableSomeErrorMessages__return);

        } catch (IllegalArgumentException_Exception e) {
            System.out.println("Expected exception: IllegalArgumentException has occurred.");
            System.out.println(e.toString());
        } catch (WsiAuthenticationException_Exception e) {
            System.out.println("Expected exception: WsiAuthenticationException has occurred.");
            System.out.println(e.toString());
        }

            }
        {
        System.out.println("Invoking purgeCompletedMessages...");
        javax.xml.datatype.XMLGregorianCalendar _purgeCompletedMessages_cutoff = null;
        try {
            port.purgeCompletedMessages(_purgeCompletedMessages_cutoff);

        } catch (IllegalArgumentException_Exception e) {
            System.out.println("Expected exception: IllegalArgumentException has occurred.");
            System.out.println(e.toString());
        } catch (WsiAuthenticationException_Exception e) {
            System.out.println("Expected exception: WsiAuthenticationException has occurred.");
            System.out.println(e.toString());
        }

            }
        {
        System.out.println("Invoking getDestinationStatus...");
        int _getDestinationStatus_destID = 0;
        try {
            java.lang.String _getDestinationStatus__return = port.getDestinationStatus(_getDestinationStatus_destID);
            System.out.println("getDestinationStatus.result=" + _getDestinationStatus__return);

        } catch (IllegalArgumentException_Exception e) {
            System.out.println("Expected exception: IllegalArgumentException has occurred.");
            System.out.println(e.toString());
        } catch (WsiAuthenticationException_Exception e) {
            System.out.println("Expected exception: WsiAuthenticationException has occurred.");
            System.out.println(e.toString());
        }

            }
        {
        System.out.println("Invoking getMessageIdBySenderRefId...");
        java.lang.String _getMessageIdBySenderRefId_senderRefID = "";
        int _getMessageIdBySenderRefId_destID = 0;
        try {
            java.lang.Long _getMessageIdBySenderRefId__return = port.getMessageIdBySenderRefId(_getMessageIdBySenderRefId_senderRefID, _getMessageIdBySenderRefId_destID);
            System.out.println("getMessageIdBySenderRefId.result=" + _getMessageIdBySenderRefId__return);

        } catch (IllegalArgumentException_Exception e) {
            System.out.println("Expected exception: IllegalArgumentException has occurred.");
            System.out.println(e.toString());
        } catch (WsiAuthenticationException_Exception e) {
            System.out.println("Expected exception: WsiAuthenticationException has occurred.");
            System.out.println(e.toString());
        }

            }
        {
        System.out.println("Invoking retryMessage...");
        long _retryMessage_messageID = 0;
        try {
            boolean _retryMessage__return = port.retryMessage(_retryMessage_messageID);
            System.out.println("retryMessage.result=" + _retryMessage__return);

        } catch (WsiAuthenticationException_Exception e) {
            System.out.println("Expected exception: WsiAuthenticationException has occurred.");
            System.out.println(e.toString());
        }

            }
        {
        System.out.println("Invoking retryRetryableErrorMessagesForCategory...");
        int _retryRetryableErrorMessagesForCategory_destID = 0;
        java.lang.String _retryRetryableErrorMessagesForCategory_category = "";
        try {
            boolean _retryRetryableErrorMessagesForCategory__return = port.retryRetryableErrorMessagesForCategory(_retryRetryableErrorMessagesForCategory_destID, _retryRetryableErrorMessagesForCategory_category);
            System.out.println("retryRetryableErrorMessagesForCategory.result=" + _retryRetryableErrorMessagesForCategory__return);

        } catch (IllegalArgumentException_Exception e) {
            System.out.println("Expected exception: IllegalArgumentException has occurred.");
            System.out.println(e.toString());
        } catch (WsiAuthenticationException_Exception e) {
            System.out.println("Expected exception: WsiAuthenticationException has occurred.");
            System.out.println(e.toString());
        }

            }
        {
        System.out.println("Invoking resyncAccount...");
        int _resyncAccount_destID = 0;
        java.lang.String _resyncAccount_accountPublicID = "";
        try {
            port.resyncAccount(_resyncAccount_destID, _resyncAccount_accountPublicID);

        } catch (RequiredFieldException_Exception e) {
            System.out.println("Expected exception: RequiredFieldException has occurred.");
            System.out.println(e.toString());
        } catch (BadIdentifierException_Exception e) {
            System.out.println("Expected exception: BadIdentifierException has occurred.");
            System.out.println(e.toString());
        } catch (WsiAuthenticationException_Exception e) {
            System.out.println("Expected exception: WsiAuthenticationException has occurred.");
            System.out.println(e.toString());
        }

            }
        {
        System.out.println("Invoking resumeDestinationBothDirections...");
        int _resumeDestinationBothDirections_destID = 0;
        try {
            port.resumeDestinationBothDirections(_resumeDestinationBothDirections_destID);

        } catch (IllegalArgumentException_Exception e) {
            System.out.println("Expected exception: IllegalArgumentException has occurred.");
            System.out.println(e.toString());
        } catch (WsiAuthenticationException_Exception e) {
            System.out.println("Expected exception: WsiAuthenticationException has occurred.");
            System.out.println(e.toString());
        }

            }
        {
        System.out.println("Invoking ackMessage...");
        framework.integrations.gwServices.messagingToolsAPI.bc.Acknowledgement _ackMessage_ack = null;
        try {
            boolean _ackMessage__return = port.ackMessage(_ackMessage_ack);
            System.out.println("ackMessage.result=" + _ackMessage__return);

        } catch (IllegalArgumentException_Exception e) {
            System.out.println("Expected exception: IllegalArgumentException has occurred.");
            System.out.println(e.toString());
        } catch (SOAPException_Exception e) {
            System.out.println("Expected exception: SOAPException has occurred.");
            System.out.println(e.toString());
        } catch (SOAPSenderException_Exception e) {
            System.out.println("Expected exception: SOAPSenderException has occurred.");
            System.out.println(e.toString());
        } catch (WsiAuthenticationException_Exception e) {
            System.out.println("Expected exception: WsiAuthenticationException has occurred.");
            System.out.println(e.toString());
        }

            }
        {
        System.out.println("Invoking suspendDestinationBothDirections...");
        int _suspendDestinationBothDirections_destID = 0;
        try {
            port.suspendDestinationBothDirections(_suspendDestinationBothDirections_destID);

        } catch (IllegalArgumentException_Exception e) {
            System.out.println("Expected exception: IllegalArgumentException has occurred.");
            System.out.println(e.toString());
        } catch (WsiAuthenticationException_Exception e) {
            System.out.println("Expected exception: WsiAuthenticationException has occurred.");
            System.out.println(e.toString());
        }

            }
        {
        System.out.println("Invoking getConfiguration...");
        int _getConfiguration_destID = 0;
        try {
            framework.integrations.gwServices.messagingToolsAPI.bc.ExternalDestinationConfig _getConfiguration__return = port.getConfiguration(_getConfiguration_destID);
            System.out.println("getConfiguration.result=" + _getConfiguration__return);

        } catch (IllegalArgumentException_Exception e) {
            System.out.println("Expected exception: IllegalArgumentException has occurred.");
            System.out.println(e.toString());
        } catch (WsiAuthenticationException_Exception e) {
            System.out.println("Expected exception: WsiAuthenticationException has occurred.");
            System.out.println(e.toString());
        }

            }
        {
        System.out.println("Invoking isResumed...");
        int _isResumed_destID = 0;
        framework.integrations.gwServices.messagingToolsAPI.bc.MessageProcessingDirection _isResumed_direction = null;
        try {
            boolean _isResumed__return = port.isResumed(_isResumed_destID, _isResumed_direction);
            System.out.println("isResumed.result=" + _isResumed__return);

        } catch (IllegalArgumentException_Exception e) {
            System.out.println("Expected exception: IllegalArgumentException has occurred.");
            System.out.println(e.toString());
        } catch (WsiAuthenticationException_Exception e) {
            System.out.println("Expected exception: WsiAuthenticationException has occurred.");
            System.out.println(e.toString());
        }

            }
        {
        System.out.println("Invoking getMessageStatisticsForSafeOrderedObject...");
        int _getMessageStatisticsForSafeOrderedObject_destID = 0;
        java.lang.String _getMessageStatisticsForSafeOrderedObject_safeOrderedObjectId = "";
        try {
            framework.integrations.gwServices.messagingToolsAPI.bc.MessageStatisticsData _getMessageStatisticsForSafeOrderedObject__return = port.getMessageStatisticsForSafeOrderedObject(_getMessageStatisticsForSafeOrderedObject_destID, _getMessageStatisticsForSafeOrderedObject_safeOrderedObjectId);
            System.out.println("getMessageStatisticsForSafeOrderedObject.result=" + _getMessageStatisticsForSafeOrderedObject__return);

        } catch (IllegalArgumentException_Exception e) {
            System.out.println("Expected exception: IllegalArgumentException has occurred.");
            System.out.println(e.toString());
        } catch (WsiAuthenticationException_Exception e) {
            System.out.println("Expected exception: WsiAuthenticationException has occurred.");
            System.out.println(e.toString());
        }

            }
        {
        System.out.println("Invoking resyncPolicyPeriod...");
        int _resyncPolicyPeriod_destID = 0;
        java.lang.String _resyncPolicyPeriod_policyPeriodPublicID = "";
        try {
            port.resyncPolicyPeriod(_resyncPolicyPeriod_destID, _resyncPolicyPeriod_policyPeriodPublicID);

        } catch (RequiredFieldException_Exception e) {
            System.out.println("Expected exception: RequiredFieldException has occurred.");
            System.out.println(e.toString());
        } catch (BadIdentifierException_Exception e) {
            System.out.println("Expected exception: BadIdentifierException has occurred.");
            System.out.println(e.toString());
        } catch (WsiAuthenticationException_Exception e) {
            System.out.println("Expected exception: WsiAuthenticationException has occurred.");
            System.out.println(e.toString());
        }

            }

        System.exit(0);
    }

}
