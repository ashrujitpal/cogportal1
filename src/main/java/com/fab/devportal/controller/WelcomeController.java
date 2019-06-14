package com.fab.devportal.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.BucketInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fab.devportal.dao.ApiDescTbl;
import com.fab.devportal.dao.Pager;
import com.fab.devportal.dao.PathParam;
import com.fab.devportal.dao.QueryParam;
import com.fab.devportal.dao.SubApiTbl;
import com.fab.devportal.dao.SwaggerPath;
import com.fab.devportal.dao.SwaggerPaths;
import com.fab.devportal.repo.SubAPIRepository;
import com.fab.devportal.service.APIDetailsService;
import com.fab.devportal.service.APIDetailsServiceImpl;
import com.fab.devportal.service.SubApiService;
import com.fab.devportal.service.SubApiServiceImpl;
import com.fab.devportal.utility.SwaggerUtility;

import ch.qos.logback.classic.Logger;
import io.swagger.models.HttpMethod;
import io.swagger.models.Operation;
import io.swagger.models.Path;
import io.swagger.models.Swagger;
import io.swagger.models.parameters.BodyParameter;
import io.swagger.models.parameters.Parameter;
import io.swagger.models.parameters.PathParameter;
import io.swagger.models.parameters.QueryParameter;
import io.swagger.models.properties.Property;
import io.swagger.parser.SwaggerParser;

import io.swagger.inflector.examples.*;
import io.swagger.inflector.examples.models.Example;
import io.swagger.inflector.processors.JsonNodeExampleSerializer;
import io.swagger.util.Json;
import io.swagger.util.Yaml;
import java.util.Map;
import com.fasterxml.jackson.databind.module.SimpleModule;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;

@Controller
@RequestMapping("fabapis")
public class WelcomeController {

    private static final int BUTTONS_TO_SHOW = 6;
    private static final int INITIAL_PAGE = 0;
    private static final int INITIAL_PAGE_SIZE = 6;
    private static final int[] PAGE_SIZES = {5, 10, 20};
    
    @Autowired 
	private SubApiService subApiService;
    
    @Autowired
    private APIDetailsService apiDtlsService;
    
	

   
    
    @GetMapping("/")
    public ModelAndView main(@RequestParam("pageSize") Optional<Integer> pageSize,
            @RequestParam("page") Optional<Integer> page) {
    	
    	ModelAndView modelAndView = new ModelAndView("index");
    	
    	int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
    	
	    int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
	    
	    System.out.println("evalPageSize   "+ evalPageSize);
	    System.out.println("evalPage   "+ evalPage);
	
    	Page<SubApiTbl> subapilist = subApiService.findAllPageable(PageRequest.of(evalPage, evalPageSize));
    	Pager pager = new Pager(subapilist.getTotalPages(), subapilist.getNumber(), BUTTONS_TO_SHOW);
    	
    	modelAndView.addObject("subapilist", subapilist);
        modelAndView.addObject("selectedPageSize", evalPageSize);
        modelAndView.addObject("pageSizes", PAGE_SIZES);
        modelAndView.addObject("pager", pager);
    	
        return modelAndView;
   }
    

    
    @GetMapping("/welcome")
    public String api(Model model) {
        //model.addAttribute("message", message);
        //model.addAttribute("tasks", tasks);

        //return "welcome"; //view
    	return "welcome";
    }

    @GetMapping("/smartbank")
    public String smartBank(Model model) {

        List<SubApiTbl> subApiLists = subApiService.findByParentApiName("Smart Bank");
        
        model.addAttribute("subapilist", subApiLists);

        return "smartbank"; //view
    }
    
    @GetMapping("/openbank")
    public String openBank(Model model) {

        List<SubApiTbl> subApiLists = subApiService.findByParentApiName("Open Bank");
        
        model.addAttribute("subapilist", subApiLists);

        return "openbank"; //view
    }
    
    @PostMapping("/apidetails")
    public String apiDetails(@RequestParam Long id,  Model model) {
    	
    	System.out.println("Id Received from Smart bank page" + id);

        Optional<ApiDescTbl> apiDetailsList =apiDtlsService.findById(id);
        
        model.addAttribute("apidetails", apiDetailsList.get());
        
        System.out.println("apiDetailsList.get()" + apiDetailsList.get().getId());

        return "apidescription"; //view
    }
    
    @PostMapping("/swagger")
    public String swaggerDetails(@RequestParam String swaggerName, Model model) {
    	
    	System.out.println("Swagger Name Received from Smart bank page" + swaggerName);
    	
    	//String path ="https://console.cloud.google.com/storage/browser/ashrujit-gcp-storage/api/bian/swaggers/" + swaggerName;
    	
    	// The name of the bucket to access
    	 String bucketName = "ashrujit-gcp-storage";
    	 
    	 Gson gson = new GsonBuilder().setPrettyPrinting().create(); 

    	// The name of the remote file to download
    	 String srcFilename = "api/bian/swaggers/"+ swaggerName;

    	// The path to which the file should be downloaded
    	// Path destFilePath = Paths.get("api/bian/swaggers/"+ swaggerName);

    	// Instantiate a Google Cloud Storage client
    	Storage storage = StorageOptions.getDefaultInstance().getService();

    	// Get specific file from specified bucket
    	Blob blob = storage.get(BlobId.of(bucketName, srcFilename));
    	
    	byte[] contentInByte = blob.getContent();

    	// Download file to specified path
    	//blob.downloadTo(destFilePath);
    	
    	Swagger swagger = new SwaggerParser().parse(new String(blob.getContent()));
    	
		String description = swagger.getInfo().getDescription();
		
		SwaggerPaths paths = new SwaggerPaths();
		
		List<SwaggerPath> params = new ArrayList<SwaggerPath>();
		
		paths.setSwaggerName(gson.toJson(new String(contentInByte)));
		
		for (Map.Entry<String, Path> entry : swagger.getPaths().entrySet()) {
			
			SwaggerPath swagg = new SwaggerPath();			
						
			for(Entry<HttpMethod, Operation> op : entry.getValue().getOperationMap().entrySet()) {
		
				swagg.setPath(entry.getKey());
				swagg.setHttpVerb(op.getKey().toString());
				swagg.setOperationName(op.getValue().getOperationId());
				swagg.setTags(op.getValue().getTags());
				swagg.setConsumers(op.getValue().getConsumes());
				swagg.setProducers(op.getValue().getProduces());
				swagg.setResponseBody(op.getValue().getResponses());
				swagg.setOperationDesc(op.getValue().getDescription());
				
				
				for (Parameter parameter : op.getValue().getParameters()) {
					
					System.out.println(parameter.getName());
					
					if (parameter instanceof PathParameter) {
												
						 PathParam pparam = new PathParam();
						 pparam.setName(parameter.getName());
						 pparam.setIsRequired(parameter.getRequired());
						 
						 if(null != swagg.getPathParams()) {
							 swagg.getPathParams().add(pparam);
						 }else {
							 swagg.setPathParams(new ArrayList());
							 swagg.getPathParams().add(pparam);
						 }					 
						 
					}
					
					if (parameter instanceof QueryParameter) {
						
						QueryParam pparam = new QueryParam();
						 pparam.setName(parameter.getName());
						 pparam.setIsRequired(parameter.getRequired());
						 
						 if(null != swagg.getQueryParams()) {
							 swagg.getQueryParams().add(pparam);
						 }else {
							 swagg.setQueryParams(new ArrayList());
							 swagg.getQueryParams().add(pparam);
						 }
						
					}
					
					
					if (parameter instanceof BodyParameter) {
			            BodyParameter bp = (BodyParameter) parameter;
			            io.swagger.models.Model schema = bp.getSchema();
			            
			            Map<String,String> exampleMap = bp.getExamples();
			            
			            Map<String, Property> propMap = ((io.swagger.models.Model) schema).getProperties();
			            
			           
			            String json = gson.toJson(propMap); 
			            
			            System.out.println(json);
			            System.out.println(gson.toJson(propMap));
			            
			            swagg.setRequestBody(json);
			            
			            
			            /*for (Entry<String, Property> entry : propMap.entrySet()) {
			                System.out.println(entry.getKey());
			                System.out.println(entry.getValue().getExample());
			                System.out.println(entry.getValue().getDescription());
			                System.out.println(entry.getValue().getType().toString());
			                
			            }*/
					}
				}
				
				params.add(swagg);
				
			}
			
		}
		
		paths.setPaths(params);
		
		model.addAttribute("paths", paths);
		model.addAttribute("description", description);

        return "swagger"; //view
    }

}