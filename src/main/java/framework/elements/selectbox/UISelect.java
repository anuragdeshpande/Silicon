package framework.elements.selectbox;

import framework.constants.ReactionTime;
import framework.elements.Identifier;
import framework.elements.ui_element.UIElement;
import org.openqa.selenium.WebElement;

public abstract class UISelect extends UIElement implements IUISelectBox {

    public UISelect(WebElement element) {
        super(element);
    }

    public UISelect(Identifier identifier) {
        super(identifier);
    }
    /**\
     *
     * Use of this constructor automatically assumes the Select Box in question is
     * optional and will wait upto the reaction time passed as parameter
     *
     * @param identifier element Identifier
     * @param reactionTime time to wait
     *
     *
     */
    public UISelect(Identifier identifier, ReactionTime reactionTime) {
        super(identifier, reactionTime);
    }
}
