package com.screensound.screensound.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import com.screensound.screensound.model.ArtistaInfo;
import com.screensound.screensound.repository.ArtistaInfoRepository;

@Service
public class ConsultaGemini {

  private final String apiKey;
  private final String model;
  private final ArtistaInfoRepository artistaInfoRepository;

  public ConsultaGemini(@Value("${gemini.api-key}") String apiKey,
      @Value("${gemini.model}") String model,
      ArtistaInfoRepository artistaInfoRepository) {
    this.apiKey = apiKey;
    this.model = model;
    this.artistaInfoRepository = artistaInfoRepository;
  }

  public String obterInformacao(String texto) {
    String nomeNormalizado = texto.toLowerCase().trim();

    Optional<ArtistaInfo> cache = artistaInfoRepository.findByNomeArtista(nomeNormalizado);
    if (cache.isPresent()) {
      System.out.println("ℹ️  Informação recuperada do cache (economia de API)");
      return cache.get().getInformacao();
    }

    System.out.println("🌐 Consultando API Gemini...");
    Client client = Client.builder()
        .apiKey(apiKey)
        .build();

    String prompt = String.format(
        "Forneça um resumo conciso sobre o artista %s em no máximo 150 palavras. " +
            "Inclua apenas: nome completo, país de origem, estilo musical principal e principais sucessos.",
        texto);

    GenerateContentResponse response = client.models.generateContent(
        model,
        prompt,
        null);

    String resultado = response.text();

    ArtistaInfo novoCache = new ArtistaInfo(nomeNormalizado, resultado);
    artistaInfoRepository.save(novoCache);

    return resultado;
  }
}
