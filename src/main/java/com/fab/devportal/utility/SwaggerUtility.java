package com.fab.devportal.utility;

import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;

import io.swagger.models.HttpMethod;
import io.swagger.models.Model;
import io.swagger.models.Operation;
import io.swagger.models.Path;
import io.swagger.models.Response;
import io.swagger.models.Swagger;
import io.swagger.models.parameters.BodyParameter;
import io.swagger.models.parameters.Parameter;
import io.swagger.models.properties.ArrayProperty;
import io.swagger.models.properties.Property;
import io.swagger.models.properties.RefProperty;
import io.swagger.models.refs.RefFormat;
import io.swagger.parser.SwaggerParser;


public class SwaggerUtility {
	

	public static void main(String[] args) {
		
		Swagger swagger = new SwaggerParser().read("/Users/fab/Downloads/API/card-case.json");
		System.out.println(swagger.getInfo().getDescription());
		
		String path = "";
		
		
		for (Map.Entry<String, Path> entry : swagger.getPaths().entrySet()) {
			
			System.out.println(entry.getKey());
			
			getOperations(swagger, entry.getValue().getOperationMap(), entry.getKey());
			
			for(Entry<HttpMethod, Operation> op : entry.getValue().getOperationMap().entrySet()) {
				
				System.out.println(op.getKey() + " - " + op.getValue().getOperationId());
				
				path= entry.getKey() + ':' + op.getKey() + ':' + op.getValue().getOperationId();
				
			}
			
		}
	
	}
	
   private static void getOperations(Swagger swagger, Map<HttpMethod, Operation> operationMap, String path) {
		
		for(Entry<HttpMethod, Operation> op : operationMap.entrySet()) {
			
			
			
			System.out.println(op.getKey() + " - " + op.getValue().getOperationId());
			System.out.println("Parameters: ");
			
			for (Parameter parameter : op.getValue().getParameters()) {
				
				System.out.println(parameter.getName() + ", ");
				
				if (parameter instanceof BodyParameter) {
		            BodyParameter bp = (BodyParameter) parameter;
		            Model schema = bp.getSchema();
		            
		            Map<String,String> exampleMap = bp.getExamples();
		            
		            Map<String, Property> propMap = schema.getProperties();
		            
		            for (Entry<String, Property> entry : propMap.entrySet()) {
		                System.out.println(entry.getKey());
		                System.out.println(entry.getValue().getExample());
		                System.out.println(entry.getValue().getDescription());
		                System.out.println(entry.getValue().getType().toString());
		                
		            }
				}
			}
			
			
//			System.out.println("");
//			printResponses(swagger, op.getValue().getResponses() );
//			System.out.println();
			
		}
	}
	
	private static void printOperations(Swagger swagger, Map<HttpMethod, Operation> operationMap) {
		
		for(Entry<HttpMethod, Operation> op : operationMap.entrySet()) {
			
			System.out.println(op.getKey() + " - " + op.getValue().getOperationId());
			System.out.println("Parameters: ");
			
			for (Parameter parameter : op.getValue().getParameters()) {
				
				System.out.println(parameter.getName() + ", ");
				
				if (parameter instanceof BodyParameter) {
		            BodyParameter bp = (BodyParameter) parameter;
		            Model schema = bp.getSchema();
		            
		            Map<String,String> exampleMap = bp.getExamples();
		            
		            Map<String, Property> propMap = schema.getProperties();
		            
		            for (Entry<String, Property> entry : propMap.entrySet()) {
		                System.out.println(entry.getKey());
		                System.out.println(entry.getValue().getExample());
		                System.out.println(entry.getValue().getDescription());
		                System.out.println(entry.getValue().getType().toString());
		                
		            }
				}
			}
			
			
//			System.out.println("");
//			printResponses(swagger, op.getValue().getResponses() );
//			System.out.println();
			
		}
	}
	
	
	@Autowired
	private static void printResponses(Swagger swagger, Map<String, Response> responseMap) {
		
		System.out.println("Responses:");
		for(Map.Entry<String, Response> response : responseMap.entrySet()) {
			System.out.println(response.getKey() + ": " + response.getValue().getDescription());
			
			if(response.getValue().getSchema() instanceof ArrayProperty) {
				
				ArrayProperty ap = (ArrayProperty) response.getValue().getSchema();
				if(ap.getItems() instanceof RefProperty) {
					RefProperty rp = (RefProperty) ap.getItems();
					printReference(swagger, rp);
				}
				
			}
		}
	}
	
	
	private static void printReference(Swagger swagger, RefProperty rp) {
		if(rp.getRefFormat() .equals(RefFormat.INTERNAL) &&
				swagger.getDefinitions().containsKey(rp.getSimpleRef())) {
			Model m = swagger.getDefinitions().get(rp.getSimpleRef());
			for(Map.Entry<String, Property> propertyEntry : m.getProperties().entrySet()) {
				System.out.println("  " + propertyEntry.getKey() + " : " + propertyEntry.getValue().getType()	);
			}
			
		}
	}
	
	
}
