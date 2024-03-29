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

            String sqlQuery = "insert into items.item (date, serial_number, customer_name, type, brand, model, status, price) " + "VALUES (?,?,?,?,?,?,?,?);";

            try {
                PreparedStatement statement = connection.prepareStatement(sqlQuery, Statement.NO_GENERATED_KEYS);
                statement.setDate(1, item.getDate());
                statement.setString(2, item.getSerialNumber());
                statement.setString(3, item.getCustomerName());
                statement.setString(4, item.getType());
                statement.setString(5, item.getBrand());
                statement.setString(6, item.getModel());
                statement.setString(7, item.getStatus());
                statement.setInt(8, item.getPrice());

                statement.executeUpdate();

                connection.close();
            } catch (SQLException e){
                throw new RuntimeException(e);
            }



    }

    public void deleteItem(int id) {
        Connection connection = database.connect();

        String sqlQuery = "delete from items.item where id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery, Statement.NO_GENERATED_KEYS);
            statement.setInt(1, id);

            statement.executeUpdate();

            connection.close();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }

    }


    public void updateItem(Item item) {
        Connection connection = database.connect();

        String sqlQuery = "UPDATE items.item SET id=?, serial_number=?,customer_name=?,type=?,brand=?,model=?,status=?,price=? WHERE id=?";

        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery, Statement.NO_GENERATED_KEYS);
            statement.setInt(1, item.getId());
            statement.setString(2, item.getSerialNumber());
            statement.setString(3, item.getCustomerName());
            statement.setString(4, item.getType());
            statement.setString(5, item.getBrand());
            statement.setString(6, item.getModel());
            statement.setString(7, item.getStatus());
            statement.setInt(8, item.getPrice());
            statement.setInt(9, item.getId());

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
                    rs.getInt("id"),
                    rs.getDate("date"),
                    rs.getString("serial_number"),
                    rs.getString("customer_name"),
                    rs.getString("type"),
                    rs.getString("brand"),
                    rs.getString("model"),
                    rs.getString("status"),
                    rs.getInt("price")
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
