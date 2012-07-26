package com.threeheadedmonkey.teepee.respository;

import com.threeheadedmonkey.teepee.entity.Consolidator;
import com.threeheadedmonkey.teepee.entity.Item;
import com.threeheadedmonkey.teepee.io.FileReader;

import javax.enterprise.context.ApplicationScoped;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Store a list of items in memory by key
 */
@ApplicationScoped
public class InMemoryRepository implements ItemRepository {

    private final Map<String, Collection<Item>> store;

    public InMemoryRepository() {
        this.store = new HashMap<String, Collection<Item>>();
        Collection<Item> items = null;
        try {
            items = readFromFile();
            this.store.put("asdf", items);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Collection<Item> get(String key) {
        return store.get(key);
    }

    @Override
    public void put(String key, Collection<Item> items) {
        if (key == null || key.isEmpty()) {
            throw new IllegalArgumentException("Key must have value");
        }
        if (items == null) {
            store.remove(key);
        } else {
            store.put(key, items);
        }
    }

    private Collection<Item> readFromFile() throws FileNotFoundException {
        Collection<Item> items = new FileReader().read(new java.io.FileReader("src/test/resources/Personal-output.taskpaper"));
        Collection<Item> consolidatedItems = new Consolidator(items).consolidate();
        return consolidatedItems;
    }

}
