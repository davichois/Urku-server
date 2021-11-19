package com.urku.controller;

import com.urku.entity.Genero;
import com.urku.service.GeneroService;
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
@RequestMapping("genero")
public class GeneroController {

    @Autowired
    private GeneroService generoService;

    @GetMapping("/")
    public ResponseEntity<?> show() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Genero> generos = generoService.findAll();
            response.put("message", "success");
            response.put("error", "false");
            response.put("body", generos);
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
            Genero genero = this.generoService.findById(id);

            if (genero == null){
                response.put("message", "El genero con ID: " + id.toString() + " no existe en la Base de datos.");
                response.put("error", "true");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
            }

            response.put("message", "success");
            response.put("error", "false");
            response.put("body", genero);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        } catch (DataAccessException e){
            response.put("message", "Error al realizar la consulta");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    public ResponseEntity<?>  create(@RequestBody Genero genero){
        Map<String, Object> response = new HashMap<>();
        try {

            if (genero == null){
                response.put("message", "more content");
                response.put("error", "true");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NO_CONTENT);
            }

            generoService.createOrUpdate(genero);
            response.put("message", "created genero correct");
            response.put("error", "false");
            response.put("body", genero);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

        }catch (DataAccessException e){
            response.put("message", "Error al realizar la consulta");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Genero update(@RequestBody Genero genero, @PathVariable Integer id) {
        Genero generoCurrent = this.generoService.findById(id);
        generoCurrent.setGenTipo(genero.getGenTipo());
        this.generoService.createOrUpdate(generoCurrent);
        return generoCurrent;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>  delete(@PathVariable("id") Integer id){
        Map<String, Object> response = new HashMap<>();
        try {
            Genero genero = this.generoService.findById(id);

            if (genero == null){
                response.put("message", "El genero con ID: " + id.toString() + " no existe en la Base de datos.");
                response.put("error", "true");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
            }

            generoService.delete(id);
            response.put("message", "genero delete success");
            response.put("error", "false");
            response.put("body", genero);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        }catch (DataAccessException e){
            response.put("message", "Error al realizar la consulta");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
