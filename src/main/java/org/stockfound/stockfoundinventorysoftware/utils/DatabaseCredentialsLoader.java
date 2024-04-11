package org.stockfound.stockfoundinventorysoftware.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class DatabaseCredentialsLoader {

    public static DatabaseCredentialRecord loadCredentials(){

        String path = System.getProperty("user.home") + File.separator + ".stockfound" + File.separator + "stockfound-properties.config";
        Properties prop = new Properties();

        try (FileInputStream fis = new FileInputStream(path)) {
            prop.load(fis);
        } catch (FileNotFoundException ex) {

        } catch (IOException ignored) {
        }

        DatabaseCredentialRecord databaseCredentials = new DatabaseCredentialRecord(
                prop.getProperty("HOST"),
                prop.getProperty("PORT"),
                prop.getProperty("DATABASE"),
                prop.getProperty("USERNAME"),
                prop.getProperty("PASSWORD")
        );

        return databaseCredentials;
    }

}
