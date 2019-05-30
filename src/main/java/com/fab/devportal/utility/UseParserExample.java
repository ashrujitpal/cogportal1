package com.fab.devportal.utility;
import java.util.Map;
import java.util.Map.Entry;

import io.swagger.models.HttpMethod;
import io.swagger.models.Operation;
import io.swagger.models.Path;
import io.swagger.models.Response;
import io.swagger.models.Swagger;
import io.swagger.models.parameters.Parameter;
import io.swagger.models.properties.Property;
import io.swagger.parser.SwaggerParser;


public class UseParserExample {

  private static Property resProp;

public static void main(String[] args) {
    //Swagger swagger = new SwaggerParser().read("/Users/fab/Downloads/API/card-case.json");
    Swagger swagger = new SwaggerParser().read("https://petstore.swagger.io/v2/swagger.json");
    Map<String, Path> paths = swagger.getPaths();
    for (Map.Entry<String, Path> p : paths.entrySet()) {
      Path path = p.getValue();
      Map<HttpMethod, Operation> operations = path.getOperationMap();
      for (Entry <HttpMethod, Operation> o : operations.entrySet()) {
        System.out.println("===");
        System.out.println("PATH:" + p.getKey());
        System.out.println("Http method:" + o.getKey());
        System.out.println("Summary:" + o.getValue().getSummary());
        System.out.println("Parameters number: " + o.getValue().getParameters().size());
        for (Parameter parameter : o.getValue().getParameters()) {
          System.out.println(" - " + parameter.getName());
        }
        System.out.println("Responses:");
        for (Map.Entry<String, Response> r : o.getValue().getResponses().entrySet()) {
          System.out.println(" - " + r.getKey() + ": " + r.getValue().getDescription());
          
          
          
          
          /*@SuppressWarnings("unchecked")
          Enumeration<String> enums = (Enumeration<String>) resProp.getName());
          while (enums.hasMoreElements()) {
            String key = enums.nextElement();
            String value = resProp.getProperty(key);
            System.out.println(key + " : " + value);
          }*/
        }
        System.out.println("");
      }

    }
  }
} 