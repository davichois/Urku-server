package com.urku.service;

import com.urku.entity.Rescatista;
import com.urku.repository.RescatistaCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RescatistaService {

    @Autowired
    private RescatistaCrudRepository rescatistaCrudRepository;

    @Transactional(readOnly = true)
    public List<Rescatista> findAll() {
        return (List<Rescatista>) rescatistaCrudRepository.findAll();
    }

    public Rescatista findById(Integer id) {
        return rescatistaCrudRepository.findById(id).orElse(null);
    }

    public Rescatista createOrUpdate(Rescatista rescatista) {
        return rescatistaCrudRepository.save(rescatista);
    }

    public void delete(Integer id) {
        rescatistaCrudRepository.deleteById(id);
    }

}
