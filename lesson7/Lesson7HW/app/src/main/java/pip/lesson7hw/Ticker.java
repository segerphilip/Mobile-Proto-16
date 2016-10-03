package pip.lesson7hw;

/**
 * A single instance of a stock ticker, consisting of its Name and Value.
 */

public class Ticker {
    private String name;
    private int value;

    public Ticker(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
