package models;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class GeneticSelector {
    public final float TAXAMUTACAO = 0.1f; // 10% de chance de mutação
    public final int GERACOES = 100;
    public final int POPULACAO = 1000;
    public ExecutorService executor = Executors.newFixedThreadPool(5); // Executor com 5 threads
    private Grade[] Popular(Grade base) throws InterruptedException {
        Grade[] populacao = new Grade[POPULACAO];
        List<Future<Void>> futures = new ArrayList<>(); // Lista para armazenar os futuros

        // Submetendo as tarefas para execução
        for (int i = 0; i < POPULACAO; i++) {
            final int index = i; // Para capturar o índice dentro da lambda
            Future<Void> future = executor.submit(() -> {
                // Simulando um trabalho e criando uma nova Grade
                ArrayList<Turma> turmas = mutacao(base).turmas;
                populacao[index] = new Grade(new ArrayList<>(turmas)); // Atribuindo no índice correto
                return null; // Tarefa sem retorno
            });
            futures.add(future);
        }

        // Esperando todas as tarefas terminarem
        for (Future<Void> future : futures) {
            try {
                future.get(); // Aguarda a conclusão de cada tarefa
            } catch (ExecutionException e) {
                e.printStackTrace(); // Tratar erros se ocorrerem
            }
        }

        return populacao;
    }


    private Grade SelecaoNatura(List<Grade> populacao) {
        // Deve pegar apenas o melhor da stream a qual pertence e retornar ele
        populacao.sort(Comparator.comparingInt(Grade::fitting));
        Grade pai1 = populacao.get(0);
        Grade pai2 = populacao.get(1);
        return GerarFilho(pai1, pai2);
    }

    private Grade GerarFilho(Grade pai1, Grade pai2) {
        // Otimizar para que possa gerar o filho atraves de uma lista de pais.
        // Bônus: escolhor de maneira mais inteligênte quais turmas passam para o filho.
        ArrayList<Turma> novaTurmas = new ArrayList<>();
        for (int i = 0; i < pai1.turmas.size(); i++) {
            if (Math.random() > 0.5) {
                novaTurmas.add(pai1.turmas.get(i));
            } else {
                novaTurmas.add(pai2.turmas.get(i));
            }
        }
        return new Grade(novaTurmas);
    }

    private Grade mutacao(Grade grade) {
        Random random = new Random();
        Grade novaGrade = grade;
        for (int i = 0; i < novaGrade.turmas.size(); i++) {
            // Chance de ocorrer a mutação
            if (random.nextFloat() < TAXAMUTACAO) {
                Turma turma = novaGrade.turmas.get(i);
                // Alterna aleatoriamente a sala ou o horário
                if (random.nextBoolean()) {
                    turma.sala = Salas.values()[random.nextInt(Salas.values().length)];
                } else {
                    turma.horario = Horarios.values()[random.nextInt(Horarios.values().length)];
                }
            }
        }
        return novaGrade;
    }

    public Grade Gerar(Grade origem) {
        // Função principal onde todo o processo evolutivo esta acontecendo
        // Deve ser adaptado para lidar com multiplas streams
        Grade pai = origem;
        for (int i = 0; i < GERACOES; i++) {
            try{
                ArrayList<Grade> populacao = new ArrayList<>(Arrays.asList(Popular(pai)));
                populacao.sort(Comparator.comparingInt(Grade::fitting));
                if (populacao.get(0).fitting() == 0) {
                    break;
                }
                Grade novoPai = SelecaoNatura(populacao);
                pai = novoPai;

                // Aplica a mutação
                pai = mutacao(pai);
            }
            catch(Exception e){
                System.out.println("Problema");
            }
        }
        executor.shutdown(); // Finaliza o executor
        return pai;
    }
}

