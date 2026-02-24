package com.screensound.screensound.principal;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.screensound.screensound.model.Artista;
import com.screensound.screensound.model.Musica;
import com.screensound.screensound.model.TipoArtista;
import com.screensound.screensound.repository.ArtistaRepository;

public class Principal {

  private Scanner scan = new Scanner(System.in);
  private ArtistaRepository artistaRepository;

  public Principal(ArtistaRepository artistaRepository) {
    this.artistaRepository = artistaRepository;
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
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'pesquisarDadosDoArtista'");
  }

  private void buscarMusicasPorArtista() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'buscarMusicasPorArtista'");
  }

  private void listarMusicas() {
    List<Artista> artistas = artistaRepository.findAll();
    artistas.forEach(artista -> {
      System.out.println("Artista: " + artista.getNome());
      artista.getMusicas().forEach(musica -> {
        System.out.println(" - Música: " + musica.getTitulo());
      });
    });
    System.out.println("\n");

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
