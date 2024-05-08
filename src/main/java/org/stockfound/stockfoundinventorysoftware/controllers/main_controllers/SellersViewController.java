package org.stockfound.stockfoundinventorysoftware.controllers.main_controllers;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.stockfound.stockfoundinventorysoftware.controllers.crud_controllers.crud_items.DeleteItemViewController;
import org.stockfound.stockfoundinventorysoftware.controllers.crud_controllers.crud_sellers.AddSellerViewController;
import org.stockfound.stockfoundinventorysoftware.controllers.crud_controllers.crud_sellers.DeleteSellerViewController;
import org.stockfound.stockfoundinventorysoftware.controllers.crud_controllers.crud_sellers.EditSellerViewController;
import org.stockfound.stockfoundinventorysoftware.entities.Item;
import org.stockfound.stockfoundinventorysoftware.entities.Seller;
import org.stockfound.stockfoundinventorysoftware.services.SellerService;
import org.stockfound.stockfoundinventorysoftware.utils.CustomJavaFX;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class SellersViewController implements Initializable {

    private final SellerService sellerService;

    // FXML

    @FXML
    private TableView<org.stockfound.stockfoundinventorysoftware.entities.Seller> sellerTableView;
    @FXML
    private TableColumn<org.stockfound.stockfoundinventorysoftware.entities.Seller, Integer> idColumn;
    @FXML
    private TableColumn<org.stockfound.stockfoundinventorysoftware.entities.Seller, String> customerNameColumn;
    @FXML
    private TableColumn<org.stockfound.stockfoundinventorysoftware.entities.Seller, String> customerAddressColumn;
    @FXML
    private TableColumn<org.stockfound.stockfoundinventorysoftware.entities.Seller, String> customerPhoneNumberColumn;
    @FXML
    private TextField sellerSearchBar;

    public SellersViewController() {
        this.sellerService = new SellerService();
    }

    public void filterData(){
        FilteredList<Seller> filteredSellers = new FilteredList<>(sellerService.getAllSellers(), b -> true);

        sellerSearchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredSellers.setPredicate(seller -> {

                String lowerCaseFilter = newValue.toLowerCase();

                if (Integer.toString(seller.getId()).equals(lowerCaseFilter)){
                    return true;
                }

                if (seller.getName().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }

                if (seller.getAddress().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }

                if (seller.getPhoneNumber().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }

                return false;
            });

        });

        SortedList<Seller> sortedSellers = new SortedList<>(filteredSellers);

        sortedSellers.comparatorProperty().bind(sellerTableView.comparatorProperty());

        sellerTableView.setItems(sortedSellers);

    }

    @FXML
    public void changeToItemsTab(ActionEvent event)
    {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/stockfound/stockfoundinventorysoftware/views/items-view.fxml"));
            loader.setControllerFactory(param -> new ItemsViewController());
            Parent root = loader.load();

            Stage primaryStage = (Stage)((Node) event.getSource()).getScene().getWindow();
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("Stockfound");

            primaryStage.getIcons().add(new Image(
                    Objects.requireNonNull(getClass().getResourceAsStream("/org/stockfound/stockfoundinventorysoftware/images/stockfound-logo.jpg"))));

            primaryStage.show();

        } catch (IOException ex) {
            CustomJavaFX.showErrorPopUp("Items View Error", "Items View Not Available", ex.getMessage());
            throw new RuntimeException(ex);
        }

    }

    @FXML
    public void showAddSellerPopup() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/stockfound/stockfoundinventorysoftware/views/add-seller-popup.fxml"));
        loader.setControllerFactory(param -> new AddSellerViewController(this));
        Parent root = null;

        try {
            root = loader.load();
        } catch (IOException ex) {
            CustomJavaFX.showErrorPopUp("Add Seller Popup Error", "Add Seller Popup Not Available", ex.getMessage());
            throw new RuntimeException(ex);
        }

        Stage addItemPopUpStage = new Stage();
        addItemPopUpStage.initModality(Modality.APPLICATION_MODAL);
        addItemPopUpStage.setTitle("Add Seller");
        addItemPopUpStage.getIcons().add(new Image(
                String.valueOf(getClass().getResource("/org/stockfound/stockfoundinventorysoftware/images/stockfound-logo.jpg"))));
        addItemPopUpStage.setResizable(false);
        addItemPopUpStage.setScene(new Scene(root));


        addItemPopUpStage.show();
    }

    @FXML
    public void showEditSellerPopup() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/stockfound/stockfoundinventorysoftware/views/edit-seller-popup.fxml"));
        loader.setControllerFactory(param -> new EditSellerViewController(this));
        Parent root = null;

        try {
            root = loader.load();
        } catch (IOException ex) {
            CustomJavaFX.showErrorPopUp("Edit Seller Popup Error", "Edit Seller Popup Not Available", ex.getMessage());
            throw new RuntimeException(ex);
        }

        Stage addItemPopUpStage = new Stage();
        addItemPopUpStage.initModality(Modality.APPLICATION_MODAL);
        addItemPopUpStage.setTitle("Edit Seller");
        addItemPopUpStage.getIcons().add(new Image(
                String.valueOf(getClass().getResource("/org/stockfound/stockfoundinventorysoftware/images/stockfound-logo.jpg"))));
        addItemPopUpStage.setResizable(false);
        addItemPopUpStage.setScene(new Scene(root));


        addItemPopUpStage.show();


    }

    @FXML
    public void showDeleteSellerPopup() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/stockfound/stockfoundinventorysoftware/views/delete-seller-popup.fxml"));
        loader.setControllerFactory(param -> new DeleteSellerViewController(this));
        Parent root = null;

        try {
            root = loader.load();
        } catch (IOException ex) {
            CustomJavaFX.showErrorPopUp("Delete Seller Popup Error", "Delete Seller Popup Not Available", ex.getMessage());
            throw new RuntimeException(ex);
        }

        Stage deleteItemPopupStage = new Stage();
        deleteItemPopupStage.initModality(Modality.APPLICATION_MODAL);
        deleteItemPopupStage.setTitle("Delete Seller");
        deleteItemPopupStage.getIcons().add(new Image(
                String.valueOf(getClass().getResource("/org/stockfound/stockfoundinventorysoftware/images/stockfound-logo.jpg"))));
        deleteItemPopupStage.setResizable(false);
        deleteItemPopupStage.setScene(new Scene(root));


        deleteItemPopupStage.show();
    }

    public void fillTable() {
        ObservableList<org.stockfound.stockfoundinventorysoftware.entities.Seller> sellers = sellerService.getAllSellers();

        sellerTableView.setItems(sellers);
        sellerTableView.refresh();

    }

    public TableView<org.stockfound.stockfoundinventorysoftware.entities.Seller> getSellerTableView() {
        return sellerTableView;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        customerAddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        customerPhoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        fillTable();

    }
}
