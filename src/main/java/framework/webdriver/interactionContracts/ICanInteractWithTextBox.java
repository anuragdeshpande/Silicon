package framework.webdriver.interactionContracts;

import framework.constants.ReactionTime;
import framework.elements.Identifier;

public interface ICanInteractWithTextBox<T> {
    T withTextbox(Identifier identifier);
    T withOptionalTextbox(Identifier identifier, ReactionTime reactionTime);
}
