package com.mariapuri.mydom.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.vault.authentication.SessionManager;
import org.springframework.vault.authentication.SimpleSessionManager;
import org.springframework.vault.authentication.TokenAuthentication;
import org.springframework.vault.client.ClientHttpRequestFactoryFactory;
import org.springframework.vault.client.VaultEndpoint;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.support.ClientOptions;
import org.springframework.vault.support.SslConfiguration;

import java.net.URI;
import java.util.stream.Stream;

@SpringBootApplication
@EnableConfigServer
public class MydomConfigServerApplication {

	public static void main(String[] args) {

		Dotenv dotenv = Dotenv.configure()
					.directory(".") // 👈 define que o .env está na raiz
					//.directory("src/main/resources")
					.filename(".env")    // 👈 nome do arquivo, opcional se for .env
					.ignoreIfMissing()   // Evita falha se .env estiver ausente em ambientes de produção
					.load();

		// Configura as variáveis que o Spring deve usar
		dotenv.entries().forEach(entry -> {

			if(Stream.of("VAULT_TOKEN", "GITHUB_USER", "GITHUB_TOKEN", "GITHUB_URI")
						.noneMatch(key -> key.equals(entry.getKey()))) {
				return;
			}

			System.out.println("🔐 .env => " + entry.getKey() + "=" + entry.getValue());
			System.setProperty(entry.getKey(), entry.getValue());

		});

		//Start Application
		SpringApplication.run(MydomConfigServerApplication.class, args);

	}

	@Bean
	public VaultTemplate vaultTemplate() {
		String vaultToken = System.getProperty("VAULT_TOKEN");
		URI uriVault = URI.create("http://localhost:8200");

		VaultEndpoint vaultEndpoint = VaultEndpoint.from(uriVault);
		ClientOptions clientOptions = new ClientOptions();
		SslConfiguration sslConfiguration = SslConfiguration.unconfigured();
		ClientHttpRequestFactory clientHttpRequestFactory = ClientHttpRequestFactoryFactory.create(clientOptions, sslConfiguration);
		SessionManager sessionManager = new SimpleSessionManager(new TokenAuthentication(vaultToken));

		return new VaultTemplate(vaultEndpoint, clientHttpRequestFactory, sessionManager);
	}

}
