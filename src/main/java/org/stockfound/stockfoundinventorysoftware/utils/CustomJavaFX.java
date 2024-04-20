package org.stockfound.stockfoundinventorysoftware.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.stockfound.stockfoundinventorysoftware.controllers.CustomersViewController;

import java.io.IOException;
import java.util.Objects;

public class CustomJavaFX {

    public static void closeWindow(Window window){
        Stage stage = (Stage) window;
        stage.close();
    }

    public static void showErrorPopUp(String title, String headerText, String contentText){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

}
