package br.com.nat;

import br.com.nat.controle.BuscarPaisControle;
import br.com.nat.modelos.Pais;
import br.com.nat.servico.EscreverArquivoServico;

import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        BuscarPaisControle buscarPaisControle = new BuscarPaisControle();
        EscreverArquivoServico escreverArquivo = new EscreverArquivoServico();

        Set<Pais> paises = new LinkedHashSet<>();
        String paisBusca = "";

        while(!paisBusca.equalsIgnoreCase("sair")){
            System.out.println("Digite o nome do país: " );
            paisBusca =  scanner.nextLine();

            if(paisBusca.equalsIgnoreCase("sair")){
                break;
            }

            Optional<Pais> pais = buscarPaisControle.buscarPais(paisBusca);

            pais.ifPresentOrElse(p -> {
                paises.add(p);
                System.out.println(p.getNome() + ", adicionado com sucesso.");
            }, () -> {
                System.out.println("País não encontrado. Tente novamente.");
            });
        }

        escreverArquivo.escrever(paises);
        scanner.close();
    }
}