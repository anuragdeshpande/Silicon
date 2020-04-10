package framework.elements.radiobutton;

import framework.constants.ReactionTime;
import framework.elements.Identifier;
import framework.elements.ui_element.UIElement;
import org.apache.commons.lang3.NotImplementedException;

public class UIRadioButton extends UIElement implements IUIRadioButton {

    protected String label;

    public UIRadioButton(Identifier identifier) {
        super(identifier);
    }

    /**\
     *
     * Use of this constructor automatically assumes the Radio Button in question is
     * optional and will wait upto the reaction time passed as parameter
     *
     * @param identifier element Identifier
     * @param reactionTime time to wait
     *
     *
     */
    public UIRadioButton(Identifier identifier, ReactionTime reactionTime) {
        super(identifier, reactionTime);
    }

    @Override
    public void clickByLabel(String label) {
        this.label = label;
        throw new NotImplementedException("This feature is not yet implemented. If this is a required, please raise a ticket on git.idfbins.com under the project.");
    }

    @Override
    public void clickYes() {
        throw new NotImplementedException("This feature is not yet implemented. If this is a required, please raise a ticket on git.idfbins.com under the project.");
    }

    @Override
    public void clickNo() {
        throw new NotImplementedException("This feature is not yet implemented. If this is a required, please raise a ticket on git.idfbins.com under the project.");
    }
}
