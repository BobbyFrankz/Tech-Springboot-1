package com.example.techiteasy.Controllers;

import com.example.techiteasy.Repositories.TelevisionRepository;
import com.example.techiteasy.exceptions.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import com.example.techiteasy.Models.Television;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;


@RestController
@RequestMapping("/televisions")
public class TelevisionController {
    @Autowired
    private TelevisionRepository tvRepos;

    @GetMapping("")
    public ResponseEntity<Iterable <Television>> getTelevisions() {
        return ResponseEntity.ok(tvRepos.findAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity <Television> getOneTelevision(@PathVariable Long id) {
        Optional <Television>  foundTelevision = tvRepos.findById(id);

        if (foundTelevision.isPresent()) {
            Television television1 = foundTelevision.get();
            return ResponseEntity.ok(television1);
        } else {
            throw new RecordNotFoundException("id not found");
        }
    }



    @PostMapping("")
    public ResponseEntity<Object> postTv(@RequestBody Television television) {
        Television savedTelevision = tvRepos.save(television);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/televisions/" + savedTelevision.getId()).toUriString());

        return ResponseEntity.created(uri).body(television);


    }

    @
    DeleteMapping("/{id}")
    public ResponseEntity<String> deletedTv(@PathVariable Long id) {

        Optional <Television> deletedTelevision = tvRepos.findById(id);
        if (deletedTelevision.isPresent()) {
            Television television1 = deletedTelevision.get();
            tvRepos.delete(television1);
            return ResponseEntity.ok("Tv Removed");
        } else {
            throw new RecordNotFoundException("Television(ID) not found!");
        }


    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTelevision(@PathVariable Long id, @RequestBody Television television) {
        Optional <Television> updatedTelevision = tvRepos.findById(id);
        if (updatedTelevision.isPresent()) {
            // haal de television uit de database
            Television television1 = updatedTelevision.get();
            // pas de television aan met nieuwe waarde
            television1.setName(television.getName());
            // sla de television op
            tvRepos.save(television1);
            return ResponseEntity.ok(television1);
        } else {
            throw new RecordNotFoundException("Television(id) not found");
        }


    }

    }


