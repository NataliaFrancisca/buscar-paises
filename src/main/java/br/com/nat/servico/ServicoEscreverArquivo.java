package br.com.nat.servico;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;

public class ServicoEscreverArquivo {
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private FileWriter criarArquivo(String nomeArquivo, String tipoArquivo){
        try{
            return new FileWriter(nomeArquivo.concat(".").concat(tipoArquivo));
        }catch (Exception exception){
            throw new RuntimeException("Não foi possível criar o arquivo.");
        }
    }

    public void montarArquivo(Object dadosParaEscreverNoArquivo, String nomeArquivo, String tipoArquivo){
        try{
            FileWriter arquivo = this.criarArquivo(nomeArquivo, tipoArquivo);
            arquivo.write(gson.toJson(dadosParaEscreverNoArquivo));
            arquivo.close();
        }catch (Exception exception){
            throw new RuntimeException("Não foi possível adicionar os dados ao arquivo.");
        }finally {
            System.out.println("Arquivo criado com sucesso.");
        }
    }
}
