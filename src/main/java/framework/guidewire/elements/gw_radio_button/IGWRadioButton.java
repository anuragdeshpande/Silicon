package framework.guidewire.elements.gw_radio_button;

import framework.elements.Identifier;

public interface IGWRadioButton {
    void clickByLabel(String label);
    void clickByLabel(Identifier identifier, String label);
}
