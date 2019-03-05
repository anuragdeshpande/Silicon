package framework.elements.radiobutton;

import framework.elements.Identifier;
import framework.elements.ui_element.UIElement;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class UIRadioButton extends UIElement implements IUIRadioButton {

    protected String label;

    public UIRadioButton(Identifier identifier) {
        super(identifier);
    }

    public UIRadioButton(Identifier identifier, boolean isOptional) {
        super(identifier, isOptional);
    }

    @Override
    public void clickByLabel(String label) {
        this.label = label;
        throw new NotImplementedException();
    }

    @Override
    public void clickYes() {
        throw new NotImplementedException();
    }

    @Override
    public void clickNo() {
        throw new NotImplementedException();
    }
}
