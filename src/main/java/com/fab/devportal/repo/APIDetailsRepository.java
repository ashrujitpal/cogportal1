package com.fab.devportal.repo;

import org.springframework.data.repository.CrudRepository;

import com.fab.devportal.dao.ApiDescTbl;
import com.fab.devportal.dao.SubApiTbl;

import java.util.List;
import java.lang.String;


public interface APIDetailsRepository extends CrudRepository<ApiDescTbl, Long> {

	List<ApiDescTbl> findByApiName(String apiname);
	
	
}
