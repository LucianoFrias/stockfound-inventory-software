package org.stockfound.stockfoundinventorysoftware.controllers.main_controllers;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.stockfound.stockfoundinventorysoftware.controllers.crud_controllers.crud_sellers.AddSellerViewController;
import org.stockfound.stockfoundinventorysoftware.controllers.crud_controllers.crud_sellers.DeleteSellerViewController;
import org.stockfound.stockfoundinventorysoftware.controllers.crud_controllers.crud_sellers.EditSellerViewController;
import org.stockfound.stockfoundinventorysoftware.entities.Seller;
import org.stockfound.stockfoundinventorysoftware.services.SellerService;
import static org.stockfound.stockfoundinventorysoftware.utils.CustomJavaFX.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SellersViewController implements Initializable {

    private final SellerService sellerService;

    // FXML

    @FXML
    private TableView<Seller> sellerTableView;
    @FXML
    private TableColumn<Seller, Integer> idColumn;
    @FXML
    private TableColumn<Seller, String> customerNameColumn;
    @FXML
    private TableColumn<Seller, String> customerAddressColumn;
    @FXML
    private TableColumn<Seller, String> customerPhoneNumberColumn;
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
        changeTab(
                "/org/stockfound/stockfoundinventorysoftware/views/items-view.fxml",
                new ItemsViewController(),
                event
        );

    }

    @FXML
    public void showAddSellerPopup() {
        openWindow(
                "/org/stockfound/stockfoundinventorysoftware/views/add-seller-popup.fxml",
                new AddSellerViewController(this),
                "Add Seller"
        );
    }

    @FXML
    public void showEditSellerPopup() {
       openWindow(
               "/org/stockfound/stockfoundinventorysoftware/views/edit-seller-popup.fxml",
               new EditSellerViewController(this),
               "Edit Seller"
       );
    }

    @FXML
    public void showDeleteSellerPopup() {
        openWindow(
                "/org/stockfound/stockfoundinventorysoftware/views/delete-seller-popup.fxml",
                new DeleteSellerViewController(this),
                "Delete Seller"
        );
    }

    public void fillTable() {
        ObservableList<Seller> sellers = sellerService.getAllSellers();

        sellerTableView.setItems(sellers);
        sellerTableView.refresh();

    }

    public TableView<Seller> getSellerTableView() {
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
