package com.screensound.screensound;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.screensound.screensound.principal.Principal;
import com.screensound.screensound.repository.ArtistaRepository;

@SpringBootApplication
public class ScreensoundApplication implements CommandLineRunner {

  @Autowired
  private ArtistaRepository artistaRepository;

  public static void main(String[] args) {
    SpringApplication.run(ScreensoundApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    Principal principal = new Principal(artistaRepository);
    principal.exibeMenu();
  }
}
