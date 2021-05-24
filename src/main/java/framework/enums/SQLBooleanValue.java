package framework.enums;

public enum SQLBooleanValue {
    TRUE(1),
    FALSE(0);

    private final int value;

    SQLBooleanValue(int value) {
        this.value = value;
    }

    public int getIntValue() {
        return value;
    }

    public String getStringValue() {
        return value+"";
    }
}
