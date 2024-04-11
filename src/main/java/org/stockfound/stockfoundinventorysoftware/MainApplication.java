package org.stockfound.stockfoundinventorysoftware;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.stockfound.stockfoundinventorysoftware.utils.DatabaseCredentialsLoader;

import java.io.IOException;
import java.util.Objects;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("views/main-view.fxml"));

        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        stage.setTitle("Stockfound");
        stage.getIcons().add(new Image(
                Objects.requireNonNull(
                        getClass().getResourceAsStream("images/stockfound-logo.jpg")
                )));
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}