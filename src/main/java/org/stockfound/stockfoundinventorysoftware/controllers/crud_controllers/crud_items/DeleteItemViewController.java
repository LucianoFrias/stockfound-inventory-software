package org.stockfound.stockfoundinventorysoftware.controllers.crud_controllers.crud_items;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.stockfound.stockfoundinventorysoftware.controllers.main_controllers.ItemsViewController;
import org.stockfound.stockfoundinventorysoftware.entities.Item;
import org.stockfound.stockfoundinventorysoftware.services.ItemService;

import java.net.URL;
import java.util.ResourceBundle;

import static org.stockfound.stockfoundinventorysoftware.utils.CustomJavaFX.closeWindow;

public class DeleteItemViewController implements Initializable {

    private final ItemService itemService;
    private final ItemsViewController itemsViewController;

    @FXML
    private Label serialNumberLabel;
    @FXML
    private Label idLabel;
    @FXML
    private Button cancelButton;
    @FXML
    private Button deleteItemButton;

    public DeleteItemViewController(ItemsViewController itemsViewController) {
        this.itemsViewController = itemsViewController;
        this.itemService = new ItemService();
    }

    @FXML
    public void onClickCancelButton(ActionEvent e){
        closeWindow(cancelButton.getScene().getWindow());
    }

    @FXML
    public void onClickDeleteItem(ActionEvent e){
        Item itemToDelete = itemsViewController.getItemTableView().getSelectionModel().getSelectedItem();

        itemService.deleteItem(itemToDelete.getId());
        itemsViewController.fillTable();
        closeWindow(cancelButton.getScene().getWindow());
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Item item = itemsViewController.getItemTableView().getSelectionModel().getSelectedItem();
        serialNumberLabel.setText("Delete Item " + item.getSerialNumber() + "?");
        idLabel.setText("ID: " + item.getId());
    }
}
