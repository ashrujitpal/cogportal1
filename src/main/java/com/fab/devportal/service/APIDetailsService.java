package com.fab.devportal.service;

import java.util.List;
import java.util.Optional;

import com.fab.devportal.dao.ApiDescTbl;

public interface APIDetailsService {
	
	public List<ApiDescTbl> findByApiName(String apiname);
	public Optional<ApiDescTbl> findById(Long id);

}
