package models;

import java.util.*;

public class Grade {
    public ArrayList<Turma> turmas;

    public Grade(ArrayList<Turma> turmas) {
        this.turmas = new ArrayList<>(turmas);
        garantirTodasDisciplinas();
    }

    private void garantirTodasDisciplinas() {
        Map<Disciplinas, Integer> contadorDisciplinas = new HashMap<>();

        // Conta o número de vezes que cada disciplina já aparece
        for (Turma turma : turmas) {
            contadorDisciplinas.put(turma.disciplina, contadorDisciplinas.getOrDefault(turma.disciplina, 0) + 1);
        }

        Random random = new Random();

        // Para cada disciplina no enum, adiciona até que ela apareça 2 vezes
        for (Disciplinas d : Disciplinas.values()) {
            int count = contadorDisciplinas.getOrDefault(d, 0);

            while (count < 2) {
                // Adiciona uma nova turma com a disciplina se ela aparecer menos que 2 vezes
                Salas sala = Salas.values()[random.nextInt(Salas.values().length)];
                Horarios horario = Horarios.values()[random.nextInt(Horarios.values().length)];
                turmas.add(new Turma(sala, d, horario));

                // Incrementa o contador para essa disciplina
                count++;
            }
        }
    }

    public int fitting() {
        int penalizacao = 0;
        Set<String> horariosOcupados = new HashSet<>();

        for (Turma turma : turmas) {
            String key = turma.horario + ":" + turma.sala;
            if (!horariosOcupados.add(key)) {
                penalizacao++;
            }
        }
        return penalizacao;
    }
}

