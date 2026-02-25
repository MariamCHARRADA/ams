package com.sip.ams.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sip.ams.dto.ArticleDTO;
import com.sip.ams.entities.Article;
import com.sip.ams.entities.Provider;
import com.sip.ams.services.ArticleService;
import com.sip.ams.services.ProviderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("articles")
@CrossOrigin("*")
public class ArticleController {

	@Autowired // IOC inversion of control = injecting dependencies
	ArticleService articleService;

	@Autowired // IOC inversion of control = injecting dependencies
	ProviderService providerService;

	@GetMapping("/")
	@Operation(summary = "Fetching all articles") // Mere annotations for Swagger
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "getAllProviders Successs"),
			@ApiResponse(responseCode = "500", description = "Problem fetching providers") })
	public ResponseEntity<List<Article>> getAllArticles() {
		return new ResponseEntity<>(this.articleService.getAllArticles(), HttpStatus.OK);
	}

	@PostMapping("/{providerId}")
	@Operation(summary = "Adding new articles")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "new article created"),
			@ApiResponse(responseCode = "500", description = "Problem adding article") })
	public ResponseEntity<Article> addArticle(@RequestBody Article a, @PathVariable int providerId) {

		Optional<Provider> optProvider = this.providerService.getProviderById(providerId);
		if (optProvider.isEmpty())
			return ResponseEntity.notFound().build();
		else {
			Provider provider = optProvider.get();
			a.setProvider(provider);

			return new ResponseEntity<>(this.articleService.addArticle(a), HttpStatus.CREATED);
		}
	}
	

	@DeleteMapping("/{id}") // id is a variable
	@Operation(summary = "Deleting provider by ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Article is deleted"),
			@ApiResponse(responseCode = "404", description = "Article not found") })
	public ResponseEntity<Article> deleteArticleById(@PathVariable int id) { // id is retrieved from the path URL

		Optional<Article> opt = this.articleService.getArticleById(id);

		if (opt.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else {
			this.articleService.deleteArticleById(id);
			return new ResponseEntity<>(opt.get(), HttpStatus.OK);
		}
	}
	
	@PutMapping("/")
	@Operation(summary = "Updating an article")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Article is updated successfully"),
			@ApiResponse(responseCode = "404", description = "Article not found") })
	public ResponseEntity<Article> updateArticle(@RequestBody ArticleDTO articleDto) {

		return new ResponseEntity<>(this.articleService.updateArticle(articleDto), HttpStatus.CREATED);
	
	}

}
