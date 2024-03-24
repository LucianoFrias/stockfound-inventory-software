package org.stockfound.stockfoundinventorysoftware.database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.stockfound.stockfoundinventorysoftware.entities.Item;

import java.sql.*;


public class ItemRepository {

    private final Database database;
    private final ObservableList<Item> items;

    public ItemRepository(Database database) {
        this.database = database;
        this.items = FXCollections.observableArrayList();
    }

    public void addItem(Item item) {
        Connection connection = database.connect();

            String sqlQuery = "insert into items.item (date, serial_number, customer_name, status, invoice, packed, shipped, amount) " + "VALUES (?,?,?,?,?,?,?,?);";

            try {
                PreparedStatement statement = connection.prepareStatement(sqlQuery, Statement.NO_GENERATED_KEYS);
                statement.setDate(1, item.getDate());
                statement.setString(2, item.getSerialNumber());
                statement.setString(3, item.getCustomerName());
                statement.setString(4, item.getStatus());
                statement.setBoolean(5, item.isInvoice());
                statement.setBoolean(6, item.isPacked());
                statement.setBoolean(7, item.isShipped());
                statement.setInt(8, item.getAmount());

                statement.executeUpdate();

                connection.close();
            } catch (SQLException e){
                throw new RuntimeException(e);
            }



    }

    public void deleteItem(String serialNumber) {
        Connection connection = database.connect();

        String sqlQuery = "delete from items.item where serial_number = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery, Statement.NO_GENERATED_KEYS);
            statement.setString(1, serialNumber);

            statement.executeUpdate();

            connection.close();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    public ObservableList<Item> getAllItems() {
        items.clear();

        Connection connection = database.connect();

        try {
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from items.item");
        while (rs.next())
        {
            Item item = new Item(
                    rs.getDate("date"),
                    rs.getString("serial_number"),
                    rs.getString("customer_name"),
                    rs.getString("status"),
                    rs.getBoolean("invoice"),
                    rs.getBoolean("packed"),
                    rs.getBoolean("shipped"),
                    rs.getInt("amount")
            );
            items.add(item);
        }

        connection.close();

        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }

        return items;
    }


}
