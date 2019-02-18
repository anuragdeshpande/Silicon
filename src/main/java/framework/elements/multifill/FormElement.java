package framework.elements.multifill;

import framework.elements.Identifier;

public class FormElement {

    private Identifier identifier;
    private String value;

    public <T> FormElement(Identifier identifier, T value) {
        this.identifier = identifier;
        this.value = String.valueOf(value);
    }

/*    public void fill(UIActions interact) {
        switch (this.identifier.getElementType()) {
            case TEXT_BOX:
                interact.withTexbox(identifier).fill(this.value);
                break;
        }
    }*/
}
