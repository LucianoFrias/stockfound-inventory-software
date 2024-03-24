package org.stockfound.stockfoundinventorysoftware.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.stockfound.stockfoundinventorysoftware.database.Database;
import org.stockfound.stockfoundinventorysoftware.database.ItemRepository;
import org.stockfound.stockfoundinventorysoftware.database.PostgreSQLDatabase;
import org.stockfound.stockfoundinventorysoftware.entities.Item;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class CrudButtonsController {
    private final ItemRepository itemRepository;
    private final MainViewController mainViewController;

    @FXML
    private TextField serialNumberTextField;
    @FXML
    private TextField customerNameTextField;
    @FXML
    private TextField statusTextField;
    @FXML
    private CheckBox invoiceCheckBox;
    @FXML
    private CheckBox packedCheckBox;
    @FXML
    private CheckBox shippedCheckBox;
    @FXML
    private TextField amountTextField;
    @FXML
    private Button addItemButton;
    @FXML
    private Button cancelButton;

    public CrudButtonsController(MainViewController mainViewController){
        this.mainViewController = mainViewController;
        Database database = new PostgreSQLDatabase("localhost", "5432", "stockfound_database", "postgres", "120708LUciano");
        this.itemRepository = new ItemRepository(database);
    }

    @FXML
    public void onClickCancelButton(ActionEvent e){
        closeWindow();
    }
    @FXML
    public void onClickAddItemButton() {
        Item item = new Item(
                Date.valueOf(LocalDate.now()),
                serialNumberTextField.getText(),
                customerNameTextField.getText(),
                statusTextField.getText(),
                invoiceCheckBox.isSelected(),
                packedCheckBox.isSelected(),
                shippedCheckBox.isSelected(),
                Integer.parseInt(amountTextField.getText())
        );

        itemRepository.addItem(item);
        mainViewController.fillTable();

        closeWindow();
    }

    private void closeWindow(){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
