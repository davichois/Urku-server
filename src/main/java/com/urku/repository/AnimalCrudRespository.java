package com.urku.repository;

import com.urku.entity.Animal;
import org.springframework.data.repository.CrudRepository;


public interface AnimalCrudRespository extends CrudRepository<Animal, Integer> {

}
