package entidades;
import entidades.Selecao;
public class Jogador {
    private int id;
    private String nome;
    private String dataNascimento;
    private String posicao;
    private int selecao_id;
    private Selecao selecao;

    public int getId() {
        return id;
    }
    public String getPosicao() {
        return posicao;
    }

    public String getNome() {
        return nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }
    public int getSelecao_id() {
        return selecao_id;
    }
    public void setSelecao_id(int selecao_id) {
        this.selecao_id = selecao_id;
    }
    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }
    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setId(int id) {
        this.id = id;
    }
    // Construtor, getters e setters omitidos para brevidade

    @Override
    public String toString() {
        return "Jogador{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", data_nascimento=" + dataNascimento +
                ", posicao='" + posicao + '\'' +
        '}';
    }

    public void setSelecao(Selecao selecao) {
        this.selecao = selecao;
    }

    public Selecao getSelecao() {
        return selecao;
    }
}
