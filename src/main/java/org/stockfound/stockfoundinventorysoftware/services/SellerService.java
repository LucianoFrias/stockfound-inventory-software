package org.stockfound.stockfoundinventorysoftware.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.stockfound.stockfoundinventorysoftware.database.SellerRepository;
import org.stockfound.stockfoundinventorysoftware.database.Database;
import org.stockfound.stockfoundinventorysoftware.database.PostgreSQLDatabase;
import org.stockfound.stockfoundinventorysoftware.entities.Seller;
import org.stockfound.stockfoundinventorysoftware.utils.DatabaseCredentialsLoader;

public class SellerService {

    private final SellerRepository sellerRepository;

    public SellerService() {
        Database database = new PostgreSQLDatabase(
                DatabaseCredentialsLoader.loadCredentials().host(),
                DatabaseCredentialsLoader.loadCredentials().port(),
                DatabaseCredentialsLoader.loadCredentials().database(),
                DatabaseCredentialsLoader.loadCredentials().username(),
                DatabaseCredentialsLoader.loadCredentials().password());

        this.sellerRepository = new SellerRepository(database);
    }


    public ObservableList<Seller> getAllSellers(){
        return sellerRepository.getAllSellers();
    }

    public ObservableList<String> getAllSellersNames(){
        ObservableList<String> customerNames = FXCollections.observableArrayList();

        for (Seller seller : getAllSellers())
        {
            customerNames.add(seller.getName());
        }

        return customerNames;
    }




    public void deleteSeller(int id){

        sellerRepository.deleteSeller(id);
    }

    public void addSeller(Seller seller){
        sellerRepository.addSeller(seller);
    }


    public void updateSeller(Seller seller) {
        sellerRepository.updateSeller(seller);
    }

}
