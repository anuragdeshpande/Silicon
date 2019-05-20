package framework.elements.selectbox;

import framework.elements.Identifier;
import framework.elements.ui_element.UIElement;

public abstract class UISelect extends UIElement implements IUISelectBox {
    public UISelect(Identifier identifier) {
        super(identifier);
    }

    public UISelect(Identifier identifier, boolean isOptional) {
        super(identifier, isOptional);
    }
}
