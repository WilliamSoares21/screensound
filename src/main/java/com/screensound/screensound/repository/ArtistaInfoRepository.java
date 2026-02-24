package com.screensound.screensound.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.screensound.screensound.model.ArtistaInfo;

public interface ArtistaInfoRepository extends JpaRepository<ArtistaInfo, Long> {

  Optional<ArtistaInfo> findByNomeArtista(String nomeArtista);
}
