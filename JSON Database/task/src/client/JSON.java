package client;

public class JSON {
    private String type;
    private String key;

    private String value;

    public String getType() {
        return type;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public JSON(String type, String key, String value) {
        this.type = type;
        this.key = key;
        this.value = value;
    }

    public JSON(String type, String key) {
        this.type = type;
        this.key = key;
    }

    public JSON(String type) {
        this.type = type;
    }
}
