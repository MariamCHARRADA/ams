package com.sip.ams.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sip.ams.dto.ArticleDTO;
import com.sip.ams.entities.Article;
import com.sip.ams.entities.Provider;
import com.sip.ams.repositories.ArticleRepository;
import com.sip.ams.repositories.ProviderRepository;

@Service
public class ArticleServiceImp implements ArticleService{
	
	@Autowired
	ArticleRepository articleRepository;
	
	@Autowired
	ProviderRepository providerRepository;

	@Override
	public List<Article> getAllArticles() {
		// TODO Auto-generated method stub
		return (List<Article>)this.articleRepository.findAll();
	}

	@Override
	public Article addArticle(Article article) {
		// TODO Auto-generated method stub
		return this.articleRepository.save(article);
	}

	@Override
	public Optional<Article> getArticleById(int id) {
		// TODO Auto-generated method stub
		return this.articleRepository.findById(id);	}

	@Override
	public void deleteArticleById(int id) {
		// TODO Auto-generated method stub
		this.articleRepository.deleteById(id);
		
	}

	@Override
	public Article updateArticle(ArticleDTO articleDto) {
		
		int idArticle = articleDto.getId();
		Optional<Article> optArticle = this.getArticleById(idArticle);
		Optional<Provider> optProvider = this.providerRepository.findById(articleDto.getIdProvider());

		Article article = optArticle.get();
		Provider provider = optProvider.get();
		
		article.setLibelle(articleDto.getLibelle());
		article.setPrice(articleDto.getPrice());
		article.setProvider(provider);
		return article;
		
	}

}
