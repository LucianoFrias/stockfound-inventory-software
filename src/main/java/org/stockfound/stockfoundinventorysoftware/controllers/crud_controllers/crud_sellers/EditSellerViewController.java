package org.stockfound.stockfoundinventorysoftware.controllers.crud_controllers.crud_sellers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.stockfound.stockfoundinventorysoftware.controllers.main_controllers.SellersViewController;
import org.stockfound.stockfoundinventorysoftware.entities.Seller;
import org.stockfound.stockfoundinventorysoftware.services.SellerService;

import java.net.URL;
import java.util.ResourceBundle;

import static org.stockfound.stockfoundinventorysoftware.utils.CustomJavaFX.closeWindow;

public class EditSellerViewController implements Initializable {

    private final SellersViewController sellersViewController;
    private final SellerService sellerService;

    @FXML
    private Label idLabel;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField addressTextField;
    @FXML
    private TextField phoneNumberTextField;
    @FXML
    private Button cancelButton;

    public EditSellerViewController(SellersViewController sellersViewController) {
        this.sellersViewController = sellersViewController;
        this.sellerService = new SellerService();
    }

    @FXML
    public void onClickCancelButton(ActionEvent e){
        closeWindow(cancelButton.getScene().getWindow());
    }

    @FXML
    public void onClickEditSellerButton() {

        Seller seller = new Seller(
                Integer.parseInt(idLabel.getText()),
                nameTextField.getText(),
                addressTextField.getText(),
                phoneNumberTextField.getText()
        );

        sellerService.updateSeller(seller);
        sellersViewController.fillTable();

        closeWindow(cancelButton.getScene().getWindow());
    }

    public void populateTextFields(){
        Seller seller = sellersViewController.getSellerTableView().getSelectionModel().getSelectedItem();

        idLabel.setText(Integer.toString(seller.getId()));
        nameTextField.setText(seller.getName());
        addressTextField.setText(seller.getAddress());
        phoneNumberTextField.setText(seller.getPhoneNumber());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateTextFields();
    }
}
