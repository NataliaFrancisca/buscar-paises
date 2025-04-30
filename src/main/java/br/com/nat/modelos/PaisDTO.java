package br.com.nat.modelos;

import java.util.List;
import java.util.Map;

public record PaisDTO (NomeDTO name, List<String> capital, String subregion, Map<String, String> languages, Map<String, String> flags){}