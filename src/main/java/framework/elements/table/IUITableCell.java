package framework.elements.table;

public interface IUITableCell {
    String getText();

    void clickSelect();

    void clickLink();

    void fillTextArea(String text);

    void fillTextBox(String text);

    void clickCheckbox();

    void markCheckBox();

    void unMarkCheckBox();

    boolean isMarked();
}
