package com.mariapuri.mydom.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.vault.core.VaultKeyValueOperations;
import org.springframework.vault.core.VaultKeyValueOperationsSupport;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.support.VaultResponse;

import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class VaultEnvironmentValidator {


  private final VaultTemplate vaultTemplate;
  private final Environment env;

  @PostConstruct
  public void validate() {

    VaultKeyValueOperations kvOps = vaultTemplate.opsForKeyValue("secret", VaultKeyValueOperationsSupport.KeyValueBackend.KV_2);
    VaultResponse response = kvOps.get("mydom-auth-server-dev");

    if (response != null && response.getData() != null) {

      Map<String, Object> secrets = response.getData();

      secrets.forEach((key, value) -> {
        if (value != null) {
          System.setProperty(key, value.toString());
          log.info("🔐 [Vault] Segredo carregado: {} = {}", key, value);
        }
      });

    }

    String secret = env.getProperty("vault.test.secret", "not-found");
    log.info("🔐 [Vault] Secret via Environment: {}", secret);

  }

}