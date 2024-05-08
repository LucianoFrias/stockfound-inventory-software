package org.stockfound.stockfoundinventorysoftware.database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.stockfound.stockfoundinventorysoftware.entities.Seller;
import org.stockfound.stockfoundinventorysoftware.utils.CustomJavaFX;

import java.sql.*;

public class SellerRepository {

    private final Database database;
    private final ObservableList<Seller> sellers;

    public SellerRepository(Database database) {
        this.database = database;
        this.sellers = FXCollections.observableArrayList();
    }

    public void addSeller(Seller seller) {
        Connection connection = database.connect();

        String sqlQuery = "insert into items.seller (name, address, phone_number) " + "VALUES (?,?,?);";

        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery, Statement.NO_GENERATED_KEYS);
            statement.setString(1, seller.getName());
            statement.setString(2, seller.getAddress());
            statement.setString(3, seller.getPhoneNumber());

            statement.executeUpdate();

            connection.close();
        } catch (SQLException e){
            CustomJavaFX.showErrorPopUp("Add Seller Error", "Can't add a seller to database", e.getMessage());
            throw new RuntimeException(e);
        }



    }

    public void deleteSeller(int id) {
        Connection connection = database.connect();

        String sqlQuery = "delete from items.seller where id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery, Statement.NO_GENERATED_KEYS);
            statement.setInt(1, id);

            statement.executeUpdate();

            connection.close();
        } catch (SQLException e){
            CustomJavaFX.showErrorPopUp("Delete Seller Error", "Can't delete a seller from database", e.getMessage());
            throw new RuntimeException(e);
        }

    }


    public void updateSeller(Seller seller) {
        Connection connection = database.connect();

        String sqlQuery = "UPDATE items.seller SET name=?,address=?,phone_number=? WHERE id=?";

        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery, Statement.NO_GENERATED_KEYS);
            statement.setString(1, seller.getName());
            statement.setString(2, seller.getAddress());
            statement.setString(3, seller.getPhoneNumber());
            statement.setInt(4, seller.getId());

            statement.executeUpdate();
            connection.close();

        } catch (SQLException e){
            CustomJavaFX.showErrorPopUp("Update Seller Error", "Can't update a Seller from database", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public ObservableList<Seller> getAllSellers() {
        sellers.clear();

        Connection connection = database.connect();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from items.seller");
            while (rs.next())
            {
                Seller seller = new Seller(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("phone_number")
                );
                sellers.add(seller);
            }

            connection.close();

        }
        catch (SQLException e){
            CustomJavaFX.showErrorPopUp("Get Sellers Error", "Can't get sellers from database", e.getMessage());
            throw new RuntimeException(e);
        }

        return sellers;
    }
}
