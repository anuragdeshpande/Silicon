package framework.elements;

import framework.elements.enums.ElementType;
import org.openqa.selenium.By;

public class Identifier extends GenericIdentifier {
    private By reference;
    private ElementType elementType;
    private boolean canThrowWarning;
    private boolean shouldGuardAgainstRaceCondition;

    public Identifier(By reference) {
        canThrowWarning = false;
        this.reference = reference;
    }

    public Identifier(By reference, ElementType elementType) {
        this.reference = reference;
        this.elementType = elementType;
        canThrowWarning = false;
    }

    public Identifier(By reference, ElementType elementType, boolean checkForWarning) {
        this.reference = reference;
        this.elementType = elementType;
        canThrowWarning = checkForWarning;
    }

    public boolean shouldCheckForWarning() {
        return canThrowWarning;
    }


    /**
     *  this is not a good approach for resolving race conditions.
     *  Please contact GW developer to take a look at the object in question and
     *  add safeguards against threading in the system.
     *
     *  The intent of this method is only for temporary safeguard.
     */
    @Deprecated
    public Identifier  guardAgainstRaceCondition(){
        this.shouldGuardAgainstRaceCondition = true;
        return this;
    }

    public boolean shouldGuardRaceCondition(){
        return this.shouldGuardAgainstRaceCondition;
    }

    public By getReference() {
        return reference;
    }

    public ElementType getElementType() {
        return elementType;
    }
}
