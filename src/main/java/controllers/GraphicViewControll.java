package controllers;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import models.GeneticSelector;
import models.Grade;
import javafx.fxml.Initializable;
import java.util.ArrayList;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.chart.CategoryAxis;

import javafx.scene.chart.NumberAxis;

import static controllers.MainViewControll.nThreads;

public class GraphicViewControll implements Initializable {

    @FXML
    private VBox coluna_Grades;

    @FXML
    private LineChart<Number, String> graphic;

    @FXML
    private NumberAxis xAxis;

    @FXML
    private CategoryAxis yAxis;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //criando as categorias
        String [] categorias = gerarCategorias(nThreads);
        yAxis.getCategories().addAll(categorias);

        // Criando uma série de dados
        XYChart.Series<Number, String> series = new XYChart.Series<>();


        //Preenchendo o grafico
        for (int i = nThreads; i > 0; i-- ) {
            //Intanciação e execução do GeneticSelector
            long Inicial = System.currentTimeMillis();
            GeneticSelector selector = new GeneticSelector(i);
            Grade base = new Grade(new ArrayList<>());
            Grade melhorGrade = selector.Gerar(base);
            System.out.println("Melhor grade encontrada com penalização: " + melhorGrade.turmas +"\nFitting: " + melhorGrade.fitting());
            long Final = System.currentTimeMillis();
            long Total = Final - Inicial;
            System.out.println("ms: " + Total);

            series.getData().add(new XYChart.Data<>(Total, String.valueOf(i)));
        }

        // Adicionando a série ao gráfico
        graphic.getData().add(series);
    }

    private String [] gerarCategorias(int n) {
        String [] categorias = new String[n];
        for (int i = 1; i <= n; i++) {
            categorias[i - 1] = String.valueOf(i);
        }
        return categorias;
    }
}

