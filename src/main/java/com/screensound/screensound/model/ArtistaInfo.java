package com.screensound.screensound.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "artista_info")
@Getter
@Setter
@NoArgsConstructor
public class ArtistaInfo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true, nullable = false)
  private String nomeArtista;

  @Column(columnDefinition = "TEXT", nullable = false)
  private String informacao;

  @Column(nullable = false)
  private LocalDateTime dataCriacao;

  @Column(nullable = false)
  private LocalDateTime dataAtualizacao;

  public ArtistaInfo(String nomeArtista, String informacao) {
    this.nomeArtista = nomeArtista.toLowerCase().trim();
    this.informacao = informacao;
    this.dataCriacao = LocalDateTime.now();
    this.dataAtualizacao = LocalDateTime.now();
  }
}
