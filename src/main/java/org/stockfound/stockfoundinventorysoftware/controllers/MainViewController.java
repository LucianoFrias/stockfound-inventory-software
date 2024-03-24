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
import org.stockfound.stockfoundinventorysoftware.database.Database;
import org.stockfound.stockfoundinventorysoftware.database.ItemRepository;
import org.stockfound.stockfoundinventorysoftware.database.PostgreSQLDatabase;
import org.stockfound.stockfoundinventorysoftware.entities.Item;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {

    // Classes
    private final ItemRepository itemRepository;

    // FXML
    @FXML
    private TableView<Item> itemTableView;

    // Columns
    @FXML
    private TableColumn<Item, String> dateColumn;
    @FXML
    private TableColumn<Item, String> serialNumberColumn;
    @FXML
    private TableColumn<Item, String> customerNameColumn;
    @FXML
    private TableColumn<Item, String> statusColumn;
    @FXML
    private TableColumn<Item, Boolean> invoiceColumn;
    @FXML
    private TableColumn<Item, Boolean> packedColumn;
    @FXML
    private TableColumn<Item, Boolean> shippedColumn;
    @FXML
    private TableColumn<Item, Integer> amountColumn;

    // Buttons
    @FXML
    private Button addItemButton;
    @FXML
    private Button deleteItemButton;
    @FXML
    private Button editItemButton;

    public MainViewController(){
        Database database = new PostgreSQLDatabase("localhost", "5432", "stockfound_database", "postgres", "120708LUciano");
        this.itemRepository = new ItemRepository(database);
    }

    @FXML
    public void showAddItemPopUp(ActionEvent e) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/stockfound/stockfoundinventorysoftware/add-item-popup.fxml"));
        loader.setControllerFactory(param -> new CrudButtonsController(this));
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
    public void deleteItem(ActionEvent e) {
        Item item = itemTableView.getSelectionModel().getSelectedItem();

        itemRepository.deleteItem(item.getSerialNumber());

        fillTable();
    }

    public void fillTable() {
        ObservableList<Item> items = itemRepository.getAllItems();

        itemTableView.setItems(items);
        itemTableView.refresh();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        serialNumberColumn.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
        customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        invoiceColumn.setCellValueFactory(new PropertyValueFactory<>("invoice"));
        packedColumn.setCellValueFactory(new PropertyValueFactory<>("packed"));
        shippedColumn.setCellValueFactory(new PropertyValueFactory<>("shipped"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));

        fillTable();
    }
}
