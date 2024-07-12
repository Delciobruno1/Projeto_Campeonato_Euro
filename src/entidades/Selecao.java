package entidades;
public class Selecao {
    private int id;
    private String nome;
    private int pais_id;
    public Selecao() {
    }

    public Selecao(String nome, int pais_id) {
        this.nome = nome;
        this.pais_id = pais_id;
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
        return pais_id;
    }

    public void setPaisId(int paisId) {
        this.pais_id = pais_id;
    }

    @Override
    public String toString() {
        return "Selecao{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", paisId=" + pais_id +
                '}';
    }
}
