module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.desktop;

    // Abrindo os pacotes para reflexão do JavaFX
    opens controllers to javafx.fxml;
    exports controllers;

    opens models to javafx.fxml;
    exports models;

    opens App to javafx.fxml, javafx.graphics;  // Permite acesso ao JavaFX
    exports App;
}
