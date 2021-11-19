package com.urku.controller;

import com.urku.entity.Clasificacion;
import com.urku.service.ClasificacionService;
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
@RequestMapping("clasificacion")
public class ClasificacionController {

    @Autowired
    private ClasificacionService clasificacionService;

    @GetMapping("/")
    public ResponseEntity<?> show() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Clasificacion> clasificacions = clasificacionService.findAll();
            response.put("message", "success");
            response.put("error", "false");
            response.put("body", clasificacions);
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
            Clasificacion clasificacion = this.clasificacionService.findById(id);

            if (clasificacion == null){
                response.put("message", "La clasificacion con ID: " + id.toString() + " no existe en la Base de datos.");
                response.put("error", "true");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
            }

            response.put("message", "success");
            response.put("error", "false");
            response.put("body", clasificacion);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        } catch (DataAccessException e){
            response.put("message", "Error al realizar la consulta");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    public ResponseEntity<?>  create(@RequestBody Clasificacion clasificacion){
        Map<String, Object> response = new HashMap<>();
        try {

            if (clasificacion == null){
                response.put("message", "more content");
                response.put("error", "true");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NO_CONTENT);
            }

            clasificacionService.createOrUpdate(clasificacion);
            response.put("message", "created clasificacion correct");
            response.put("error", "false");
            response.put("body", clasificacion);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

        }catch (DataAccessException e){
            response.put("message", "Error al realizar la consulta");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Clasificacion update(@RequestBody Clasificacion clasificacion, @PathVariable Integer id) {
        Clasificacion clasificacionCurrent = this.clasificacionService.findById(id);
        clasificacionCurrent.setClasTipo(clasificacion.getClasTipo());
        this.clasificacionService.createOrUpdate(clasificacionCurrent);
        return clasificacionCurrent;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>  delete(@PathVariable("id") Integer id){
        Map<String, Object> response = new HashMap<>();
        try {
            Clasificacion clasificacion = this.clasificacionService.findById(id);

            if (clasificacion == null){
                response.put("message", "La clasificacion con ID: " + id.toString() + " no existe en la Base de datos.");
                response.put("error", "true");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
            }

            clasificacionService.delete(id);
            response.put("message", "clasificacion delete success");
            response.put("error", "false");
            response.put("body", clasificacion);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        }catch (DataAccessException e){
            response.put("message", "Error al realizar la consulta");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
