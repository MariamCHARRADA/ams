package com.sip.ams.dto;

public class ArticleDTO {
	private int id;
	private String libelle;
	private double price;
	private int idProvider;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public double getPrice() {
		return price;
	}
	public ArticleDTO(int id, String libelle, double price, int idProvider) {
		super();
		this.id = id;
		this.libelle = libelle;
		this.price = price;
		this.idProvider = idProvider;
	}
	public ArticleDTO() {
		super();
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getIdProvider() {
		return idProvider;
	}
	public void setIdProvider(int idProvider) {
		this.idProvider = idProvider;
	}

}
