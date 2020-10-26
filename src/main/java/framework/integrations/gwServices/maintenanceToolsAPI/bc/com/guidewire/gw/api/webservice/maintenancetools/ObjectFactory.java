
package framework.integrations.gwServices.maintenanceToolsAPI.bc.com.guidewire.gw.api.webservice.maintenancetools;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.guidewire.gw.api.webservice.maintenancetools package. 
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


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.guidewire.gw.api.webservice.maintenancetools
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link WQueueStatus }
     * 
     */
    public WQueueStatus createWQueueStatus() {
        return new WQueueStatus();
    }

    /**
     * Create an instance of {@link WQueueExecutorDetails }
     * 
     */
    public WQueueExecutorDetails createWQueueExecutorDetails() {
        return new WQueueExecutorDetails();
    }

    /**
     * Create an instance of {@link ProcessStatus }
     * 
     */
    public ProcessStatus createProcessStatus() {
        return new ProcessStatus();
    }

    /**
     * Create an instance of {@link WorkQueueConfig }
     * 
     */
    public WorkQueueConfig createWorkQueueConfig() {
        return new WorkQueueConfig();
    }

    /**
     * Create an instance of {@link WQTaskDetails }
     * 
     */
    public WQTaskDetails createWQTaskDetails() {
        return new WQTaskDetails();
    }

    /**
     * Create an instance of {@link WQueueStatus.Executors }
     * 
     */
    public WQueueStatus.Executors createWQueueStatusExecutors() {
        return new WQueueStatus.Executors();
    }

    /**
     * Create an instance of {@link WQueueExecutorDetails.Tasks }
     * 
     */
    public WQueueExecutorDetails.Tasks createWQueueExecutorDetailsTasks() {
        return new WQueueExecutorDetails.Tasks();
    }

}
