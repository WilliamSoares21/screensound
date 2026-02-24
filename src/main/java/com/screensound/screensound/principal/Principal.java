package com.screensound.screensound.principal;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.screensound.screensound.model.Artista;
import com.screensound.screensound.model.Musica;
import com.screensound.screensound.model.TipoArtista;
import com.screensound.screensound.repository.ArtistaRepository;
import com.screensound.screensound.service.ConsultaGemini;

public class Principal {

  private final Scanner scan = new Scanner(System.in);
  private final ArtistaRepository artistaRepository;
  private final ConsultaGemini consultaGemini;

  public Principal(ArtistaRepository artistaRepository, ConsultaGemini consultaGemini) {
    this.artistaRepository = artistaRepository;
    this.consultaGemini = consultaGemini;
  }

  public void exibeMenu() {
    var opcao = -1;

    while (opcao != 9) {
      var menu = """
          *** Screen Sound Músicas ***

          1- Cadastrar artistas
          2- Cadastrar músicas
          3- Listar músicas
          4- Buscar músicas por artistas
          5- Pesquisar dados sobre um artista

          9 - Sair
          """;

      System.out.println(menu);
      opcao = scan.nextInt();
      scan.nextLine();

      switch (opcao) {
        case 1:
          cadastrarArtistas();
          break;
        case 2:
          cadastrarMusicas();
          break;
        case 3:
          listarMusicas();
          break;
        case 4:
          buscarMusicasPorArtista();
          break;
        case 5:
          pesquisarDadosDoArtista();
          break;
        case 9:
          System.out.println("Encerrando a aplicação!");
          System.exit(0);
          break;
        default:
          System.out.println("Opção inválida!");
      }
    }
  }

  private void cadastrarMusicas() {
    System.out.println("Deseja cadastrar musica de qual artista?");
    String nome = scan.nextLine();
    Optional<Artista> artista = artistaRepository.findByNomeContainingIgnoreCase(nome);
    if (artista.isPresent()) {
      System.out.println("Qual o nome da música ?");
      var nomeMusica = scan.nextLine();
      Musica musica = new Musica(nomeMusica);
      musica.setArtista(artista.get());
      artista.get().getMusicas().add(musica);
      artistaRepository.save(artista.get());
    } else {
      System.out.println("Artista não encontrado!");
    }
    System.out.println("\n");
  }

  private void pesquisarDadosDoArtista() {
    System.out.println("Pesquisar dados sobre qual artista?");
    var nome = scan.nextLine();
    var resposta = consultaGemini.obterInformacao(nome);
    System.out.println(resposta.trim());
  }

  private void buscarMusicasPorArtista() {
    System.out.println("Deseja buscar músicas de qual artista?");
    var nome = scan.nextLine();
    List<Musica> musicas = artistaRepository.buscarMusicasPorArtista(nome);

    String titulo = " Músicas encontradas para: " + nome + " ";
    String separador = "=".repeat(Math.max(40, titulo.length()));
    System.out.println(separador);
    System.out.println(centerText(titulo, separador.length()));
    System.out.println(separador);

    if (musicas.isEmpty()) {
      System.out.println("Nenhuma música encontrada para este artista.");
    } else {
      System.out.printf("%-5s | %-30s%n", "Nº", "Título da Música");
      System.out.println("-".repeat(40));
      int i = 1;
      for (Musica musica : musicas) {
        System.out.printf("%-5d | %-30s%n", i++, musica.getTitulo());
      }
    }
    System.out.println(separador);
  }

  // Método auxiliar para centralizar texto
  private String centerText(String text, int width) {
    int padding = (width - text.length()) / 2;
    return " ".repeat(Math.max(0, padding)) + text;
  }

  private void listarMusicas() {
    List<Artista> artistas = artistaRepository.findAll();
    System.out.println("========== Lista de Músicas por Artista ==========\n");
    artistas.forEach(artista -> {
      System.out.println("Artista: " + artista.getNome());
      System.out.println("Tipo: " + artista.getTipo());
      if (artista.getMusicas().isEmpty()) {
        System.out.println("  (Nenhuma música cadastrada)");
      } else {
        artista.getMusicas().forEach(musica -> {
          System.out.println("  • " + musica.getTitulo());
        });
      }
      System.out.println("----------------------------------------");
    });
  }

  private void cadastrarArtistas() {
    var cadastrarNovo = "s";

    while (cadastrarNovo.equalsIgnoreCase("s")) {
      System.out.println("Informe o nome do artista:");
      var nome = scan.nextLine();

      System.out.println("informe o tip do artista : (solo, dupla ou banda)");
      var tipo = scan.nextLine();

      TipoArtista tipoArtista = TipoArtista.valueOf(tipo.toUpperCase());

      Artista artista = new Artista(nome, tipoArtista);
      artistaRepository.save(artista);

      System.out.println("Deseja cadastrar outro artista? (s/n)");
      cadastrarNovo = scan.nextLine();
    }
  }
}
