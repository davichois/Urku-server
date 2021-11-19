package com.urku.service;

import com.urku.entity.Clasificacion;
import com.urku.repository.ClasificacionCrudRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClasificacionService {

    @Autowired
    private ClasificacionCrudRespository clasificacionCrudRespository;

    @Transactional(readOnly = true)
    public List<Clasificacion> findAll() {
        return (List<Clasificacion>) clasificacionCrudRespository.findAll();
    }

    public Clasificacion findById(Integer id) {
        return clasificacionCrudRespository.findById(id).orElse(null);
    }

    public Clasificacion createOrUpdate(Clasificacion clasificacion) {
        return clasificacionCrudRespository.save(clasificacion);
    }

    public void delete(Integer id) {
        clasificacionCrudRespository.deleteById(id);
    }

}
