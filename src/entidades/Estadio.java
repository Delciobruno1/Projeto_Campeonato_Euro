package entidades;

public class Estadio {
    private int id;
    private String nome;
    private int capacidade;
    private int cidade_id;

    public Estadio() {
    }

    public Estadio(String nome, int capacidade, int cidade_id) {
        this.nome = nome;
        this.capacidade = capacidade;
        this.cidade_id = cidade_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public int getCidade_id() {
        return cidade_id;
    }
    public void setCidade_id(int cidade_id) {
        this.cidade_id = cidade_id;
    }

    @Override
    public String toString() {
        return "Estadio{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", capacidade=" + capacidade +
        '}';
    }
}
