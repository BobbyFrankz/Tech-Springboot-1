package com.example.techiteasy.Controllers;
import com.example.techiteasy.dtos.RemoteControllerDto;
import com.example.techiteasy.service.RemoteControllerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;



@RestController
@RequestMapping("/remotes")
public class RemoteControllerController {
    private final RemoteControllerService remoteService;

    public RemoteControllerController(RemoteControllerService remoteService) {
        this.remoteService = remoteService;
    }
    @GetMapping("")
    public ResponseEntity<Iterable <RemoteControllerDto>> getRemoteControllers() {
        return ResponseEntity.ok(remoteService.getRemoteControllers());
    }


    @GetMapping("/{id}")
    public ResponseEntity <RemoteControllerDto> getOneRemote(@PathVariable Long id) {

        return ResponseEntity.ok(remoteService.getOneRemote(id));
    }



    @PostMapping("")
    public ResponseEntity<RemoteControllerDto> postRemote(@RequestBody RemoteControllerDto remotecontrollerDto) {

        long id = remoteService.createRemote(remotecontrollerDto);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/remotes/" + id ).toUriString());
        return ResponseEntity.created(uri).body(remotecontrollerDto);


    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletedRemote(@PathVariable Long id) {

        return ResponseEntity.ok(remoteService.deletedRemote(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<RemoteControllerDto> updateRemote(@PathVariable Long id, @RequestBody RemoteControllerDto remoteControllerDto) {


        return ResponseEntity.ok(remoteService.updateRemote(id, remoteControllerDto));
    }


}

