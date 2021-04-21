package framework.webdriver.interactionContracts;

import framework.constants.ReactionTime;

public interface ICanInteractWithConfirmationWindow<T> {
    T withConfirmationWindow();
    T withOptionalConfirmationWindow(ReactionTime reactionTime);
}
