package br.com.nat;

import br.com.nat.modelos.Pais;
import br.com.nat.servico.ServicoEscreverArquivo;
import br.com.nat.servico.ServicoLerArquivo;
import br.com.nat.servico.ServicoPaises;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.*;

public class PesquisarPais {

    private static Set<Pais> buscarPaisesEmArquivo(ServicoLerArquivo servicoLerArquivo){
        try{
            Type tipoListaPais = new TypeToken<Set<Pais>>(){}.getType();
            return servicoLerArquivo.lerArquivo("paises", "json", tipoListaPais);
        }catch (Exception exception){
            return new HashSet<>();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ServicoPaises servicoPaises = new ServicoPaises();
        ServicoLerArquivo servicoLerArquivo = new ServicoLerArquivo();
        ServicoEscreverArquivo servicoEscreverArquivo = new ServicoEscreverArquivo();

        System.out.print("Digite o nome do país: ");
        String paisNome = scanner.nextLine().trim();

        Pais pais = servicoPaises.buscarDadosPais(paisNome);

        System.out.println("\n" + pais);

        System.out.println("Deseja adicionar esse país no arquivo .json? (sim/não)");
        String opcao = scanner.nextLine();

        if(opcao.equalsIgnoreCase("sim")){
            Set<Pais> paises = buscarPaisesEmArquivo(servicoLerArquivo);

            if(!paises.contains(pais)){
                paises.add(pais);
                servicoEscreverArquivo.montarArquivo(paises, "paises", "json");
            }

            List<String> paisesNome = paises.stream().map(Pais::getNome).toList();
            System.out.println("Países já salvos no seu arquivo: " + paisesNome);
        }

        scanner.close();
    }
}
