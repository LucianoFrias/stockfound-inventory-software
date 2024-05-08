package org.stockfound.stockfoundinventorysoftware.controllers.crud_controllers.crud_sellers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.stockfound.stockfoundinventorysoftware.controllers.main_controllers.ItemsViewController;
import org.stockfound.stockfoundinventorysoftware.controllers.main_controllers.SellersViewController;
import org.stockfound.stockfoundinventorysoftware.entities.Item;
import org.stockfound.stockfoundinventorysoftware.entities.Seller;
import org.stockfound.stockfoundinventorysoftware.services.ItemService;
import org.stockfound.stockfoundinventorysoftware.services.SellerService;

import java.net.URL;
import java.util.ResourceBundle;

import static org.stockfound.stockfoundinventorysoftware.utils.CustomJavaFX.closeWindow;

public class DeleteSellerViewController implements Initializable {

    private final SellerService sellerService;
    private final SellersViewController sellersViewController;

    @FXML
    private Label nameLabel;
    @FXML
    private Label idLabel;
    @FXML
    private Button cancelButton;
    @FXML
    private Button deleteItemButton;

    public DeleteSellerViewController(SellersViewController sellersViewController){
        this.sellersViewController = sellersViewController;
        this.sellerService = new SellerService();
    }

    @FXML
    public void onClickCancelButton(ActionEvent e){
        closeWindow(cancelButton.getScene().getWindow());
    }

    @FXML
    public void onClickDeleteSeller(ActionEvent e){
        Seller sellerToDelete = sellersViewController.getSellerTableView().getSelectionModel().getSelectedItem();

        sellerService.deleteSeller(sellerToDelete.getId());
        sellersViewController.fillTable();
        closeWindow(cancelButton.getScene().getWindow());
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Seller seller = sellersViewController.getSellerTableView().getSelectionModel().getSelectedItem();
        nameLabel.setText("Delete Seller " + seller.getName() + "?");
        idLabel.setText("ID: " + seller.getId());
    }
}
