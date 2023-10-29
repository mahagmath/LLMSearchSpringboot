package com.maha.mongo.contoller;

import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.maha.mongo.model.Customer;
import com.maha.mongo.model.request.SearchQueryRequestbody;
import com.maha.mongo.model.response.PromptResponse;
import com.maha.mongo.service.SearchService;
import com.mongodb.client.MongoCursor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
@RestController
@RequestMapping(value = "api/search")
@Slf4j
public class SearchContoller {

	@Autowired
	SearchService service;

    @PostMapping("v1/{uuid}")
    public ResponseEntity <?> searchUUID(@Valid @PathVariable String uuid) {	
    	ResponseEntity <?> result = service.findOneDocument(uuid);
    	if(result != null)
    		System.out.println("inside searchUUID() method Found : "+result);
    	return result;
        //return ResponseEntity.ok().body(result);
    	
    }
    @PostMapping(value = "v1/q", produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> searchperPrompt1( @RequestParam int limit,@NotNull @RequestBody SearchQueryRequestbody reqbody ) {
    	//Informative the prompt?
    	
    	if(reqbody != null && reqbody.getPrompt() != null ) {
    		String prompt = reqbody.getPrompt().toLowerCase();
    		if(!(prompt.contains("search") || prompt.contains("find"))) {
    			log.debug("Prompt NOT Good ..");
    			PromptResponse pojo = new PromptResponse();
    			pojo.setCode("101");
    			pojo.setCategory("prompt_not_clear");
    			pojo.setDescription("Revise the prompt please");
    			//("101","prompt","Revise prompt");
    			
    			ObjectMapper mapper = new ObjectMapper();
    			mapper.enable(SerializationFeature.INDENT_OUTPUT);
    			String jsonObj = null;
				try {
					jsonObj = mapper.writeValueAsString(pojo);
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

    			return ResponseEntity.ok(jsonObj);
    		}
    	}
    	
    	return ResponseEntity.ok(service.searchPattern1(limit, reqbody));
     
   
    }
    @PostMapping(value = "v2/q", produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> searchperPrompt2( @RequestParam int limit,@NotNull @RequestBody SearchQueryRequestbody reqbody ) {
    	return ResponseEntity. ok(service.searchPattern2(limit, reqbody));
     
   
    }
    @PostMapping(value = "v3/q", produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> searchperPrompt3( @RequestParam int limit,@NotNull @RequestBody SearchQueryRequestbody reqbody ) {
    	return ResponseEntity. ok(service.searchPattern3(limit, reqbody));
     
   
    }
}
