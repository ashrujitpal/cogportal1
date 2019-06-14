package com.fab.devportal.dao;

import java.util.List;

public class SwaggerPaths {
	
	private String swaggerName;
	
	
	public String getSwaggerName() {
		return swaggerName;
	}

	public void setSwaggerName(String swaggerName) {
		this.swaggerName = swaggerName;
	}

	private List<SwaggerPath> paths;

	public List<SwaggerPath> getPaths() {
		return paths;
	}

	public void setPaths(List<SwaggerPath> paths) {
		this.paths = paths;
	}

	
}
