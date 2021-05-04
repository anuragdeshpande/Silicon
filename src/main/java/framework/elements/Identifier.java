package framework.elements;

import framework.elements.enums.ElementType;
import framework.logger.RegressionLogger;
import framework.webdriver.DriverFactory;
import org.openqa.selenium.By;

public class Identifier extends GenericIdentifier {
    private By reference;
    private boolean canThrowWarning;
    private String friendlyName;
    private boolean ignoreTableLVCheck = true;
    private int timeoutInSeconds;

    protected Identifier(){
        setDefaultTimeout();
    }

    protected Identifier(By reference, ElementType type){
        setDefaultTimeout();
    }

    public Identifier(By reference) {
        canThrowWarning = false;
        this.reference = reference;
        setDefaultTimeout();
    }

    public Identifier(By reference, String friendlyName){
        this.reference = reference;
        this.friendlyName = friendlyName;
        this.canThrowWarning = false;
        setDefaultTimeout();
    }

    public Identifier(By reference, String friendlyName, boolean checkForWarning){
        this(reference, friendlyName);
        this.canThrowWarning = checkForWarning;
        setDefaultTimeout();
    }

    public boolean shouldCheckForWarning() {
        return canThrowWarning;
    }

    public Identifier ignoreLVCheckForTable(){
        this.ignoreTableLVCheck = false;
        return this;
    }

    public Identifier doNotIgnoreLVCheckForTable(){
        this.ignoreTableLVCheck = true;
        return this;
    }

    public By getReference() {
        return reference;
    }

    public String getFriendlyName() {
        return friendlyName == null ? "Please add friendly name to this identifier" : friendlyName;
    }

    public boolean shouldIgnoreLVCheckForTable() {
        return ignoreTableLVCheck;
    }

    public String getReferenceValue(){
        return (this.reference.toString().split(":")[1]).trim();
    }

    public int getTimeout(){
        return this.timeoutInSeconds;
    }

    public Identifier setTimeoutInSeconds(int newTimeoutInSeconds){
        RegressionLogger testLogger = RegressionLogger.getTestLogger();
        testLogger.addTag("Custom_Timeout");
        testLogger.info("Default timeout is being changed from: "+getTimeout()+" seconds to: "+newTimeoutInSeconds+" seconds");
        this.timeoutInSeconds = newTimeoutInSeconds;
        return this;
    }

    private void setDefaultTimeout(){
        this.timeoutInSeconds = ((int) DriverFactory.getReactionTime().getTime());
    }


}
