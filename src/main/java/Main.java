import models.Grade;
import models.GeneticSelector;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        long Inicial = System.currentTimeMillis();
        GeneticSelector selector = new GeneticSelector();
        Grade base = new Grade(new ArrayList<>());
        Grade melhorGrade = selector.Gerar(base);
        System.out.println("Melhor grade encontrada com penalização: " + melhorGrade.turmas +"\nFitting: " + melhorGrade.fitting());
        long Final = System.currentTimeMillis();
        long Total = Final - Inicial;
        System.out.println("ms: " + Total);
    }
}

