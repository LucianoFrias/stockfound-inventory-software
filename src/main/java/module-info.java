module org.stockfound.stockfoundinventorysoftware {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.postgresql.jdbc;
    requires java.sql;


    opens org.stockfound.stockfoundinventorysoftware to javafx.fxml;
    exports org.stockfound.stockfoundinventorysoftware;
    exports org.stockfound.stockfoundinventorysoftware.controllers;
    exports org.stockfound.stockfoundinventorysoftware.entities;
    exports org.stockfound.stockfoundinventorysoftware.database;
    exports org.stockfound.stockfoundinventorysoftware.services;
    exports org.stockfound.stockfoundinventorysoftware.utils;
    opens org.stockfound.stockfoundinventorysoftware.controllers to javafx.fxml;
    opens org.stockfound.stockfoundinventorysoftware.entities to javafx.fxml;
    opens org.stockfound.stockfoundinventorysoftware.database to javafx.fxml;
    opens org.stockfound.stockfoundinventorysoftware.services to javafx.fxml;
    opens org.stockfound.stockfoundinventorysoftware.utils to javafx.fxml;
}