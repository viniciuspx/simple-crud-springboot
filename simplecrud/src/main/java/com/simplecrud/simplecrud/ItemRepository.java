package com.simplecrud.simplecrud;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;


public interface ItemRepository extends MongoRepository<Employee, String> {

    @Query("{'_id': ?0}")
    public Employee findById(int serialID);

}
