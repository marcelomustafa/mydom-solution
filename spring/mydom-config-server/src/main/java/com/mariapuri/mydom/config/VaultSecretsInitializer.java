package com.mariapuri.mydom.config;

import lombok.extern.slf4j.Slf4j;


@Slf4j
//@Component
//@Configuration
public class VaultSecretsInitializer {
      //implements ApplicationContextInitializer<ConfigurableApplicationContext> {

  //@Override
  //@Bean
//  public VaultTemplate vaultTemplate() {


//    String vaultHost = System.getProperty("VAULT_HOST");
//    int vaultPort = Integer.parseInt(System.getProperty("VAULT_PORT"));
//    String vaultToken = System.getProperty("VAULT_TOKEN");
//
//    VaultEndpoint vaultEndpoint = VaultEndpoint.create(vaultHost, vaultPort);
//    ClientOptions clientOptions = new ClientOptions();
//    SslConfiguration sslConfiguration = SslConfiguration.unconfigured();
//    ClientHttpRequestFactory clientHttpRequestFactory = ClientHttpRequestFactoryFactory.create(clientOptions, sslConfiguration);
//    SessionManager sessionManager = new SimpleSessionManager(new TokenAuthentication(vaultToken));
//
//
//    VaultTemplate vaultTemplate = new VaultTemplate(vaultEndpoint, clientHttpRequestFactory, sessionManager);
//    //Environment env = context.getBean(Environment.class);
//
//    VaultKeyValueOperations kvOps = vaultTemplate.opsForKeyValue("secret", VaultKeyValueOperationsSupport.KeyValueBackend.KV_2);
//    VaultResponse response = kvOps.get("mydom-auth-server-dev");
//
//    if (response != null && response.getData() != null) {
//
//      Map<String, Object> secrets = response.getData();
//
//      secrets.forEach((key, value) -> {
//        if (value != null) {
//          System.setProperty(key, value.toString());
//          log.info("🔐 [Vault] Segredo carregado: {} = {}", key, value);
//        }
//      });
//
//    }
//
//    //String secret = env.getProperty("vault.test.secret", "not-found");
//    //log.info("🔐 [Vault] Secret via Environment: {}", secret);
//
//    return vaultTemplate;
//  }
}
