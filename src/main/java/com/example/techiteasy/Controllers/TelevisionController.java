package com.example.techiteasy.Controllers;
import com.example.techiteasy.dtos.TelevisionDto;
import com.example.techiteasy.service.TelevisionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;



@RestController
@RequestMapping("/televisions")
public class TelevisionController {
    private final TelevisionService tvService;

    public TelevisionController(TelevisionService tvService) {
        this.tvService = tvService;
    }

    @GetMapping("")
    public ResponseEntity<Iterable<TelevisionDto>> getTelevisions() {
        return ResponseEntity.ok(tvService.getTelevisions());
    }


    @GetMapping("/{id}")
    public ResponseEntity<TelevisionDto> getOneTelevision(@PathVariable Long id) {

        return ResponseEntity.ok(tvService.getOneTelevision(id));
    }


    @PostMapping("")
    public ResponseEntity<TelevisionDto> postTv(@RequestBody TelevisionDto televisionDto) {

        long id = tvService.createTelevision(televisionDto);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/televisions/" + id).toUriString());
        return ResponseEntity.created(uri).body(televisionDto);


    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletedTv(@PathVariable Long id) {

        return ResponseEntity.ok(tvService.deletedTv(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<TelevisionDto> updateTelevision(@PathVariable Long id, @RequestBody TelevisionDto televisionDto) {


        return ResponseEntity.ok(tvService.updateTelevision(id, televisionDto));
    }

    @PutMapping("{id}/remotecontroller/{remoteControllerId}")
    public ResponseEntity <String> assignRemoteToTelevision(@PathVariable Long id, @PathVariable Long remoteControllerId) {

        tvService.assignedRemoteToTv(id,remoteControllerId);
        return ResponseEntity.ok("Televisie is gekoppeld");

    }
}




