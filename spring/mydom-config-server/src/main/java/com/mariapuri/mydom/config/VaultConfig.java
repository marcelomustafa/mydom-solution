package com.mariapuri.mydom.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.vault.authentication.SessionManager;
import org.springframework.vault.authentication.SimpleSessionManager;
import org.springframework.vault.authentication.TokenAuthentication;
import org.springframework.vault.client.ClientHttpRequestFactoryFactory;
import org.springframework.vault.client.VaultEndpoint;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.support.ClientOptions;
import org.springframework.vault.support.SslConfiguration;

@Configuration
public class VaultConfig {

  @Bean
  public VaultTemplate vaultTemplate() {

    String vaultHost = System.getProperty("VAULT_HOST");
    int vaultPort = Integer.parseInt(System.getProperty("VAULT_PORT"));
    String vaultToken = System.getProperty("VAULT_TOKEN");

    VaultEndpoint vaultEndpoint = VaultEndpoint.create(vaultHost, vaultPort);
    ClientOptions clientOptions = new ClientOptions();
    SslConfiguration sslConfiguration = SslConfiguration.unconfigured();
    ClientHttpRequestFactory clientHttpRequestFactory = ClientHttpRequestFactoryFactory.create(clientOptions, sslConfiguration);
    SessionManager sessionManager = new SimpleSessionManager(new TokenAuthentication(vaultToken));

    return new VaultTemplate(vaultEndpoint, clientHttpRequestFactory, sessionManager);

  }
}
