package framework.elements;

import framework.elements.enums.ElementType;
import org.openqa.selenium.By;

public class Identifier extends GenericIdentifier {
    private By reference;
    private boolean canThrowWarning;
    private String friendlyName;

    protected Identifier(){

    }

    protected Identifier(By reference, ElementType type){

    }

    public Identifier(By reference) {
        canThrowWarning = false;
        this.reference = reference;
    }

    public Identifier(By reference, String friendlyName){
        this.reference = reference;
        this.friendlyName = friendlyName;
        this.canThrowWarning = false;
    }

    public Identifier(By reference, String friendlyName, boolean checkForWarning){
        this(reference, friendlyName);
        this.canThrowWarning = checkForWarning;
    }

    public boolean shouldCheckForWarning() {
        return canThrowWarning;
    }

    public By getReference() {
        return reference;
    }

    public boolean canThrowWarning() {
        return canThrowWarning;
    }

    public String getFriendlyName() {
        return friendlyName == null ? "Please add friendly name to this identifier" : friendlyName;
    }

    public String getReferenceValue(){
        return this.reference.toString().split(":")[1];
    }
}
