package br.com.nat.controle;

import br.com.nat.modelos.Pais;
import br.com.nat.modelos.PaisDTO;
import br.com.nat.servico.BuscarPaisServico;

import java.util.Optional;

public class BuscarPaisControle {
    private final BuscarPaisServico buscarPaisServico;

    public BuscarPaisControle(){
        this.buscarPaisServico = new BuscarPaisServico();
    }

    public Optional<Pais> buscarPais(String nomePais){
        PaisDTO paisDTO = this.buscarPaisServico.buscar(nomePais);

        if(paisDTO == null){
            return Optional.empty();
        }

        return Optional.of(new Pais(paisDTO));
    }
}
