package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.io.IOException;


public class MainViewControll {

    static int nThreads;

    @FXML
    private Button button_testar;

    @FXML
    private TextField textField_theard;

    @FXML
    void click_button_testar(ActionEvent event) {
        System.out.println(textField_theard.getText());

        if (textField_theard.getText().isEmpty()){
            Alert alerta = new Alert(AlertType.WARNING);
            alerta.setTitle("Aviso");
            alerta.setHeaderText(null);
            alerta.setContentText("Você deve preencher o campo com o numero de threads");
            alerta.showAndWait();
        } else {
            try{
                // numero de threads
                nThreads = Integer.parseInt(textField_theard.getText());

                // tela de grafico
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/graphicView.fxml"));
                Parent root = loader.load();

                // janela atual
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("Nova Tela");
                stage.show();

            } catch (IOException e) {
                System.err.println("Erro ao carregar a nova tela: " + e.getMessage());
                e.printStackTrace();

            } catch (NumberFormatException e){
                Alert alerta = new Alert(AlertType.WARNING);
                alerta.setTitle("Aviso");
                alerta.setHeaderText(null);
                alerta.setContentText("Você deve preencher o campo com numero");
                alerta.showAndWait();
            }
        }
    }
}

