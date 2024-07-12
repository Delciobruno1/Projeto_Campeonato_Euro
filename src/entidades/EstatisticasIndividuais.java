package entidades;

public class EstatisticasIndividuais {
    private int golos;
    private int remates;
    private int foraJogos;
    private int faltas;
    private int assistencias;
    private int jogador_id;
    private int passes;
    private int partida_id;
    private Jogador jogador;

    public EstatisticasIndividuais(){};

    public int getGolos() {
        return golos;
    }

    public void setGolos(int golos) {
        this.golos = golos;
    }

    public int getRemates() {
        return remates;
    }

    public void setRemates(int remates) {
        this.remates = remates;
    }

    public int getForaJogos() {
        return foraJogos;
    }

    public void setForaJogos(int foraJogos) {
        this.foraJogos = foraJogos;
    }

    public int getFaltas() {
        return faltas;
    }

    public void setFaltas(int faltas) {
        this.faltas = faltas;
    }

    public int getAssistencias() {
        return assistencias;
    }

    public void setAssistencias(int assistencias) {
        this.assistencias = assistencias;
    }

    public int getJogador_id() {
        return jogador_id;
    }

    public void setJogador_id(int jogador_id) {
        this.jogador_id = jogador_id;
    }

    public int getPasses() {
        return passes;
    }

    public void setPasses(int passes) {
        this.passes = passes;
    }

    public int getPartida_id() {
        return partida_id;
    }

    public void setPartida_id(int partida_id) {
        this.partida_id = partida_id;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }
}
