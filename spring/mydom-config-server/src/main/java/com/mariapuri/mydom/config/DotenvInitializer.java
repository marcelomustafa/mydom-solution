package com.mariapuri.mydom.config;

import io.github.cdimascio.dotenv.Dotenv;

import java.util.stream.Stream;

public abstract class DotenvInitializer {

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

      System.out.println("🔐 .env => " + entry.getKey() + "=" + entry.getValue());
      System.setProperty(entry.getKey(), entry.getValue());

    });
  }

}
