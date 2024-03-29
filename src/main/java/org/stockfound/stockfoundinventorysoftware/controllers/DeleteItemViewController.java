package org.stockfound.stockfoundinventorysoftware.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import org.stockfound.stockfoundinventorysoftware.entities.Item;
import org.stockfound.stockfoundinventorysoftware.services.ItemService;

import java.net.URL;
import java.util.ResourceBundle;

import static org.stockfound.stockfoundinventorysoftware.utils.CustomJavaFX.closeWindow;

public class DeleteItemViewController implements Initializable {

    private final ItemService itemService;
    private final MainViewController mainViewController;

    @FXML
    private Label serialNumberLabel;
    @FXML
    private Label idLabel;
    @FXML
    private Button cancelButton;
    @FXML
    private Button deleteItemButton;

    public DeleteItemViewController(MainViewController mainViewController) {
        this.mainViewController = mainViewController;
        this.itemService = new ItemService();
    }

    @FXML
    public void onClickCancelButton(ActionEvent e){
        closeWindow(cancelButton.getScene().getWindow());
    }

    @FXML
    public void onClickDeleteItem(ActionEvent e){
        Item itemToDelete = mainViewController.getItemTableView().getSelectionModel().getSelectedItem();

        itemService.deleteItem(itemToDelete.getId());
        mainViewController.fillTable();
        closeWindow(cancelButton.getScene().getWindow());
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Item item = mainViewController.getItemTableView().getSelectionModel().getSelectedItem();
        serialNumberLabel.setText("Delete Item " + item.getSerialNumber() + "?");
        idLabel.setText("ID: " + item.getId());
    }
}
