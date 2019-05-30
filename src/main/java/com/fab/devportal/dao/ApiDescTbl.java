package com.fab.devportal.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ApiDescTbl {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	private String parentApiName;
	private String swaggerName;
    private String apiName;
    private String apiDescription;
    private String apiVersion;
    private String apiOwner;
    private String apiStatus;
    private String apiDevportalLink;
    private String keyFeature;
    private String keyFeature1;
    private String keyFeature2;
    private String keyFeature3;
    private String keyFeature4;
      
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getApiName() {
		return apiName;
	}
	public void setApiName(String apiName) {
		this.apiName = apiName;
	}
	public String getApiDescription() {
		return apiDescription;
	}
	public void setApiDesc(String apiDesc) {
		this.apiDescription = apiDesc;
	}
	public String getApiVersion() {
		return apiVersion;
	}
	public void setApiVersion(String apiVersion) {
		this.apiVersion = apiVersion;
	}
	public String getApiOwner() {
		return apiOwner;
	}
	public void setApiOwner(String apiOwner) {
		this.apiOwner = apiOwner;
	}
	public String getApiStatus() {
		return apiStatus;
	}
	public void setApiStatus(String apiStatus) {
		this.apiStatus = apiStatus;
	}
	public String getApiDevportalLink() {
		return apiDevportalLink;
	}
	public void setApiDevportalLink(String apiDevportalLink) {
		this.apiDevportalLink = apiDevportalLink;
	}
	
	public String getKeyFeature() {
		return keyFeature;
	}
	public void setKeyFeature(String keyFeature) {
		this.keyFeature = keyFeature;
	}
	public void setApiDescription(String apiDescription) {
		this.apiDescription = apiDescription;
	}
	public String getKeyFeature1() {
		return keyFeature1;
	}
	public void setKeyFeature1(String keyFeature1) {
		this.keyFeature1 = keyFeature1;
	}
	public String getKeyFeature2() {
		return keyFeature2;
	}
	public void setKeyFeature2(String keyFeature2) {
		this.keyFeature2 = keyFeature2;
	}
	public String getKeyFeature3() {
		return keyFeature3;
	}
	public void setKeyFeature3(String keyFeature3) {
		this.keyFeature3 = keyFeature3;
	}
	public String getKeyFeature4() {
		return keyFeature4;
	}
	public void setKeyFeature4(String keyFeature4) {
		this.keyFeature4 = keyFeature4;
	}
	public String getParentApiName() {
		return parentApiName;
	}
	public void setParentApiName(String parentApiName) {
		this.parentApiName = parentApiName;
	}
	public String getSwaggerName() {
		return swaggerName;
	}
	public void setSwaggerName(String swaggerName) {
		this.swaggerName = swaggerName;
	}
	
	
    
}
