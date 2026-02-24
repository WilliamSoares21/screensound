package com.screensound.screensound.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.screensound.screensound.model.Artista;
import com.screensound.screensound.model.Musica;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {

  Optional<Artista> findByNomeContainingIgnoreCase(String nome);

  @Query("SELECT m FROM Artista a JOIN a.musicas m WHERE LOWER(a.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
  List<Musica> buscarMusicasPorArtista(String nome);
}
