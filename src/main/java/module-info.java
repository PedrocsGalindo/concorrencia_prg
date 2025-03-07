module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens concorrencia.concorrencia to javafx.fxml;
    exports concorrencia.concorrencia;
}