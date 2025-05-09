package br.com.nat.servico;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;

public class ServicoLerArquivo {
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private File buscarArquivo(String nomeArquivo, String tipoArquivo) throws FileNotFoundException {
        File file = new File(nomeArquivo.concat(".").concat(tipoArquivo));

        if(!file.exists()){
            throw new FileNotFoundException("Não foi possível encontrar o arquivo.");
        }

        return file;
    }

    public <T> T lerArquivo(String nomeArquivo, String tipoArquivo, Type tipoRetorno) throws FileNotFoundException {
        File file = this.buscarArquivo(nomeArquivo, tipoArquivo);

        try(FileReader reader = new FileReader(file)){
            return gson.fromJson(reader, tipoRetorno);
        } catch (Exception exception){
            throw new FileNotFoundException(exception.getMessage());
        }
    }
}
