
package framework.integrations.gwServices.messagingToolsAPI.pc.com.guidewire.pc.ws.gw.webservice.pc.pc800.messagingtoolsapi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="destID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="timeToWaitInSec" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="maxretries" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="initialretryinterval" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="retrybackoffmultiplier" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="pollinterval" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="numsenderthreads" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="chunksize" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "destID",
    "timeToWaitInSec",
    "maxretries",
    "initialretryinterval",
    "retrybackoffmultiplier",
    "pollinterval",
    "numsenderthreads",
    "chunksize"
})
@XmlRootElement(name = "configureDestination")
public class ConfigureDestination {

    protected int destID;
    protected int timeToWaitInSec;
    protected Integer maxretries;
    protected Long initialretryinterval;
    protected Integer retrybackoffmultiplier;
    protected Integer pollinterval;
    protected Integer numsenderthreads;
    protected Integer chunksize;

    /**
     * Gets the value of the destID property.
     * 
     */
    public int getDestID() {
        return destID;
    }

    /**
     * Sets the value of the destID property.
     * 
     */
    public void setDestID(int value) {
        this.destID = value;
    }

    /**
     * Gets the value of the timeToWaitInSec property.
     * 
     */
    public int getTimeToWaitInSec() {
        return timeToWaitInSec;
    }

    /**
     * Sets the value of the timeToWaitInSec property.
     * 
     */
    public void setTimeToWaitInSec(int value) {
        this.timeToWaitInSec = value;
    }

    /**
     * Gets the value of the maxretries property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMaxretries() {
        return maxretries;
    }

    /**
     * Sets the value of the maxretries property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMaxretries(Integer value) {
        this.maxretries = value;
    }

    /**
     * Gets the value of the initialretryinterval property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getInitialretryinterval() {
        return initialretryinterval;
    }

    /**
     * Sets the value of the initialretryinterval property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setInitialretryinterval(Long value) {
        this.initialretryinterval = value;
    }

    /**
     * Gets the value of the retrybackoffmultiplier property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRetrybackoffmultiplier() {
        return retrybackoffmultiplier;
    }

    /**
     * Sets the value of the retrybackoffmultiplier property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRetrybackoffmultiplier(Integer value) {
        this.retrybackoffmultiplier = value;
    }

    /**
     * Gets the value of the pollinterval property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPollinterval() {
        return pollinterval;
    }

    /**
     * Sets the value of the pollinterval property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPollinterval(Integer value) {
        this.pollinterval = value;
    }

    /**
     * Gets the value of the numsenderthreads property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumsenderthreads() {
        return numsenderthreads;
    }

    /**
     * Sets the value of the numsenderthreads property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumsenderthreads(Integer value) {
        this.numsenderthreads = value;
    }

    /**
     * Gets the value of the chunksize property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getChunksize() {
        return chunksize;
    }

    /**
     * Sets the value of the chunksize property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setChunksize(Integer value) {
        this.chunksize = value;
    }

}
