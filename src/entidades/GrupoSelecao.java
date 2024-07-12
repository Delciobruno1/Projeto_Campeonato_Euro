package entidades;
public class GrupoSelecao {
    private int id;
    private int grupoId;
    private int selecaoId;
    private int pontos;
    private int jogos;
    private int vitorias;
    private int empates;
    private int derrotas;
    private int golsMarcados;
    private int golsSofridos;

    public GrupoSelecao() {
    }

    public int getId() {
        return id;
    }

    public int getGrupoId() {
        return grupoId;
    }

    public int getDerrotas() {
        return derrotas;
    }

    public int getEmpates() {
        return empates;
    }

    public int getGolsMarcados() {
        return golsMarcados;
    }

    public int getGolsSofridos() {
        return golsSofridos;
    }

    public int getJogos() {
        return jogos;
    }

    public int getPontos() {
        return pontos;
    }

    public int getSelecaoId() {
        return selecaoId;
    }

    public int getVitorias() {
        return vitorias;
    }

    @Override
    public String toString() {
        return "GrupoSeleccao{" +
                "id=" + id +
                ", grupoId=" + grupoId +
                ", selecaoId=" + selecaoId +
                ", pontos=" + pontos +
                ", jogos=" + jogos +
                ", vitorias=" + vitorias +
                ", empates=" + empates +
                ", derrotas=" + derrotas +
                ", golsMarcados=" + golsMarcados +
                ", golsSofridos=" + golsSofridos +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setGrupoId(int grupoId) {
        this.grupoId = grupoId;
    }

    public void setSelecaoId(int selecaoId) {
        this.selecaoId = selecaoId;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public void setJogos(int jogos) {
        this.jogos = jogos;
    }

    public void setVitorias(int vitorias) {
        this.vitorias = vitorias;
    }

    public void setEmpates(int empates) {
        this.empates = empates;
    }

    public void setDerrotas(int derrotas) {
        this.derrotas = derrotas;
    }

    public void setGolsMarcados(int golsMarcados) {
        this.golsMarcados = golsMarcados;
    }

    public void setGolsSofridos(int golsSofridos) {
        this.golsSofridos = golsSofridos;
    }
}

