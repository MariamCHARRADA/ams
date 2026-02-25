package com.sip.ams.services;

import java.util.List;
import java.util.Optional;

import com.sip.ams.dto.ArticleDTO;
import com.sip.ams.entities.Article;

public interface ArticleService { //CRUD
	public List<Article> getAllArticles(); //SELECT
	public Article addArticle(Article a); //UPDATE
	public Optional<Article> getArticleById(int id); //SELECT
	public void deleteArticleById(int id); //DELETE
	public Article updateArticle(ArticleDTO articleDto);

}
