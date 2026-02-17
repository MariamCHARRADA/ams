package com.sip.ams.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity; //What really changes API return status
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sip.ams.entities.Provider;
import com.sip.ams.services.ProviderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("providers")
public class ProviderController {

	@Autowired
	ProviderService providerService;

	@GetMapping("/")
	@Operation(summary = "Fetching all providers") // Mere annotations for Swagger
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "getAllProviders Successs"),
			@ApiResponse(responseCode = "500", description = "Problem fetching providers") })
	public ResponseEntity<List<Provider>> getAllProviders() {
		return new ResponseEntity<>(this.providerService.getAllProviders(), HttpStatus.OK);
	}

	@GetMapping("/{id}") // id is a variable
	@Operation(summary = "Fetching provider by ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Provider is found"),
			@ApiResponse(responseCode = "404", description = "Provider not found") })
	public ResponseEntity<Provider> getProviderById(@PathVariable int id) { // id is retrieved from the path URL

		Optional<Provider> opt = this.providerService.getProviderById(id);

		if (opt.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(opt.get(), HttpStatus.FOUND); // code 302
	}

	@DeleteMapping("/{id}") // id is a variable
	@Operation(summary = "Deleting provider by ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Provider is deleted"),
			@ApiResponse(responseCode = "404", description = "Provider not found") })
	public ResponseEntity<Provider> deleteProviderById(@PathVariable int id) { // id is retrieved from the path URL

		Optional<Provider> opt = this.providerService.getProviderById(id);

		if (opt.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else {
			this.providerService.deleteProviderById(id);
			return new ResponseEntity<>(opt.get(), HttpStatus.OK);
		}
	}

	@PutMapping("/")
	@Operation(summary = "Updating a provider")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Provider is updated successfully"),
			@ApiResponse(responseCode = "404", description = "Provider not found") })
	public ResponseEntity<Provider> updateProvider(@RequestBody Provider provider) {

		Optional<Provider> opt = this.providerService.getProviderById(provider.getId());

		if (opt.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else {
			Provider savedProvider = opt.get();
			savedProvider.setName(provider.getName());
			savedProvider.setEmail(provider.getEmail());
			savedProvider.setAddress(provider.getAddress());

			return new ResponseEntity<>(this.providerService.addProvider(savedProvider), HttpStatus.OK);
		}
	}

	@PostMapping("/")
	@Operation(summary = "Adding new providers")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "new provider created"),
			@ApiResponse(responseCode = "500", description = "Problem adding provider") })
	public ResponseEntity<Provider> addProvider(@RequestBody Provider p) {

		return new ResponseEntity<>(this.providerService.addProvider(p), HttpStatus.CREATED);
	}

}
