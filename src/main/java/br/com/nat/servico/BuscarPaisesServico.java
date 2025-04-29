package br.com.nat.servico;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class BuscarPaisesServico {
    private final String API_URL = "https://restcountries.com/v3.1/name/%s";
    private final HttpClient httpClient;

    public BuscarPaisesServico(){
        this.httpClient = HttpClient.newHttpClient();
    }

    private String formatarNomeParaRequisicao(String nomePais){
        return URLEncoder.encode(nomePais, StandardCharsets.UTF_8)
                        .replace("+", "%20");
    }

    public String buscar(String nomePais){
        String nomePaisFormatado = this.formatarNomeParaRequisicao(nomePais);
        String urlCompleta = API_URL.formatted(nomePaisFormatado);

        try{
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(urlCompleta))
                    .build();

            HttpResponse<String> response = this.httpClient.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );

            if(response.statusCode() != 200){
                System.out.println("País não encontrado: " + nomePais);
                return null;
            }

            return response.body();
        }catch (Exception exception){
            System.out.println("Erro ao buscar o país: " + exception.getMessage());
            return null;
        }
    }
}
