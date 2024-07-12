package entidades;

import java.util.Date;

public class Partida {
    private int id;
    private Date data;
    private Estadio estadio;
    private int selecaoCasa_id;
    private int selecaoFora_id;
    private int golsCasa;
    private int golsFora;
    private Selecao selecaoCasa;
    private Selecao selecaoFora;


    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Estadio getEstadio() {
        return estadio;
    }

    public void setEstadio(Estadio estadio) {
        this.estadio = estadio;
    }

    public int getSelecaoCasa_id() {
        return selecaoCasa_id;
    }

    public int getSelecaoFora_id() {
        return selecaoFora_id;
    }

    public void setSelecaoCasa_id(int selecaoCasa_id) {
        this.selecaoCasa_id = selecaoCasa_id;
    }

    public void setSelecaoFora_id(int selecaoFora_id) {
        this.selecaoFora_id = selecaoFora_id;
    }

    public int getGolsCasa() {
        return golsCasa;
    }

    public void setGolsCasa(int golsCasa) {
        this.golsCasa = golsCasa;
    }

    public int getGolsFora() {
        return golsFora;
    }

    public void setGolsFora(int golsFora) {
        this.golsFora = golsFora;
    }

    // ToString method
    @Override
    public String toString() {
        return "Partida{" +
                "id=" + id +
                ", data=" + data +
                ", estadio=" + estadio +
                ", golsCasa=" + golsCasa +
                ", golsFora=" + golsFora +
                '}';
    }

    public void setSelecaoCasa(Selecao selecaoCasa) {
        this.selecaoCasa = selecaoCasa;
    }

    public Selecao getSelecaoCasa() {
        return selecaoCasa;
    }

    public void setSelecaoFora(Selecao selecaoFora) {
        this.selecaoFora = selecaoFora;
    }

    public Selecao getSelecaoFora() {
        return selecaoFora;
    }
}
