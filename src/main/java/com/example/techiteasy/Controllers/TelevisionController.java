package com.example.techiteasy.Controllers;

import com.example.techiteasy.exceptions.RecordNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import service.Television;

import java.net.URI;
import java.sql.ClientInfoStatus;
import java.util.ArrayList;
import java.util.List;


@RestController
    public class TelevisionController {
    List<Television> Televisions = new ArrayList<>();

    @GetMapping("/televisions/{id}")
    public ResponseEntity<Object> getOneTelevision(@PathVariable int id) {

        if (id < Televisions.size()) {
            Television television = Televisions.get(id);
            return ResponseEntity.ok(television);
        } else {
            throw new RecordNotFoundException("id not found");
        }
    }

    @GetMapping("/televisions")
    public ResponseEntity<Object> getTelevisions() {
        return ResponseEntity.ok(Televisions);
    }

    @PostMapping("/televisions")
    public ResponseEntity<Object> postTelevision(@RequestBody Television television) {
        Televisions.add(television);

        return ResponseEntity.created(null).build();


    }

    @PutMapping("/televisions/{id}")
    public ResponseEntity<Object> updateTelevision(@PathVariable int id, @RequestBody Television television) {
        if (id < Televisions.size()) {
            // haal de television uit de database
            Television newTelevision = Televisions.get(id);
            // pas de television aan met nieuwe waarde
            newTelevision.setName(television.getName());
            // sla de television op
            Televisions.set(id, newTelevision);
            return ResponseEntity.created(null).body(newTelevision);
        } else {
            throw new RecordNotFoundException("id not found");
        }


    }
    @
    DeleteMapping("/televisions/{id}")
    public ResponseEntity<Object> deleteTelevision(@RequestBody int id) {
        Television deleteTelevision = Televisions.remove(id);
        if (deleteTelevision == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(id, HttpStatus.NO_CONTENT);
      }

    }


