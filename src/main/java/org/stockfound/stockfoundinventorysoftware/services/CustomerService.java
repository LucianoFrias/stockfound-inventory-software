package org.stockfound.stockfoundinventorysoftware.services;

import javafx.collections.ObservableList;
import org.stockfound.stockfoundinventorysoftware.entities.Customer;
import org.stockfound.stockfoundinventorysoftware.database.CustomerRepository;
import org.stockfound.stockfoundinventorysoftware.database.Database;
import org.stockfound.stockfoundinventorysoftware.database.PostgreSQLDatabase;
import org.stockfound.stockfoundinventorysoftware.utils.DatabaseCredentialsLoader;

public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService() {
        Database database = new PostgreSQLDatabase(
                DatabaseCredentialsLoader.loadCredentials().host(),
                DatabaseCredentialsLoader.loadCredentials().port(),
                DatabaseCredentialsLoader.loadCredentials().database(),
                DatabaseCredentialsLoader.loadCredentials().username(),
                DatabaseCredentialsLoader.loadCredentials().password());

        this.customerRepository = new CustomerRepository(database);
    }

    public ObservableList<Customer> getAllCustomers(){
        return customerRepository.getAllCustomers();
    }

    public void deleteCustomer(int id){

        customerRepository.deleteCustomer(id);
    }

    public void addCustomer(Customer customer){
        customerRepository.addCustomer(customer);
    }


    public void updateCustomer(Customer customer) {
        customerRepository.updateCustomer(customer);
    }

}
