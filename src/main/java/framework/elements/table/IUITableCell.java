package framework.elements.table;

public interface IUITableCell {
    String getText();

    void clickSelect();

    void clickLink();

    void fillTextArea(String text);

    void fillTextBox(String text);

    /**
     * @deprecated use mark/unmark checkbox instead
     */
    @Deprecated
    void clickCheckbox();

    void markCheckBox();

    void unMarkCheckBox();

    boolean isMarked();
}
