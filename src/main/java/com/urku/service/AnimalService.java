package com.urku.service;

import com.urku.entity.Animal;
import com.urku.repository.AnimalCrudRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AnimalService {

    @Autowired
    private AnimalCrudRespository animalCrudRespository;

    @Transactional(readOnly = true)
    public List<Animal> findAll() {
        return (List<Animal>) animalCrudRespository.findAll();
    }

    public Animal findById(Integer id) {
        return animalCrudRespository.findById(id).orElse(null);
    }

    public Animal createOrUpdate(Animal animal) {
        return animalCrudRespository.save(animal);
    }

    public void delete(Integer id) {
        animalCrudRespository.deleteById(id);
    }
}
