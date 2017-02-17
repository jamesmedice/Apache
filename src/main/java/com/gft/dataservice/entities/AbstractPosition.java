package com.gft.dataservice.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

@MappedSuperclass
public abstract class AbstractPosition extends AbstractEntity implements Serializable {
	
    
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="client_id")
	private Client client;
    
    @Column(name = "position_nr")
	private String position;
    
    @Column(name = "article_number")
	private String article;
    
    @Column(name = "short_text")
	private String shortT;
    
    @Column(name = "lang_text")
	private String langT;
    
    @Column(name = "quantity")
	private BigDecimal quantity;
    
    @Column(name = "price_total")
	private BigDecimal price;
    
    @Column(name = "position_factor")
	private BigDecimal factor;
    
    @Column(name = "time")
	private BigDecimal time;

    @Column(name = "formula")
	private String formula;
    
    @Column(name = "result")
	private String result;

	public Client getClient() {
		return client;
	}

	public String getPosition() {
		return position;
	}

	public String getArticle() {
		return article;
	}

	public String getShortT() {
		return shortT;
	}

	public String getLangT() {
		return langT;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public BigDecimal getFactor() {
		return factor;
	}

	public BigDecimal getTime() {
		return time;
	}

	public String getFormula() {
		return formula;
	}

	public String getResult() {
		return result;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public void setArticle(String article) {
		this.article = article;
	}

	public void setShortT(String shortT) {
		this.shortT = shortT;
	}

	public void setLangT(String langT) {
		this.langT = langT;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public void setFactor(BigDecimal factor) {
		this.factor = factor;
	}

	public void setTime(BigDecimal time) {
		this.time = time;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public void setResult(String result) {
		result = result;
	}
}
