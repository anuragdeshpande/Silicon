package framework.applications.portals.elements.tables;

import framework.constants.ReactionTime;
import framework.elements.Identifier;
import framework.elements.ui_element.UIElement;
import org.openqa.selenium.WebElement;

public class PortalTableCell extends UIElement {
    public PortalTableCell(Identifier identifier) {
        super(identifier);
    }

    public PortalTableCell(Identifier identifier, ReactionTime reactionTime) {
        super(identifier, reactionTime);
    }

    protected PortalTableCell(WebElement element) {
        super(element);
    }
}
