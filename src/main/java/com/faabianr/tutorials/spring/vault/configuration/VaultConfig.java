package com.faabianr.tutorials.spring.vault.configuration;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.context.annotation.Configuration;
import org.springframework.vault.authentication.ClientAuthentication;
import org.springframework.vault.authentication.TokenAuthentication;
import org.springframework.vault.client.VaultEndpoint;
import org.springframework.vault.config.AbstractVaultConfiguration;

@Configuration
public class VaultConfig extends AbstractVaultConfiguration {

	/**
	 * This example works with a local instance of Vault. You can start yours using the 
	 * official docker image (https://hub.docker.com/_/vault/): 
	 * 
	 * $ docker run --cap-add=IPC_LOCK -d -p 8200:8200 -e VAULT_DEV_ROOT_TOKEN_ID=secrettoken123 --name=dev-vault vault
	 * */
	private static final String VAULT_URI = "http://127.0.0.1:8200";
	
	
	/**
	 * This is the token used for starting the local vault instance (in above's example).
	 * */
	private static final String VAULT_AUTH_TOKEN = "secrettoken123";
	
	@Override
	public VaultEndpoint vaultEndpoint() {
		try {
			return VaultEndpoint.from(new URI(VAULT_URI));
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ClientAuthentication clientAuthentication() {
		return new TokenAuthentication(VAULT_AUTH_TOKEN);
	}

}
