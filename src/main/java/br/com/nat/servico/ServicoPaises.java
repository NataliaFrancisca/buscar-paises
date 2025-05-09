package br.com.nat.servico;

import br.com.nat.modelos.Pais;
import br.com.nat.modelos.PaisDTO;
import br.com.nat.repository.RepositorioPaises;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

public class ServicoPaises {
    private final RepositorioPaises repositorioPaises = new RepositorioPaises();

    public Pais buscarDadosPais(String nomePais){
        PaisDTO paisDTO = this.repositorioPaises.buscarPais(nomePais);

        if(paisDTO == null){
            throw  new NoSuchElementException("Não foi possível encontrar esse país.");
        }

        return new Pais(paisDTO);
    }

    public Set<String> buscarNomePaisesContinente(String nomeContinente){
        List<PaisDTO> paisesDTO = this.repositorioPaises.buscarPaisesContinente(nomeContinente);

        if(paisesDTO == null){
            throw new NoSuchElementException("Não foi possível encontrar esse continente.");
        }

        return paisesDTO.stream().map(Pais::new).map(pais -> pais.getNome().toLowerCase()).collect(Collectors.toSet());
    }
}
