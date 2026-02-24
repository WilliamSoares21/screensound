package com.screensound.screensound.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.screensound.screensound.model.Artista;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {

  Optional<Artista> findByNomeContainingIgnoreCase(String nome);
}
