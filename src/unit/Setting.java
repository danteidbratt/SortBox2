package unit;

public abstract class Setting {

    private final String identifier;
    private final int value;

    protected Setting(String identifier, int value) {
        this.identifier = identifier;
        this.value = value;
    }

    public String getIdentifier() {
        return identifier;
    }

    public int getValue() {
        return value;
    }

}