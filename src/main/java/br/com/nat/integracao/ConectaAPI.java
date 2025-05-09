package br.com.nat.integracao;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class ConectaAPI {
    private final String API_URL = "https://restcountries.com/v3.1";
    private final HttpClient httpClient = HttpClient.newHttpClient();

    private String formatarTextoPesquisa(String query){
        return URLEncoder.encode(query, StandardCharsets.UTF_8)
                .replace("+", "%20");
    }

    public HttpRequest criarRequisicao(String endpoint, String query){
        final String url = API_URL.concat(endpoint).concat("/%s");
        final String queryFormatada = this.formatarTextoPesquisa(query);
        final URI uri = URI.create(url.formatted(queryFormatada));

        return HttpRequest.newBuilder().uri(uri).build();
    }

    public String realizarRequisicao(HttpRequest httpRequest){
        try{
            HttpResponse<String> response = this.httpClient.send(
                    httpRequest,
                    HttpResponse.BodyHandlers.ofString()
            );

            if(!(response.statusCode() == 200)){
                return null;
            }

            return response.body();
        }catch (Exception exception){
            throw new RuntimeException("Não foi possível obter os dados a partir da query informada.");
        }
    }
}
