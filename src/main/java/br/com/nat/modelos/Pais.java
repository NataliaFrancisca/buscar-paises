package br.com.nat.modelos;

import java.util.List;

public class Pais {
    private String nome;
    private String nomeOficial;
    private List<String> capital;
    private String continente;
    private List<String> idiomas;
    private Object bandeiraImgURL;

    public Pais(String nome, String nomeOficial, List<String> capital, String continente, List<String> idioma, Object bandeiraImgURL) {
        this.nome = nome;
        this.nomeOficial = nomeOficial;
        this.capital = capital;
        this.continente = continente;
        this.idiomas = idioma;
        this.bandeiraImgURL = bandeiraImgURL;
    }

    public Pais(PaisDTO paisDTO){
        this.nome = paisDTO.name().common();
        this.nomeOficial = paisDTO.name().official();
        this.capital = paisDTO.capital();
        this.continente = paisDTO.subregion();
        this.idiomas = paisDTO.languages().values().stream().toList();
        this.bandeiraImgURL = paisDTO.flags();
    }

    public String getNome() {
        return nome;
    }

    public String getNomeOficial() {
        return nomeOficial;
    }

    public List<String> getCapital() {
        return capital;
    }

    public String getContinente() {
        return continente;
    }

    public Object getIdiomas() {
        return idiomas;
    }

    public Object getBandeiraImgURL() {
        return bandeiraImgURL;
    }

    @Override
    public String toString() {
        return """
                Nome: %s
                Nome Oficial: %s
                Capital: %s
                Continente: %s
                Idioma: %s
                Bandeira: %s
                """.formatted(this.nome, this.nomeOficial, this.capital.getFirst(), this.continente, this.idiomas, this.bandeiraImgURL);
    }
}
