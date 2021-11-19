package com.urku.service;

import com.urku.entity.Genero;
import com.urku.repository.GeneroCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GeneroService {

    @Autowired
    private GeneroCrudRepository generoCrudRepository;

    @Transactional(readOnly = true)
    public List<Genero> findAll() {
        return (List<Genero>) generoCrudRepository.findAll();
    }

    public Genero findById(Integer id) {
        return generoCrudRepository.findById(id).orElse(null);
    }

    public Genero createOrUpdate(Genero genero) {
        return generoCrudRepository.save(genero);
    }

    public void delete(Integer id) {
        generoCrudRepository.deleteById(id);
    }

}
