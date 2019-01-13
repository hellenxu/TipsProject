package six.ca.droiddailyproject.eventbus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Xiaolin on 2016-06-17.
 */
public class Item {
    public String id;
    public String content;
    public static List<Item> items = new ArrayList<>();

    static {
        addItem(new Item("1", "item 1"));
        addItem(new Item("2", "item 2"));
        addItem(new Item("3", "item 3"));
        addItem(new Item("4", "item 4"));
        addItem(new Item("5", "item 5"));
        addItem(new Item("6", "item 6"));
    }

    private static void addItem(Item item){
        items.add(item);
    }

    public Item (String id, String content){
        this.id = id;
        this.content = content;
    }

    @Override
    public String toString() {
        return content;
    }
}
