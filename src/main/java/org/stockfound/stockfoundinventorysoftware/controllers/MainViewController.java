package org.stockfound.stockfoundinventorysoftware.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.stockfound.stockfoundinventorysoftware.entities.Item;
import org.stockfound.stockfoundinventorysoftware.services.ItemService;
import org.stockfound.stockfoundinventorysoftware.utils.CustomJavaFX;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {

    // Classes
    private final ItemService itemService;

    // FXML
    @FXML
    private TextField itemSearchBar;
    @FXML
    private ChoiceBox itemSearchFilters;
    @FXML
    private TableView<Item> itemTableView;

    // Columns
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

    public MainViewController(){
        this.itemService = new ItemService();
    }

    @FXML
    public void showAddItemPopUp() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/stockfound/stockfoundinventorysoftware/views/add-item-popup.fxml"));
        loader.setControllerFactory(param -> new AddItemViewController(this));
        Parent root = null;

        try {
            root = loader.load();
        } catch (IOException ex) {
            CustomJavaFX.showErrorPopUp("Add Item Popup Error", "Add Item Popup Not Available", ex.getMessage());
            throw new RuntimeException(ex);
        }

        Stage addItemPopUpStage = new Stage();
        addItemPopUpStage.initModality(Modality.APPLICATION_MODAL);
        addItemPopUpStage.setTitle("Add Item");
        addItemPopUpStage.getIcons().add(new Image(
                String.valueOf(getClass().getResource("/org/stockfound/stockfoundinventorysoftware/images/stockfound-logo.jpg"))));
        addItemPopUpStage.setResizable(false);
        addItemPopUpStage.setScene(new Scene(root));


        addItemPopUpStage.show();


    }

    @FXML
    public void showDeleteItemPopup(ActionEvent e) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/stockfound/stockfoundinventorysoftware/views/delete-item-popup.fxml"));
        loader.setControllerFactory(param -> new DeleteItemViewController(this));
        Parent root = null;

        try {
            root = loader.load();
        } catch (IOException ex) {
            CustomJavaFX.showErrorPopUp("Delete Item Popup Error", "Delete Item Popup Not Available", ex.getMessage());
            throw new RuntimeException(ex);
        }

        Stage deleteItemPopupStage = new Stage();
        deleteItemPopupStage.initModality(Modality.APPLICATION_MODAL);
        deleteItemPopupStage.setTitle("Delete Item");
        deleteItemPopupStage.getIcons().add(new Image(
                String.valueOf(getClass().getResource("/org/stockfound/stockfoundinventorysoftware/images/stockfound-logo.jpg"))));
        deleteItemPopupStage.setResizable(false);
        deleteItemPopupStage.setScene(new Scene(root));


        deleteItemPopupStage.show();
    }

    @FXML
    public void showEditItemPopup(ActionEvent e){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/stockfound/stockfoundinventorysoftware/views/edit-item-popup.fxml"));
        loader.setControllerFactory(param -> new EditItemViewController(this));
        Parent root = null;

        try {
            root = loader.load();
        } catch (IOException ex) {
            CustomJavaFX.showErrorPopUp("Edit Item Popup Error", "Edit Item Popup Not Available", ex.getMessage());
            throw new RuntimeException(ex);
        }

        Stage editItemPopupStage = new Stage();
        editItemPopupStage.initModality(Modality.APPLICATION_MODAL);
        editItemPopupStage.setTitle("Edit Item");
        editItemPopupStage.getIcons().add(new Image(
                String.valueOf(getClass().getResource("/org/stockfound/stockfoundinventorysoftware/images/stockfound-logo.jpg"))));
        editItemPopupStage.setScene(new Scene(root));
        editItemPopupStage.setResizable(false);


        editItemPopupStage.show();
    }

    public void fillTable() {
        ObservableList<Item> items = itemService.getAllItems();

        itemTableView.setItems(items);
        itemTableView.refresh();

    }

    public void filterData(){
        FilteredList<Item> filteredItems = new FilteredList<Item>(itemService.getAllItems(), b -> true);

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

        itemSearchFilters.getItems().removeAll(itemSearchFilters.getItems());
        itemSearchFilters.getItems().addAll(searchFilters);
        itemSearchFilters.getSelectionModel().selectFirst();

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
