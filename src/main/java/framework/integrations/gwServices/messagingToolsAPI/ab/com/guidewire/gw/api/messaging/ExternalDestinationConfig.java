
package framework.integrations.gwServices.messagingToolsAPI.ab.com.guidewire.gw.api.messaging;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ExternalDestinationConfig complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ExternalDestinationConfig">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ChunkSize" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="InitialRetryInterval" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="MaxRetries" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="MultiThreadNonSafeOrderedMessages" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="NumSenderThreads" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="PollInterval" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="RetryBackoffMultiplier" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="ShutdownTimeout" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="SingleThreadOnNonSafeOrderedMessages" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExternalDestinationConfig", propOrder = {
    "chunkSize",
    "initialRetryInterval",
    "maxRetries",
    "multiThreadNonSafeOrderedMessages",
    "numSenderThreads",
    "pollInterval",
    "retryBackoffMultiplier",
    "shutdownTimeout",
    "singleThreadOnNonSafeOrderedMessages"
})
public class ExternalDestinationConfig {

    @XmlElement(name = "ChunkSize")
    protected Integer chunkSize;
    @XmlElement(name = "InitialRetryInterval")
    protected Long initialRetryInterval;
    @XmlElement(name = "MaxRetries")
    protected Integer maxRetries;
    @XmlElement(name = "MultiThreadNonSafeOrderedMessages")
    protected Boolean multiThreadNonSafeOrderedMessages;
    @XmlElement(name = "NumSenderThreads")
    protected Integer numSenderThreads;
    @XmlElement(name = "PollInterval")
    protected Integer pollInterval;
    @XmlElement(name = "RetryBackoffMultiplier")
    protected Integer retryBackoffMultiplier;
    @XmlElement(name = "ShutdownTimeout")
    protected Integer shutdownTimeout;
    @XmlElement(name = "SingleThreadOnNonSafeOrderedMessages")
    protected Boolean singleThreadOnNonSafeOrderedMessages;

    /**
     * Gets the value of the chunkSize property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getChunkSize() {
        return chunkSize;
    }

    /**
     * Sets the value of the chunkSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setChunkSize(Integer value) {
        this.chunkSize = value;
    }

    /**
     * Gets the value of the initialRetryInterval property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getInitialRetryInterval() {
        return initialRetryInterval;
    }

    /**
     * Sets the value of the initialRetryInterval property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setInitialRetryInterval(Long value) {
        this.initialRetryInterval = value;
    }

    /**
     * Gets the value of the maxRetries property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMaxRetries() {
        return maxRetries;
    }

    /**
     * Sets the value of the maxRetries property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMaxRetries(Integer value) {
        this.maxRetries = value;
    }

    /**
     * Gets the value of the multiThreadNonSafeOrderedMessages property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isMultiThreadNonSafeOrderedMessages() {
        return multiThreadNonSafeOrderedMessages;
    }

    /**
     * Sets the value of the multiThreadNonSafeOrderedMessages property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMultiThreadNonSafeOrderedMessages(Boolean value) {
        this.multiThreadNonSafeOrderedMessages = value;
    }

    /**
     * Gets the value of the numSenderThreads property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumSenderThreads() {
        return numSenderThreads;
    }

    /**
     * Sets the value of the numSenderThreads property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumSenderThreads(Integer value) {
        this.numSenderThreads = value;
    }

    /**
     * Gets the value of the pollInterval property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPollInterval() {
        return pollInterval;
    }

    /**
     * Sets the value of the pollInterval property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPollInterval(Integer value) {
        this.pollInterval = value;
    }

    /**
     * Gets the value of the retryBackoffMultiplier property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRetryBackoffMultiplier() {
        return retryBackoffMultiplier;
    }

    /**
     * Sets the value of the retryBackoffMultiplier property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRetryBackoffMultiplier(Integer value) {
        this.retryBackoffMultiplier = value;
    }

    /**
     * Gets the value of the shutdownTimeout property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getShutdownTimeout() {
        return shutdownTimeout;
    }

    /**
     * Sets the value of the shutdownTimeout property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setShutdownTimeout(Integer value) {
        this.shutdownTimeout = value;
    }

    /**
     * Gets the value of the singleThreadOnNonSafeOrderedMessages property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSingleThreadOnNonSafeOrderedMessages() {
        return singleThreadOnNonSafeOrderedMessages;
    }

    /**
     * Sets the value of the singleThreadOnNonSafeOrderedMessages property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSingleThreadOnNonSafeOrderedMessages(Boolean value) {
        this.singleThreadOnNonSafeOrderedMessages = value;
    }

}
