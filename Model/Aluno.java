package Model;

import java.util.ArrayList;

public class Aluno {

    private int id;
    private String nome;
    private int idade;
    private String curso;
    private int fase;
    private static ArrayList<Aluno> lista = new ArrayList<>();
    private static int contador = 1;

    public Aluno() {}

    public Aluno(int id, String nome, int idade, String curso, int fase) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.curso = curso;
        this.fase = fase;
    }

    public boolean InsertAlunoBD(String curso, int fase, String nome, int idade) {
        Aluno novo = new Aluno(contador++, nome, idade, curso, fase);
        lista.add(novo);
        return true;
    }

    public boolean UpdateAlunoBD(String curso, int fase, int id, String nome, int idade) {
        for (Aluno a : lista) {
            if (a.getId() == id) {
                a.setNome(nome);
                a.setIdade(idade);
                a.setCurso(curso);
                a.setFase(fase);
                return true;
            }
        }
        return false;
    }

    public boolean DeleteAlunoBD(int id) {
        return lista.removeIf(a -> a.getId() == id);
    }

    public ArrayList<Aluno> getMinhaLista() {
        return lista;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public int getIdade() { return idade; }
    public String getCurso() { return curso; }
    public int getFase() { return fase; }

    public void setId(int id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setIdade(int idade) { this.idade = idade; }
    public void setCurso(String curso) { this.curso = curso; }
    public void setFase(int fase) { this.fase = fase; }
}