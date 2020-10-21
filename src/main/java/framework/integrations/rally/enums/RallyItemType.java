package framework.integrations.rally.enums;

public enum RallyItemType {
    TEST_CASE("TestCase"),
    USER_STORY("HierarchicalRequirement"),
    TEST_CASE_RESULT("testcaseresult"),
    USER("user"),
    DEFECT("Defect");

    private final String item_type_key;

    RallyItemType(String item_type_key) {
        this.item_type_key = item_type_key;
    }

    public String getItem_type_key() {
        return item_type_key;
    }
}
