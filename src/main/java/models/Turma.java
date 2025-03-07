package models;

class Turma {
    public Salas sala;
    public Disciplinas disciplina;
    public Horarios horario;

    public Turma(Salas s, Disciplinas d, Horarios h) {
        this.sala = s;
        this.disciplina = d;
        this.horario = h;
    }
    @Override
    public String toString() {
        return String.format("Turma(%s, %s, %s)", disciplina, sala, horario);
    }
}

