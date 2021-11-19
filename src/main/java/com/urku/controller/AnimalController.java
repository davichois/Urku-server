package com.urku.controller;

import com.urku.entity.Animal;
import com.urku.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/animal")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @GetMapping("/")
    public ResponseEntity<?> show() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Animal> animals = animalService.findAll();
            response.put("message", "success");
            response.put("error", "false");
            response.put("body", animals);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        }catch (DataAccessException e){
            response.put("message", "Error al realizar la consulta");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> showOne(@PathVariable("id") Integer id){
        Map<String, Object> response = new HashMap<>();
        try {
            Animal animal = this.animalService.findById(id);

            if (animal == null){
                response.put("message", "El animal con ID: " + id.toString() + " no existe en la Base de datos.");
                response.put("error", "true");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
            }

            response.put("message", "success");
            response.put("error", "false");
            response.put("body", animal);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        } catch (DataAccessException e){
            response.put("message", "Error al realizar la consulta");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    public ResponseEntity<?>  create(@RequestBody Animal animal){
        Map<String, Object> response = new HashMap<>();
        try {

            if (animal == null){
                response.put("message", "more content");
                response.put("error", "true");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NO_CONTENT);
            }

            animalService.createOrUpdate(animal);
            response.put("message", "created animal correct");
            response.put("error", "false");
            response.put("body", animal);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

        }catch (DataAccessException e){
            response.put("message", "Error al realizar la consulta");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Animal update(@RequestBody Animal animal, @PathVariable Integer id) {
        Animal animalCurrent = this.animalService.findById(id);
        animalCurrent.setAniNombre(animal.getAniNombre());
        animalCurrent.setAniObservaciones(animal.getAniObservaciones());
        animalCurrent.setClasificacion(animal.getClasificacion());
        animalCurrent.setGenero(animal.getGenero());
        animalCurrent.setRescatista(animal.getRescatista());
        this.animalService.createOrUpdate(animalCurrent);
        return animalCurrent;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>  delete(@PathVariable("id") Integer id){
        Map<String, Object> response = new HashMap<>();
        try {
            Animal animal = this.animalService.findById(id);

            if (animal == null){
                response.put("message", "El animal con ID: " + id.toString() + " no existe en la Base de datos.");
                response.put("error", "true");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
            }

            animalService.delete(id);
            response.put("message", "animal delete success");
            response.put("error", "false");
            response.put("body", animal);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        }catch (DataAccessException e){
            response.put("message", "Error al realizar la consulta");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

