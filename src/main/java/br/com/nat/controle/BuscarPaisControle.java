package br.com.nat.controle;

import br.com.nat.modelos.Pais;
import br.com.nat.modelos.PaisDTO;
import br.com.nat.servico.BuscarPaisesServico;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

public class BuscarPaisControle {
    private BuscarPaisesServico buscarPaisesServico;
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public BuscarPaisControle(){
        this.buscarPaisesServico = new BuscarPaisesServico();
    }

    private PaisDTO deseralizarResposta(String resposta){
        Type countryListType = new TypeToken<List<PaisDTO>>(){}.getType();
        List<PaisDTO> paises = gson.fromJson(resposta, countryListType);
        return paises.getFirst();
    }

    public Optional<Pais> buscar(String nomePais){
        String resposta = this.buscarPaisesServico.buscar(nomePais);

        if(resposta == null){
            return Optional.empty();
        }

        PaisDTO paisDTO = this.deseralizarResposta(resposta);
        return Optional.of(new Pais(paisDTO));
    }
}
