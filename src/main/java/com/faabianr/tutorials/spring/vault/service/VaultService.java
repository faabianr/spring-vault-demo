package com.faabianr.tutorials.spring.vault.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.vault.core.VaultOperations;
import org.springframework.vault.support.VaultResponseSupport;

@Service
public class VaultService {

	VaultOperations vaultOperations;
	
	@Autowired
	public VaultService(VaultOperations vaultOperations) {
		this.vaultOperations = vaultOperations;
	}
	
	public void writeSecrets(String path, String key, String value) {
		Map<String, String> data = new HashMap<>();
		data.put(key, value);
		
		vaultOperations.write(path, data);
	}
	
	public <T> T readSecrets(String path, Class<T> clazz) {
		VaultResponseSupport<T> response = vaultOperations.read(path, clazz);
		return response.getData();
	}
	
}
