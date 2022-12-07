package com.example.techiteasy.Controllers;
import com.example.techiteasy.dtos.TelevisionDto;
import com.example.techiteasy.dtos.WallBracketDto;
import com.example.techiteasy.service.TelevisionService;
import com.example.techiteasy.service.WallBracketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;



@RestController
@RequestMapping("/wallbrackets")
public class WallBracketController {
    private final WallBracketService wallService;

    public WallBracketController(WallBracketService wallService) {
        this.wallService = wallService;
    }
    @GetMapping("")
    public ResponseEntity<Iterable <WallBracketDto>> getWallBrackets() {
        return ResponseEntity.ok(wallService.getWallBrackets());
    }


    @GetMapping("/{id}")
    public ResponseEntity <WallBracketDto> getOneWallBracket(@PathVariable Long id) {

        return ResponseEntity.ok(wallService.getOneWallBracket(id));
    }



    @PostMapping("")
    public ResponseEntity<WallBracketDto> postWallBracket(@RequestBody WallBracketDto wallbracketDto) {

        long id = wallService.createWallBracket(wallbracketDto);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/wallbrackets/" + id ).toUriString());
        return ResponseEntity.created(uri).body(wallbracketDto);


    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletedWallBracket(@PathVariable Long id) {

        return ResponseEntity.ok(wallService.deletedWallBracket(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<WallBracketDto> updateWallBracket(@PathVariable Long id, @RequestBody WallBracketDto wallbracketDto) {


        return ResponseEntity.ok(wallService.updateWallBracket(id, wallbracketDto));
    }


}