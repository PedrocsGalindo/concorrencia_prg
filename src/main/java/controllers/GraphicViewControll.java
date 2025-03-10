package controllers;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.layout.VBox;
import models.GeneticSelector;
import models.Grade;
import javafx.fxml.Initializable;
import java.util.ArrayList;
import java.net.URL;
import java.util.ResourceBundle;

import static controllers.MainViewControll.nThreads;

public class GraphicViewControll implements Initializable {

    @FXML
    private VBox coluna_Grades;

    @FXML
    private LineChart<?, ?> graphic;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Intanciação e execução do GeneticSelector
        long Inicial = System.currentTimeMillis();
        GeneticSelector selector = new GeneticSelector(nThreads);
        Grade base = new Grade(new ArrayList<>());
        Grade melhorGrade = selector.Gerar(base);
        System.out.println("Melhor grade encontrada com penalização: " + melhorGrade.turmas +"\nFitting: " + melhorGrade.fitting());
        long Final = System.currentTimeMillis();
        long Total = Final - Inicial;
        System.out.println("ms: " + Total);
    }
}

