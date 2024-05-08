package org.stockfound.stockfoundinventorysoftware.utils;

import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

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
