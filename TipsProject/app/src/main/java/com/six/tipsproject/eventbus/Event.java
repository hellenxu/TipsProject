package com.six.tipsproject.eventbus;

import java.util.List;

/**
 * Created by Xiaolin on 2016-06-17.
 */
public class Event {
    public static class ItemListEvent{
        private List<Item> items;

        public ItemListEvent(List<Item> items){
            this.items = items;
        }

        public List<Item> getItems(){
            return items;
        }
    }
}
