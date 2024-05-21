package org.stockfound.stockfoundinventorysoftware.controllers.crud_controllers.crud_sellers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.stockfound.stockfoundinventorysoftware.controllers.main_controllers.SellersViewController;
import org.stockfound.stockfoundinventorysoftware.entities.Item;
import org.stockfound.stockfoundinventorysoftware.entities.Seller;
import org.stockfound.stockfoundinventorysoftware.services.SellerService;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static org.stockfound.stockfoundinventorysoftware.utils.CustomJavaFX.closeWindow;

public class AddSellerViewController implements Initializable {

    private final SellersViewController sellersViewController;
    private final SellerService sellerService;

    @FXML
    private TextField nameTextField;
    @FXML
    private TextField addressTextField;
    @FXML
    private TextField phoneNumberTextField;
    @FXML
    private Button addSellerButton;
    @FXML
    private Button cancelButton;

    public AddSellerViewController(SellersViewController sellersViewController) {
        this.sellerService = new SellerService();
        this.sellersViewController = sellersViewController;
    }

    @FXML
    public void onClickCancelButton(ActionEvent e){
        closeWindow(cancelButton.getScene().getWindow());
    }

    @FXML
    public void onClickAddSellerButton() {
        Seller seller = new Seller(
                nameTextField.getText(),
                addressTextField.getText(),
                phoneNumberTextField.getText()
        );

        sellerService.addSeller(seller);
        sellersViewController.fillTable();

        closeWindow(cancelButton.getScene().getWindow());
    }

    @FXML
    public void onClickEditSellerButton() {
        Seller seller = new Seller(
                nameTextField.getText(),
                addressTextField.getText(),
                phoneNumberTextField.getText()
        );

        sellerService.addSeller(seller);
        sellersViewController.fillTable();

        closeWindow(cancelButton.getScene().getWindow());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
