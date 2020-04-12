package framework.webdriver.interactionContracts;

import framework.constants.ReactionTime;
import framework.elements.Identifier;

public interface ICanInteractWithCheckbox<T> {
    T withCheckbox(Identifier identifier);
    T withOptionalCheckbox(Identifier identifier, ReactionTime reactionTime);
}
