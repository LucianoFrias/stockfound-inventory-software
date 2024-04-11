package org.stockfound.stockfoundinventorysoftware.database;

import javafx.scene.control.Alert;
import javafx.stage.Modality;
import org.stockfound.stockfoundinventorysoftware.utils.CustomJavaFX;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSQLDatabase implements Database{

    private String host;
    private String port;
    private String database;
    private String username;
    private String password;

    public PostgreSQLDatabase(String host, String port, String database, String username, String password) {
        this.host = host;
        this.port = port;
        this.database = database;
        this.username = username;
        this.password = password;
    }

    @Override
    public Connection connect() {
        String databaseUrl = "jdbc:postgresql://" + host + ":" + port + "/" + database;
        Connection connection;

        try {
            connection = DriverManager.getConnection(databaseUrl, username, password);
        } catch (SQLException | NullPointerException e) {
            CustomJavaFX.showErrorPopUp(
                    "Database Error",
                    "Database Not Found",
                    e.getMessage());

            throw new RuntimeException(e);
        }

        return connection;
    }

}
