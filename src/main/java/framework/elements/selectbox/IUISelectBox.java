package framework.elements.selectbox;

import java.util.List;

public interface IUISelectBox {
    void select(String selection);

    String selectRandom();

    String select(int itemNumber);

    boolean hasOption(String selection);

    List<String> getOptions();

    String selectFirstExisting(String[] strings);

    String selectByPartial(String selection);
}
