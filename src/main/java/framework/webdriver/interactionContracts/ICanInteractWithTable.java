package framework.webdriver.interactionContracts;

import framework.constants.ReactionTime;
import framework.elements.Identifier;

import java.util.Optional;

public interface ICanInteractWithTable<T> {
    T withTable(Identifier identifier);
    T withOptionalTable(Identifier identifier, ReactionTime reactionTime);
}
