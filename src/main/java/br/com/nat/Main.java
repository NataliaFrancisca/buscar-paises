package br.com.nat;

import br.com.nat.controle.BuscarPaisControle;
import br.com.nat.modelos.Pais;
import br.com.nat.servico.EscreverArquivoServico;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    private static String receberTextoTeclado(Scanner scanner){
        System.out.println("Digite o nome do país: " );
        return scanner.nextLine();
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        BuscarPaisControle buscarPaisControle = new BuscarPaisControle();
        EscreverArquivoServico escreverArquivo = new EscreverArquivoServico();

        List<Pais> paises = new ArrayList<>();
        String paisBusca = "";

        while(!paisBusca.equalsIgnoreCase("sair")){

            paisBusca = receberTextoTeclado(scanner);

            if(paisBusca.equalsIgnoreCase("sair")){
                break;
            }

            Optional<Pais> pais = buscarPaisControle.buscar(paisBusca);

            pais.ifPresent(p -> {
                paises.add(p);
                System.out.println(p.getNome());
            });
        }

        escreverArquivo.escrever(paises);
        scanner.close();
    }
}