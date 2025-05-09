package br.com.nat.repository;

import br.com.nat.integracao.ConectaAPI;
import br.com.nat.modelos.PaisDTO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.net.http.HttpRequest;
import java.util.List;

public class RepositorioPaises {
    private final ConectaAPI conectaAPI = new ConectaAPI();

    private List<PaisDTO> desserializarResposta(String resposta){
        Type paisesListaTipo = new TypeToken<List<PaisDTO>>(){}.getType();
        return new Gson().fromJson(resposta, paisesListaTipo);
    }

    public PaisDTO buscarPais(String nomePais){
        HttpRequest requisicao = this.conectaAPI.criarRequisicao("/name", nomePais);
        String resposta = this.conectaAPI.realizarRequisicao(requisicao);

        return this.desserializarResposta(resposta).getFirst();
    }

    public List<PaisDTO> buscarPaisesContinente(String nomeContinente){
        HttpRequest requisicao = this.conectaAPI.criarRequisicao("/subregion", nomeContinente);
        String resposta = this.conectaAPI.realizarRequisicao(requisicao);

        return this.desserializarResposta(resposta);
    }
}
