package com.screensound.screensound.principal;

import java.util.Scanner;

public class Principal {

  private Scanner scan = new Scanner(System.in);

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
          cadastrarArtistas();
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

  private void pesquisarDadosDoArtista() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'pesquisarDadosDoArtista'");
  }

  private void buscarMusicasPorArtista() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'buscarMusicasPorArtista'");
  }

  private void listarMusicas() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'listarMusicas'");
  }

  private void cadastrarArtistas() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'cadastrarArtistas'");
  }
}
