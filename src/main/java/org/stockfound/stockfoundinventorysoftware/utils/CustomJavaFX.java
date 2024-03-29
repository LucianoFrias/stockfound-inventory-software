package org.stockfound.stockfoundinventorysoftware.utils;

import javafx.stage.Stage;
import javafx.stage.Window;

public class CustomJavaFX {

    public static void closeWindow(Window window){
        Stage stage = (Stage) window;
        stage.close();
    }

}
