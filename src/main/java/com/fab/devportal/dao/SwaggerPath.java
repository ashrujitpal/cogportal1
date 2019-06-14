package com.fab.devportal.dao;

import java.util.List;
import java.util.Map;

import io.swagger.models.Response;

public class SwaggerPath {
	
	private String path;
	private String operationName;
	private String httpVerb;
	private List<String> tags;
	private List<QueryParam> queryParams;
	private List<PathParam> pathParams;
	private String requestBody;
	private Map<String, Response> responseBody;
	private List<String> consumers;
	private List<String> producers;
	private String responses;
	private String operationDesc;
	
	
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
	public void setRequestBody(String requestBody) {
		this.requestBody = requestBody;
	}
	public Object getResponseBody() {
		return responseBody;
	}
	public void setResponseBody(Map<String, Response> map) {
		this.responseBody = map;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	public List<PathParam> getPathParams() {
		return pathParams;
	}
	public void setPathParams(List<PathParam> pathParams) {
		this.pathParams = pathParams;
	}
	public List<String> getConsumers() {
		return consumers;
	}
	public void setConsumers(List<String> consumers) {
		this.consumers = consumers;
	}
	public List<String> getProducers() {
		return producers;
	}
	public void setProducers(List<String> producers) {
		this.producers = producers;
	}
	public String getResponses() {
		return responses;
	}
	public void setResponses(String responses) {
		this.responses = responses;
	}
	public String getOperationDesc() {
		return operationDesc;
	}
	public void setOperationDesc(String operationDesc) {
		this.operationDesc = operationDesc;
	}
	
	
	
}
