package framework.webdriver.interactionContracts;

import framework.constants.ReactionTime;
import framework.elements.Identifier;

public interface ICanInteractWithConfirmationWindow<T> {
    T withConfirmationWindow();
    T withOptionalConfirmationWindow(ReactionTime reactionTime);
}
