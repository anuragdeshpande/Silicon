package framework.webdriver.interactionContracts;

import framework.constants.ReactionTime;
import framework.elements.Identifier;

public interface ICanInteractWithTextArea<T> {
    T withTextArea(Identifier identifier);
    T withOptionalTextArea(Identifier identifier, ReactionTime reactionTime);
}
