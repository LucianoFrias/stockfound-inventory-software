package org.stockfound.stockfoundinventorysoftware.utils;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

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

    public static void insertItemsIntoChoiceBox(ChoiceBox choiceBox, ObservableList items){
        choiceBox.getItems().removeAll(choiceBox.getItems());
        choiceBox.getItems().addAll(items);
        choiceBox.getSelectionModel().selectFirst();
    }

    public static void openWindow(String destinationPath, Initializable controller, String windowName){
        FXMLLoader loader = new FXMLLoader(CustomJavaFX.class.getResource(destinationPath));
        loader.setControllerFactory(param -> controller);
        Parent root = null;

        try {
            root = loader.load();
        } catch (IOException ex) {
            showErrorPopUp("Window Error", "Window Not Available", ex.getMessage());
            throw new RuntimeException(ex);
        }

        Stage windowStage = new Stage();
        windowStage.initModality(Modality.APPLICATION_MODAL);
        windowStage.setTitle(windowName);
        windowStage.getIcons().add(new Image(
                String.valueOf(CustomJavaFX.class.getResource("/org/stockfound/stockfoundinventorysoftware/images/stockfound-logo.jpg"))));
        windowStage.setResizable(false);
        windowStage.setScene(new Scene(root));


        windowStage.show();
    }

    public static void changeTab(String destinationPath, Initializable destinationController, ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(CustomJavaFX.class.getResource(destinationPath));
            loader.setControllerFactory(param -> destinationController);
            Parent root = loader.load();

            Stage primaryStage = (Stage)((Node) event.getSource()).getScene().getWindow();
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("Stockfound");

            primaryStage.getIcons().add(new Image(
                    Objects.requireNonNull(CustomJavaFX.class.getResourceAsStream("/org/stockfound/stockfoundinventorysoftware/images/stockfound-logo.jpg"))));

            primaryStage.show();

        } catch (IOException ex) {
            showErrorPopUp("Tab Error", "Tab Not Available", ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

}
