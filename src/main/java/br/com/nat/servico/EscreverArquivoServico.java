package br.com.nat.servico;

import br.com.nat.modelos.Pais;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

public class EscreverArquivoServico {
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public void escrever(Set<Pais> paises) throws IOException {
        FileWriter arquivo = new FileWriter("paises.json");
        arquivo.write(gson.toJson(paises));
        arquivo.close();
        System.out.println("programa gerou arquivo com nome dos países.");
    }
}
