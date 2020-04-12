package framework.webdriver.interactionContracts;

import framework.constants.ReactionTime;
import framework.elements.Identifier;

public interface ICanInteractWithRadioButton<T> {
    T withRadioButton(Identifier identifier);
    T withOptionalRadioButton(Identifier identifier, ReactionTime reactionTime);
}
