package org.stockfound.stockfoundinventorysoftware.services;

import javafx.collections.ObservableList;
import org.stockfound.stockfoundinventorysoftware.database.Database;
import org.stockfound.stockfoundinventorysoftware.database.ItemRepository;
import org.stockfound.stockfoundinventorysoftware.database.PostgreSQLDatabase;
import org.stockfound.stockfoundinventorysoftware.entities.Item;

public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService() {
        Database database = new PostgreSQLDatabase("localhost", "5432", "stockfound_database", "postgres", "120708LUciano");
        this.itemRepository = new ItemRepository(database);
    }

    public ObservableList<Item> getAllItems(){
        return itemRepository.getAllItems();
    }

    public void deleteItem(String serialNumber){

        itemRepository.deleteItem(serialNumber);
    }

    public void addItem(Item item){
        itemRepository.addItem(item);
    }


}
