package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private LineChart<String, Number> graphic;

    @FXML
    private VBox coluna_Grades;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @FXML
    public void initialize() {
        ObservableList<XYChart.Series<String, Number>> lineChartData = FXCollections.observableArrayList(
                new XYChart.Series<>("Series 1", FXCollections.observableArrayList(
                        new XYChart.Data<>("Category 1", 1),
                        new XYChart.Data<>("Category 2", 2),
                        new XYChart.Data<>("Category 3", 3)
                ))
        );
        graphic.setData(lineChartData);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Creating the categories
        String[] categorias = gerarCategorias(nThreads);
        xAxis.getCategories().addAll(categorias);

        // Creating a data series
        XYChart.Series<String, Number> series = new XYChart.Series<>();

        // Filling the chart
        for (int i = nThreads; i > 0; i--) {
            // Instantiation and execution of GeneticSelector
            long Inicial = System.currentTimeMillis();
            GeneticSelector selector = new GeneticSelector(i);
            Grade base = new Grade(new ArrayList<>());
            Grade melhorGrade = selector.Gerar(base);
            System.out.println("Melhor grade encontrada com penalização: " + melhorGrade.turmas + "\nFitting: " + melhorGrade.fitting());
            long Final = System.currentTimeMillis();
            long Total = Final - Inicial;
            System.out.println("ms: " + Total);

            series.getData().add(new XYChart.Data<>(String.valueOf(i), Total));
        }

        // Adding the series to the chart
        graphic.getData().add(series);
    }

    private String[] gerarCategorias(int n) {
        String[] categorias = new String[n];
        for (int i = 1; i <= n; i++) {
            categorias[i - 1] = String.valueOf(i);
        }
        return categorias;
    }
}