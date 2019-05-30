package com.fab.devportal.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SubApiTbl {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String parentApiName;
    private String apiName;
    private String apiDescripton;
    private String swaggerName;
    private String apiImageUrl;
    private String apiPageUrl;
    private String apiDevportalLink;
      
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getParentApiName() {
		return parentApiName;
	}
	public void setParentApiName(String parentApiName) {
		this.parentApiName = parentApiName;
	}
	public String getApiName() {
		return apiName;
	}
	public void setApiName(String apiName) {
		this.apiName = apiName;
	}
	public String getApiDescripton() {
		return apiDescripton;
	}
	public void setApiDescripton(String apiDescripton) {
		this.apiDescripton = apiDescripton;
	}
	public String getSwaggerName() {
		return swaggerName;
	}
	public void setSwaggerName(String swaggerName) {
		this.swaggerName = swaggerName;
	}
	public String getApiImageUrl() {
		return apiImageUrl;
	}
	public void setApiImageUrl(String apiImageUrl) {
		this.apiImageUrl = apiImageUrl;
	}
	public String getApiPageUrl() {
		return apiPageUrl;
	}
	public void setApiPageUrl(String apiPageUrl) {
		this.apiPageUrl = apiPageUrl;
	}
	public String getApiDevportalLink() {
		return apiDevportalLink;
	}
	public void setApiDevportalLink(String apiDevportalLink) {
		this.apiDevportalLink = apiDevportalLink;
	}

    
    
    
}
