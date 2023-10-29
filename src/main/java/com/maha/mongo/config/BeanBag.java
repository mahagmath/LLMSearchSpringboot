package com.maha.mongo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.maha.mongo.repository.CustomerRepository;
import com.maha.mongo.service.SearchService;

@Configuration
public class BeanBag {
	
	
	final CustomerRepository repository = null;
	

    final SearchService searvice = new SearchService();

}
