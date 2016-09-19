package pip.lesson5HW;

/**
 * Defines an individual to-do item, for use in TodoAdapter
 */
public class Item {
    private boolean done;
    private String item;

    public Item(boolean done, String item) {
        this.done = done;
        this.item = item;
    }

    public boolean isDone() {
        return done;
    }

    public String getItem() {
        return item;
    }

    public void setDone(boolean newDone) {
        done = newDone;
    }

    public void setItem(String newItem) {
        item = newItem;
    }
}
