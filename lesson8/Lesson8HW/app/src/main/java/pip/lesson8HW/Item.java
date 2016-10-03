package pip.lesson8HW;

/**
 * Defines an individual to-do item, for use in TodoAdapter
 */
public class Item {
    private long id;
    private boolean done;
    private String item;

    public Item(long id, boolean done, String item) {
        this.id = id;
        this.done = done;
        this.item = item;
    }

    public long getId() {
        return id;
    }

    public boolean isDone() {
        return done;
    }

    public String getItem() {
        return item;
    }

    public void setId(long newId) {
        id = newId;
    }

    public void setDone(boolean newDone) {
        done = newDone;
    }

    public void setItem(String newItem) {
        item = newItem;
    }
}
