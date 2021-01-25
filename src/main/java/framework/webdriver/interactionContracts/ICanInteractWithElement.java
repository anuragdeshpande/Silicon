package framework.webdriver.interactionContracts;

import framework.constants.ReactionTime;
import framework.elements.Identifier;

import java.util.Optional;

public interface ICanInteractWithElement<T> {
    T withElement(Identifier identifier);
    T withOptionalElement(Identifier identifier, ReactionTime reactionTime);
}
