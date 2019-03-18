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

    public Identifier setPossibleWarningMessage(boolean value){
        this.canThrowWarning = value;
        return this;
    }

    public By getReference() {
        return reference;
    }

    public ElementType getElementType() {
        return elementType;
    }
}
