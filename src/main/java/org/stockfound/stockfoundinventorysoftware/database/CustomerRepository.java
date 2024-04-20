package org.stockfound.stockfoundinventorysoftware.database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.stockfound.stockfoundinventorysoftware.entities.Customer;
import org.stockfound.stockfoundinventorysoftware.utils.CustomJavaFX;

import java.sql.*;

public class CustomerRepository {

    private final Database database;
    private final ObservableList<Customer> customers;

    public CustomerRepository(Database database) {
        this.database = database;
        this.customers = FXCollections.observableArrayList();
    }

    public void addCustomer(Customer customer) {
        Connection connection = database.connect();

        String sqlQuery = "insert into items.customer (name, address, phone_number) " + "VALUES (?,?,?);";

        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery, Statement.NO_GENERATED_KEYS);
            statement.setString(1, customer.getName());
            statement.setString(2, customer.getAddress());
            statement.setString(3, customer.getPhoneNumber());

            statement.executeUpdate();

            connection.close();
        } catch (SQLException e){
            CustomJavaFX.showErrorPopUp("Add Customer Error", "Can't add an customer to database", e.getMessage());
            throw new RuntimeException(e);
        }



    }

    public void deleteCustomer(int id) {
        Connection connection = database.connect();

        String sqlQuery = "delete from items.customer where id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery, Statement.NO_GENERATED_KEYS);
            statement.setInt(1, id);

            statement.executeUpdate();

            connection.close();
        } catch (SQLException e){
            CustomJavaFX.showErrorPopUp("Delete Customer Error", "Can't delete an customer from database", e.getMessage());
            throw new RuntimeException(e);
        }

    }


    public void updateCustomer(Customer customer) {
        Connection connection = database.connect();

        String sqlQuery = "UPDATE items.customer SET id=?, name=?,address=?,phone_number=? WHERE id=?";

        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery, Statement.NO_GENERATED_KEYS);
            statement.setInt(1, customer.getId());
            statement.setString(2, customer.getName());
            statement.setString(3, customer.getAddress());
            statement.setString(4, customer.getPhoneNumber());
            statement.setInt(5, customer.getId());

            statement.executeUpdate();
            connection.close();

        } catch (SQLException e){
            CustomJavaFX.showErrorPopUp("Update Customer Error", "Can't update a customer from database", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public ObservableList<Customer> getAllCustomers() {
        customers.clear();

        Connection connection = database.connect();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from items.customer");
            while (rs.next())
            {
                Customer customer = new Customer(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("phone_number")
                );
                customers.add(customer);
            }

            connection.close();

        }
        catch (SQLException e){
            CustomJavaFX.showErrorPopUp("Get Customers Error", "Can't get customers from database", e.getMessage());
            throw new RuntimeException(e);
        }

        return customers;
    }
}
