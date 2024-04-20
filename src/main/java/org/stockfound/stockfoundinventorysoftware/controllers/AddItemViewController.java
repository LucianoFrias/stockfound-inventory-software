package org.stockfound.stockfoundinventorysoftware.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import org.stockfound.stockfoundinventorysoftware.entities.Item;
import org.stockfound.stockfoundinventorysoftware.services.ItemService;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static org.stockfound.stockfoundinventorysoftware.utils.CustomJavaFX.closeWindow;

public class AddItemViewController implements Initializable {
    private final ItemService itemService;
    private final ItemsViewController itemsViewController;

    @FXML
    private TextField serialNumberTextField;
    @FXML
    private TextField customerNameTextField;
    @FXML
    private ChoiceBox typeChoiceBox;
    @FXML
    private TextField brandTextField;
    @FXML
    private TextField modelTextField;
    @FXML
    private ChoiceBox statusChoiceBox;
    @FXML
    private TextField priceTextField;
    @FXML
    private Button addItemButton;
    @FXML
    private Button cancelButton;

    public AddItemViewController(ItemsViewController itemsViewController){
        this.itemsViewController = itemsViewController;
        this.itemService = new ItemService();
    }

    @FXML
    public void onClickCancelButton(ActionEvent e){
        closeWindow(cancelButton.getScene().getWindow());
    }
    @FXML
    public void onClickAddItemButton() {
        Item item = new Item(
                Date.valueOf(LocalDate.now()),
                serialNumberTextField.getText(),
                customerNameTextField.getText(),
                typeChoiceBox.getSelectionModel().getSelectedItem().toString(),
                brandTextField.getText(),
                modelTextField.getText(),
                statusChoiceBox.getSelectionModel().getSelectedItem().toString(),
                Integer.parseInt(priceTextField.getText())
        );

        itemService.addItem(item);
        itemsViewController.fillTable();

        closeWindow(cancelButton.getScene().getWindow());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> statusChoices = FXCollections.observableArrayList(
                "NEW",
                "USED",
                "RECONDITIONED");

        ObservableList<String> typeChoices = FXCollections.observableArrayList(
                "KEYBOARD",
                "MOUSE",
                "HEADPHONES",
                "POWER SUPPLY",
                "UNINTERRUPTABLE POWER SUPPLY",
                "LAPTOP",
                "DIMM RAM",
                "SODIMM RAM",
                "SOLID STATE DRIVE",
                "HARD DISK DRIVE",
                "MOTHERBOARD",
                "CPU",
                "PC CASE",
                "PRE-MADE PC",
                "ROUTER",
                "PENDRIVE");

        statusChoiceBox.getItems().removeAll(statusChoiceBox.getItems());
        statusChoiceBox.getItems().addAll(statusChoices);
        statusChoiceBox.getSelectionModel().selectFirst();

        typeChoiceBox.getItems().removeAll(typeChoiceBox.getItems());
        typeChoiceBox.getItems().addAll(typeChoices);
        typeChoiceBox.getSelectionModel().selectFirst();
    }
}
