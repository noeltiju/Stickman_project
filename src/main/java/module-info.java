module com.example.stickman_project {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires javafx.media;

    opens com.example.stickman_project to javafx.fxml;
    exports com.example.stickman_project;
}