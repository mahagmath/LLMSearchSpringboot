package com.maha.mongo.service;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.and;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.maha.mongo.model.Customer;
import com.maha.mongo.model.request.SearchQueryRequestbody;
import com.maha.mongo.repository.CustomerRepository;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import java.util.concurrent.TimeUnit;

import com.mongodb.ConnectionString;
import com.mongodb.client.FindIterable;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
@RequiredArgsConstructor
public class SearchService {
	@Autowired
	CustomerRepository repo;

	public Optional<Customer> searchbyId(String id) {
		
		return repo.findById(id);
	}

	public Optional<Customer> searchbyId(SearchQueryRequestbody reqbody) {
		return repo.findById("");
	}

	public String searchPattern1(Integer limit, SearchQueryRequestbody reqbody) {
	
		/*
		 * Requires the MongoDB Java Driver.
		 * https://mongodb.github.io/mongo-java-driver
		 */
		ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017/");
		MongoClient mongoClient = MongoClients.create(connectionString);
		
		//MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017/"));
		MongoDatabase database = mongoClient.getDatabase("test");
		MongoCollection<Document> collection = database.getCollection("customer");
		Bson filter = eq("lastName", "Beauford");
		Bson project = eq("firstName", 1L);
		FindIterable<Document> result = collection.find(filter).projection(project);
		List<String> list = new ArrayList<String>(); 
		MongoCursor<Document> cursor = result.iterator();
		while (cursor.hasNext()) {
			Document doc = cursor.next();
		    System.out.println("Search Prompt :"+doc);
		    list.add(doc.toJson());
		}
		return list.toString();
	}
	
	public String searchPattern2(Integer limit, SearchQueryRequestbody reqbody) {
		
		/*
		 * Requires the MongoDB Java Driver.
		 * https://mongodb.github.io/mongo-java-driver
		 */
		ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017/");
		MongoClient mongoClient = MongoClients.create(connectionString);
		
		//MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017/"));
		MongoDatabase database = mongoClient.getDatabase("test");
		MongoCollection<Document> collection = database.getCollection("customer");
		Bson filter = eq("lastName", "Matthews");
		Bson project = eq("firstName", 1L);
		FindIterable<Document> result = collection.find(filter).projection(project);
		List<String> list = new ArrayList<String>(); 
		MongoCursor<Document> cursor = result.iterator();
		while (cursor.hasNext()) {
			Document doc = cursor.next();
		    System.out.println("Search Prompt :"+doc);
		    list.add(doc.toJson());
		}
		return list.toString();
	}
	public String searchPattern3(Integer limit, SearchQueryRequestbody reqbody) {
		
		/*
		 * Requires the MongoDB Java Driver.
		 * https://mongodb.github.io/mongo-java-driver
		 */
		ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017/");
		MongoClient mongoClient = MongoClients.create(connectionString);
		
		//MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017/"));
		MongoDatabase database = mongoClient.getDatabase("test");
		MongoCollection<Document> collection = database.getCollection("customer");
		Bson filter = eq("lastName", "Matthews");
		Bson project = Projections.fields(Projections.include("firstName"),Projections.include("lastName"), Projections.excludeId());
		FindIterable<Document> result = collection.find(filter).projection(project);
		List<String> list = new ArrayList<String>(); 
		MongoCursor<Document> cursor = result.iterator();
		while (cursor.hasNext()) {
			Document doc = cursor.next();
		    System.out.println("Search Prompt :"+doc);
		    list.add(doc.toJson());
		}
		return list.toString();
	}

	/*
	 *
	 *Find first single document from a collection
	 *
	*/
	public ResponseEntity  findOneDocument(String id) {
		Optional<Customer> data = repo.findById(id);
		return data.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
		// MongoCollection<Customer> collection;
		//Bson query = collection.find(Filters.and( Filters.eq("account_type","savings"))).first();
	   	//try (MongoCursor<Customer> cursor = collection.find(query).iterator()) {
	   	//	while (cursor.hasNext()) {
	   		
	   	//	      log.info("Found document metadata : "+cursor.next().toJson());
	   	//	}
	   	//    return cursor;
	   	
	}
	
	
}
