package entidades;

public class Cidade {
    private int id;
    private String nome;
    private int paisId;
    public Cidade() {}
    public Cidade(String nome, int paisId) {
        this.nome = nome;
        this.paisId = paisId;
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

    public int getPaisId() {
        return paisId;
    }

    public void setPaisId(int paisId) {
        this.paisId = paisId;
    }

    @Override
    public String toString() {
        return "Cidade{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", paisId=" + paisId +
                '}';
    }
}
