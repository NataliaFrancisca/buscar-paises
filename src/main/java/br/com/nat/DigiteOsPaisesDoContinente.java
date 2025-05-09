package br.com.nat;

import br.com.nat.servico.ServicoEscreverArquivo;
import br.com.nat.servico.ServicoPaises;

import java.time.LocalTime;
import java.util.*;

public class DigiteOsPaisesDoContinente {

    private static Set<String> buscarPaisesDoContinente(String continenteNome){
        ServicoPaises servicoPaises = new ServicoPaises();
        return servicoPaises.buscarNomePaisesContinente(continenteNome);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ServicoEscreverArquivo servicoEscreverArquivo = new ServicoEscreverArquivo();

        System.out.print("Digite o nome do continente: ");
        String continenteNome = scanner.nextLine();

        Set<String> listaPaisesAPI = buscarPaisesDoContinente(continenteNome);
        Set<String> listaPaisesUsuario = new LinkedHashSet<>();

        LocalTime horaInicioJogo = LocalTime.now();
        LocalTime horaFimJogo = horaInicioJogo.plusSeconds(60);

        System.out.println("O jogo começa agora! Digite o número máximo de países que você conseguir.");
        System.out.println("*".repeat(20));

        while(LocalTime.now().isBefore(horaFimJogo)){
            System.out.print("Nome do país: ");
            String nomePais = scanner.nextLine();

            if(listaPaisesAPI.contains(nomePais)){
                listaPaisesUsuario.add(nomePais);
            }else{
                System.out.println("País não encontrado, ou digitado incorretamente.");
            }
        }

        System.out.println("Yeahh! Deseja salvar os países em um arquivo .json? (sim/nao)");
        String opcao = scanner.nextLine();

        if(opcao.equalsIgnoreCase("sim")){
            servicoEscreverArquivo.montarArquivo(listaPaisesUsuario, continenteNome, "json");
        }

        scanner.close();
    }
}
