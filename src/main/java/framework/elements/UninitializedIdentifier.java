package framework.elements;

import framework.elements.enums.ElementType;
import org.openqa.selenium.By;

public class UninitializedIdentifier extends Identifier{

    public UninitializedIdentifier(By reference, ElementType type) {
        super(reference, type);
    }

    public UninitializedIdentifier(By reference) {
        super(reference);
    }
}
