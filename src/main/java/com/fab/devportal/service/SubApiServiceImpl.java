package com.fab.devportal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fab.devportal.dao.SubApiTbl;
import com.fab.devportal.repo.SubAPIRepository;

@Service
public class SubApiServiceImpl implements SubApiService{
	
	@Autowired
    private SubAPIRepository repository;
	
	public List<SubApiTbl> findByParentApiName(String parentapiname){
		
		return repository.findByParentApiName(parentapiname);
		
	}
	
	public Iterable<SubApiTbl> findAll(){
		
		return (Iterable<SubApiTbl>) repository.findAll();
		
	}

	@Override
	public Page<SubApiTbl> findAllPageable(Pageable pageable) {
		// TODO Auto-generated method stub
		return repository.findAll(pageable);
	}

}
