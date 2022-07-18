package com.project.books.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.project.books.model.Item;

public interface BookRepository extends MongoRepository<Item, String>{

}
