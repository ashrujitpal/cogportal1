package com.fab.devportal.dao;

import java.util.List;

public class SwaggerPath {
	
	private String path;
	private String operationName;
	private String httpVerb;
	
	private List<QueryParam> queryParams;
	private Object requestBody;
	private Object responseBody;
	
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getOperationName() {
		return operationName;
	}
	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}
	public String getHttpVerb() {
		return httpVerb;
	}
	public void setHttpVerb(String httpVerb) {
		this.httpVerb = httpVerb;
	}
	public List<QueryParam> getQueryParams() {
		return queryParams;
	}
	public void setQueryParams(List<QueryParam> queryParams) {
		this.queryParams = queryParams;
	}
	public Object getRequestBody() {
		return requestBody;
	}
	public void setRequestBody(Object requestBody) {
		this.requestBody = requestBody;
	}
	public Object getResponseBody() {
		return responseBody;
	}
	public void setResponseBody(Object responseBody) {
		this.responseBody = responseBody;
	}
	
	

}
