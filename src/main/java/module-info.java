module org.stockfound.stockfoundinventorysoftware {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.postgresql.jdbc;
    requires java.sql;


    opens org.stockfound.stockfoundinventorysoftware to javafx.fxml;
    exports org.stockfound.stockfoundinventorysoftware;
    exports org.stockfound.stockfoundinventorysoftware.entities;
    exports org.stockfound.stockfoundinventorysoftware.database;
    exports org.stockfound.stockfoundinventorysoftware.services;
    exports org.stockfound.stockfoundinventorysoftware.utils;
    opens org.stockfound.stockfoundinventorysoftware.entities;
    opens org.stockfound.stockfoundinventorysoftware.database;
    opens org.stockfound.stockfoundinventorysoftware.services;
    opens org.stockfound.stockfoundinventorysoftware.utils;
    exports org.stockfound.stockfoundinventorysoftware.controllers.main_controllers;
    opens org.stockfound.stockfoundinventorysoftware.controllers.main_controllers;
    exports org.stockfound.stockfoundinventorysoftware.controllers.crud_controllers.crud_items;
    opens org.stockfound.stockfoundinventorysoftware.controllers.crud_controllers.crud_items;
    exports org.stockfound.stockfoundinventorysoftware.controllers.crud_controllers.crud_sellers;
    opens org.stockfound.stockfoundinventorysoftware.controllers.crud_controllers.crud_sellers;
}