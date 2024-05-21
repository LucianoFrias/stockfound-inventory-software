package org.stockfound.stockfoundinventorysoftware.controllers.main_controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.stockfound.stockfoundinventorysoftware.controllers.crud_controllers.crud_items.AddItemViewController;
import org.stockfound.stockfoundinventorysoftware.controllers.crud_controllers.crud_items.DeleteItemViewController;
import org.stockfound.stockfoundinventorysoftware.controllers.crud_controllers.crud_items.EditItemViewController;
import org.stockfound.stockfoundinventorysoftware.entities.Item;
import org.stockfound.stockfoundinventorysoftware.services.ItemService;

import java.net.URL;
import java.util.ResourceBundle;

import static org.stockfound.stockfoundinventorysoftware.utils.CustomJavaFX.*;

public class ItemsViewController implements Initializable {

    // Classes
    private final ItemService itemService;

    // FXML
    @FXML
    private TextField itemSearchBar;
    @FXML
    private ChoiceBox itemSearchFilters;
    @FXML
    private TableView<Item> itemTableView;

    // Table Columns
    @FXML
    private TableColumn<Item, Integer> idColumn;
    @FXML
    private TableColumn<Item, String> dateColumn;
    @FXML
    private TableColumn<Item, String> serialNumberColumn;
    @FXML
    private TableColumn<Item, String> customerNameColumn;
    @FXML
    private TableColumn<Item, String> typeColumn;
    @FXML
    private TableColumn<Item, String> brandColumn;
    @FXML
    private TableColumn<Item, String> modelColumn;
    @FXML
    private TableColumn<Item, String> statusColumn;
    @FXML
    private TableColumn<Item, Integer> priceColumn;

    // Buttons
    @FXML
    private Button addItemButton;
    @FXML
    private Button deleteItemButton;
    @FXML
    private Button editItemButton;

    public ItemsViewController(){
        this.itemService = new ItemService();
    }

    @FXML
    public void showAddItemWindow() {
        openWindow(
                "/org/stockfound/stockfoundinventorysoftware/views/add-item-popup.fxml",
                new AddItemViewController(this),
                "Add Item"
        );
    }

    @FXML
    public void showDeleteItemWindow(ActionEvent e) {
        openWindow(
                "/org/stockfound/stockfoundinventorysoftware/views/delete-item-popup.fxml",
                new DeleteItemViewController(this),
                "Delete Item"
        );
    }

    @FXML
    public void showEditItemWindow(ActionEvent e){
        openWindow(
                "/org/stockfound/stockfoundinventorysoftware/views/edit-item-popup.fxml",
                new EditItemViewController(this),
                "Edit Item"
        );
    }


    @FXML
    public void changeToSellersTab(ActionEvent event)
    {
        changeTab(
                "/org/stockfound/stockfoundinventorysoftware/views/sellers-view.fxml",
                new SellersViewController(),
                event
        );
    }

    public void fillTable() {
        ObservableList<Item> items = itemService.getAllItems();

        itemTableView.setItems(items);
        itemTableView.refresh();
    }

    public void filterData(){
        FilteredList<Item> filteredItems = new FilteredList<>(itemService.getAllItems(), b -> true);

        itemSearchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredItems.setPredicate(item -> {

                String lowerCaseFilter = newValue.toLowerCase();


                if (itemSearchFilters.getSelectionModel().getSelectedItem() == "SEARCH BY ID" && Integer.toString(item.getId()).equals(lowerCaseFilter)){
                    return true;
                }

                if (itemSearchFilters.getSelectionModel().getSelectedItem() == "SEARCH BY DATE" && item.getDate().toString().contains(lowerCaseFilter)){
                    return true;
                }

                if (itemSearchFilters.getSelectionModel().getSelectedItem() == "SEARCH BY SERIAL NUMBER" && item.getSerialNumber().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }

                if (itemSearchFilters.getSelectionModel().getSelectedItem() == "SEARCH BY TYPE" && item.getType().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }

                if (itemSearchFilters.getSelectionModel().getSelectedItem() == "SEARCH BY BRAND" && item.getBrand().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }

                if (itemSearchFilters.getSelectionModel().getSelectedItem() == "SEARCH BY MODEL" && item.getModel().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }

                if (itemSearchFilters.getSelectionModel().getSelectedItem() == "SEARCH BY CUSTOMER NAME" && item.getCustomerName().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }

                if (itemSearchFilters.getSelectionModel().getSelectedItem() == "SEARCH BY STATUS" && item.getStatus().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }

                if (itemSearchFilters.getSelectionModel().getSelectedItem() == "SEARCH BY PRICE" && Integer.toString(item.getPrice()).toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }

                return false;
            });

        });

        SortedList<Item> sortedItems = new SortedList<>(filteredItems);

        sortedItems.comparatorProperty().bind(itemTableView.comparatorProperty());

        itemTableView.setItems(sortedItems);

    }

    public TableView<Item> getItemTableView() {
        return itemTableView;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        ObservableList<String> searchFilters = FXCollections.observableArrayList(
            "SEARCH BY ID",
                "SEARCH BY DATE",
                "SEARCH BY SERIAL NUMBER",
                "SEARCH BY TYPE",
                "SEARCH BY BRAND",
                "SEARCH BY MODEL",
                "SEARCH BY CUSTOMER NAME",
                "SEARCH BY STATUS",
                "SEARCH BY PRICE"
        );

        insertItemsIntoChoiceBox(itemSearchFilters, searchFilters);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        serialNumberColumn.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
        customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        fillTable();
    }
}
