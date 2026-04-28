module org.multitool {
    requires atlantafx.base;
    requires java.datatransfer;
    requires java.desktop;
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    exports org.multitool;
    opens org.multitool to javafx.fxml;
}