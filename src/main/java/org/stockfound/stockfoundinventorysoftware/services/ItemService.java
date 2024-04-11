package org.stockfound.stockfoundinventorysoftware.services;

import javafx.collections.ObservableList;
import org.stockfound.stockfoundinventorysoftware.database.Database;
import org.stockfound.stockfoundinventorysoftware.database.ItemRepository;
import org.stockfound.stockfoundinventorysoftware.database.PostgreSQLDatabase;
import org.stockfound.stockfoundinventorysoftware.entities.Item;
import org.stockfound.stockfoundinventorysoftware.utils.DatabaseCredentialsLoader;

public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService() {
        Database database = new PostgreSQLDatabase(
                DatabaseCredentialsLoader.loadCredentials().host(),
                DatabaseCredentialsLoader.loadCredentials().port(),
                DatabaseCredentialsLoader.loadCredentials().database(),
                DatabaseCredentialsLoader.loadCredentials().username(),
                DatabaseCredentialsLoader.loadCredentials().password());

        this.itemRepository = new ItemRepository(database);
    }

    public ObservableList<Item> getAllItems(){
        return itemRepository.getAllItems();
    }

    public void deleteItem(int id){

        itemRepository.deleteItem(id);
    }

    public void addItem(Item item){
        itemRepository.addItem(item);
    }


    public void updateItem(Item item) {
        itemRepository.updateItem(item);
    }
}
