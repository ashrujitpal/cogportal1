package com.fab.devportal.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fab.devportal.dao.SubApiTbl;

public interface SubApiService {
	
	public List<SubApiTbl> findByParentApiName(String parentapiname);
	
	public Iterable<SubApiTbl> findAll();
	
    public Page<SubApiTbl> findAllPageable(Pageable pageable);
	
}
