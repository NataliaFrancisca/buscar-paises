package br.com.nat.servico;

import br.com.nat.modelos.PaisDTO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class BuscarPaisServico {
    private final HttpClient httpClient = HttpClient.newHttpClient();

    private PaisDTO desserializarResposta(String resposta){
        Type countryListType = new TypeToken<List<PaisDTO>>(){}.getType();
        List<PaisDTO> paises = new Gson().fromJson(resposta, countryListType);
        return paises.getFirst();
    }

    private String formatarNomeParaRequisicao(String nomePais){
        return URLEncoder.encode(nomePais, StandardCharsets.UTF_8)
                .replace("+", "%20");
    }

    public PaisDTO buscar(String nomePais) {
        final String API_URL = "https://restcountries.com/v3.1/name/%s";
        final String nomePaisFormatado = this.formatarNomeParaRequisicao(nomePais);
        final String urlCompleta = API_URL.formatted(nomePaisFormatado);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlCompleta))
                .build();

        try{
            HttpResponse<String> response = this.httpClient.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );

            if(!(response.statusCode() == 200)){
                return null;
            }

            return this.desserializarResposta(response.body());
        }catch (Exception exception){
            throw new RuntimeException("Não consegui obter o endereço a partir desse CEP.");
        }
    }
}
