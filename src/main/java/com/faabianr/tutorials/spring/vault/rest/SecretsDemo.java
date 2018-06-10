package com.faabianr.tutorials.spring.vault.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.faabianr.tutorials.spring.vault.model.User;
import com.faabianr.tutorials.spring.vault.service.VaultService;

@RestController
public class SecretsDemo {

	/*
	 * This is mapped to a path in the secrets engine. It means that the
	 * path user-123 needs to be available.
	 * 
	 * You can enable it with:
	 * 
	 * $ vault secrets enable -path=user-123 kv
	 * */
	private static final String TEST_USER_ID = "user-123";

	private VaultService vaultService;
	
	public SecretsDemo(VaultService vaultService) {
		this.vaultService = vaultService;
	}
	
	@RequestMapping(value = "/secrets", method = RequestMethod.GET)
	public User getTestPassword() {
		return vaultService.readSecrets(TEST_USER_ID, User.class);
	}
	
	@RequestMapping(value = "/secrets", method = RequestMethod.POST)
	public String setSecrets(@RequestParam(value="password") String password) {
		User user = new User();
		user.setUserId(TEST_USER_ID);
		vaultService.writeSecrets(TEST_USER_ID, "password", password);
		return "Password updated";
	}
	
}
