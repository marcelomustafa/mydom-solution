package com.mariapuri.mydom.config;

import io.github.cdimascio.dotenv.Dotenv;
import lombok.extern.slf4j.Slf4j;

import java.util.stream.Stream;

@Slf4j
public abstract class DotenvInitializer {

  // Private constructor to prevent instantiation
  private DotenvInitializer() {
    throw new UnsupportedOperationException("Utility class");
  }


  public static void init() {
    Dotenv dotenv = Dotenv.configure()
          .directory(".") // 👈 define que o .env está na raiz
          //.directory("src/main/resources")
          .filename(".env")    // 👈 nome do arquivo, opcional se for .env
          .ignoreIfMissing()   // Evita falha se .env estiver ausente em ambientes de produção
          .load();

    // Configura as variáveis que o Spring deve usar
    dotenv.entries().forEach(entry -> {

      if(Stream.of("VAULT_HOST", "VAULT_PORT", "VAULT_TOKEN", "GITHUB_USER", "GITHUB_TOKEN", "GITHUB_URI")
            .noneMatch(key -> key.equals(entry.getKey()))) {
        return;
      }

      log.debug("🔐 .env => {}={}", entry.getKey(), entry.getValue());
      System.setProperty(entry.getKey(), entry.getValue());

    });
  }

}
