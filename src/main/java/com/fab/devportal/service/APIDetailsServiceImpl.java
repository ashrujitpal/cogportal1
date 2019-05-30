package com.fab.devportal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fab.devportal.dao.ApiDescTbl;
import com.fab.devportal.repo.APIDetailsRepository;

@Service
public class APIDetailsServiceImpl implements APIDetailsService{
	
	@Autowired
    private APIDetailsRepository repository;
	
	
	public List<ApiDescTbl> findByApiName(String apiname){
		
		return repository.findByApiName(apiname);
		
	}
	
	public Optional<ApiDescTbl> findById(Long id){
			
			return repository.findById(id);
			
	}

}
