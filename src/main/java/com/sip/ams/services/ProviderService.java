package com.sip.ams.services;

import com.sip.ams.entities.Provider;
import java.util.List;
import java.util.Optional;

public interface ProviderService {
	public List<Provider> getAllProviders();
	public Provider addProvider(Provider provider);
	public Optional<Provider> getProviderById(int id);
	public void deleteProviderById(int id);

}
