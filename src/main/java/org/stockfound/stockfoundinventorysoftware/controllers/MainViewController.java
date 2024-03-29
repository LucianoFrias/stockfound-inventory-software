package org.stockfound.stockfoundinventorysoftware.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.stockfound.stockfoundinventorysoftware.entities.Item;
import org.stockfound.stockfoundinventorysoftware.services.ItemService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {

    // Classes
    private final ItemService itemService;

    // FXML
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
    public void showAddItemPopUp(ActionEvent e) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/stockfound/stockfoundinventorysoftware/add-item-popup.fxml"));
        loader.setControllerFactory(param -> new AddItemViewController(this));
        Parent root = null;

        try {
            root = loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        Stage addItemPopUpStage = new Stage();
        addItemPopUpStage.initModality(Modality.APPLICATION_MODAL);
        addItemPopUpStage.setTitle("Add Item");
        addItemPopUpStage.setScene(new Scene(root));


        addItemPopUpStage.show();


    }

    @FXML
    public void showDeleteItemPopup(ActionEvent e) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/stockfound/stockfoundinventorysoftware/delete-item-popup.fxml"));
        loader.setControllerFactory(param -> new DeleteItemViewController(this));
        Parent root = null;

        try {
            root = loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        Stage deleteItemPopupStage = new Stage();
        deleteItemPopupStage.initModality(Modality.APPLICATION_MODAL);
        deleteItemPopupStage.setTitle("Delete Item");
        deleteItemPopupStage.setScene(new Scene(root));


        deleteItemPopupStage.show();
    }

    @FXML
    public void showEditItemPopup(ActionEvent e){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/stockfound/stockfoundinventorysoftware/edit-item-popup.fxml"));
        loader.setControllerFactory(param -> new EditItemViewController(this));
        Parent root = null;

        try {
            root = loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        Stage editItemPopupStage = new Stage();
        editItemPopupStage.initModality(Modality.APPLICATION_MODAL);
        editItemPopupStage.setTitle("Edit Item");
        editItemPopupStage.setScene(new Scene(root));


        editItemPopupStage.show();
    }

    public void fillTable() {
        ObservableList<Item> items = itemService.getAllItems();

        itemTableView.setItems(items);
        itemTableView.refresh();
    }

    public TableView<Item> getItemTableView() {
        return itemTableView;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
