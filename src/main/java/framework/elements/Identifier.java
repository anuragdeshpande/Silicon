package framework.elements;

import framework.elements.enums.ElementType;
import org.openqa.selenium.By;

public class Identifier {
    private By reference;
    private ElementType elementType;
    private boolean canThrowWarning;

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

    public By getReference() {
        return reference;
    }

    public ElementType getElementType() {
        return elementType;
    }
}
