package framework.webdriver.interactionContracts;

import framework.constants.ReactionTime;
import framework.elements.Identifier;

public interface ICanInteractWithSelectBox<T> {
    T withSelectBox(Identifier identifier);
    T withOptionalSelectBox(Identifier identifier, ReactionTime reactionTime);
}
