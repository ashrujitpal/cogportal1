package com.fab.devportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
import io.swagger.parser.SwaggerParser;

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
	private List<SwaggerPath> params;

   
    
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
    	
    	String path ="/Users/fab/Downloads/API/" + swaggerName;
    	
    	Swagger swagger = new SwaggerParser().read(path);
    	
		System.out.println(swagger.getInfo().getDescription());
		
		String description = swagger.getInfo().getDescription();
		
		
		SwaggerPaths paths = new SwaggerPaths();
		params = new ArrayList();

		for (Map.Entry<String, Path> entry : swagger.getPaths().entrySet()) {
			
			System.out.println(entry.getKey());
			
			SwaggerPath swagg = new SwaggerPath();
			
			for(Entry<HttpMethod, Operation> op : entry.getValue().getOperationMap().entrySet()) {
				
				System.out.println(op.getKey() + " - " + op.getValue().getOperationId());
				
				swagg.setPath(entry.getKey());
				swagg.setHttpVerb(op.getKey().toString());
				swagg.setOperationName(op.getValue().getOperationId());
				
				params.add(swagg);
				
			}
			
		}
		
		paths.setPaths(params);
		
		model.addAttribute("paths", paths);
		model.addAttribute("description", description);

        return "swagger"; //view
    }

}