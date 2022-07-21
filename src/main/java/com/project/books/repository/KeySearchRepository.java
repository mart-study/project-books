package com.project.books.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.project.books.model.KeySearch;

public interface KeySearchRepository extends MongoRepository<KeySearch, String> {

}
