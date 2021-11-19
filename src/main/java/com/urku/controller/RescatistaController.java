package com.urku.controller;

import com.urku.entity.Genero;
import com.urku.entity.Rescatista;
import com.urku.service.GeneroService;
import com.urku.service.RescatistaService;
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
@RequestMapping("rescatista")
public class RescatistaController {

    @Autowired
    private RescatistaService rescatistaService;

    @GetMapping("/")
    public ResponseEntity<?> show() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Rescatista> rescatistas = rescatistaService.findAll();
            response.put("message", "success");
            response.put("error", "false");
            response.put("body", rescatistas);
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
            Rescatista rescatista = this.rescatistaService.findById(id);

            if (rescatista == null){
                response.put("message", "El rescatista con ID: " + id.toString() + " no existe en la Base de datos.");
                response.put("error", "true");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
            }

            response.put("message", "success");
            response.put("error", "false");
            response.put("body", rescatista);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        } catch (DataAccessException e){
            response.put("message", "Error al realizar la consulta");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    public ResponseEntity<?>  create(@RequestBody Rescatista rescatista){
        Map<String, Object> response = new HashMap<>();
        try {

            if (rescatista == null){
                response.put("message", "more content");
                response.put("error", "true");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NO_CONTENT);
            }

            rescatistaService.createOrUpdate(rescatista);
            response.put("message", "created genero correct");
            response.put("error", "false");
            response.put("body", rescatista);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

        }catch (DataAccessException e){
            response.put("message", "Error al realizar la consulta");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Rescatista update(@RequestBody Rescatista rescatista, @PathVariable Integer id) {
        Rescatista rescatistaCurrent = this.rescatistaService.findById(id);
        rescatistaCurrent.setResNombre(rescatista.getResNombre());
        this.rescatistaService.createOrUpdate(rescatistaCurrent);
        return rescatistaCurrent;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>  delete(@PathVariable("id") Integer id){
        Map<String, Object> response = new HashMap<>();
        try {
            Rescatista rescatista = this.rescatistaService.findById(id);

            if (rescatista == null){
                response.put("message", "El rescatista con ID: " + id.toString() + " no existe en la Base de datos.");
                response.put("error", "true");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
            }

            rescatistaService.delete(id);
            response.put("message", "genero delete success");
            response.put("error", "false");
            response.put("body", rescatista);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        }catch (DataAccessException e){
            response.put("message", "Error al realizar la consulta");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
