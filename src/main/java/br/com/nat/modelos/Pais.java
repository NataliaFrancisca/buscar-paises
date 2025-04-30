package br.com.nat.modelos;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Pais {
    private String nome;
    private String nomeOficial;
    private List<String> capital;
    private String continente;
    private List<String> idiomas;
    private Map<String, String> bandeiraImgURL;

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

    public List<String> getIdiomas() {
        return idiomas;
    }

    public Map<String, String> getBandeiraImgURL() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pais pais = (Pais) o;
        return Objects.equals(nome, pais.nome) && Objects.equals(nomeOficial, pais.nomeOficial);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, nomeOficial, capital, continente, idiomas, bandeiraImgURL);
    }
}
