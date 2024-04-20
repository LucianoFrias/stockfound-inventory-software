package org.stockfound.stockfoundinventorysoftware.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.stockfound.stockfoundinventorysoftware.entities.Customer;
import org.stockfound.stockfoundinventorysoftware.entities.Item;
import org.stockfound.stockfoundinventorysoftware.services.CustomerService;
import org.stockfound.stockfoundinventorysoftware.utils.CustomJavaFX;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class CustomersViewController implements Initializable {

    private final CustomerService customerService;

    // FXML

    @FXML
    private TableView<Customer> customerTableView;
    @FXML
    private TableColumn<Customer, Integer> idColumn;
    @FXML
    private TableColumn<Customer, String> customerNameColumn;
    @FXML
    private TableColumn<Customer, String> customerAddressColumn;
    @FXML
    private TableColumn<Customer, String> customerPhoneNumberColumn;

    public CustomersViewController() {
        this.customerService = new CustomerService();
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
            primaryStage.setTitle("Items");

            primaryStage.getIcons().add(new Image(
                    Objects.requireNonNull(getClass().getResourceAsStream("/org/stockfound/stockfoundinventorysoftware/images/stockfound-logo.jpg"))));

            primaryStage.show();

        } catch (IOException ex) {
            CustomJavaFX.showErrorPopUp("Items View Error", "Items View Not Available", ex.getMessage());
            throw new RuntimeException(ex);
        }

    }

    public void fillTable() {
        ObservableList<Customer> customers = customerService.getAllCustomers();

        customerTableView.setItems(customers);
        customerTableView.refresh();

    }

    public TableView<Customer> getItemTableView() {
        return customerTableView;
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
