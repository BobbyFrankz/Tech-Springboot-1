package com.example.techiteasy.Controllers;
import com.example.techiteasy.dtos.CIModuleDto;
import com.example.techiteasy.service.CIModuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/cimodules")
public class CIModuleController {
    private final CIModuleService ciService;

    public CIModuleController(CIModuleService ciService) {
        this.ciService = ciService;
    }
    @GetMapping("")
    public ResponseEntity<Iterable <CIModuleDto>> getCIModules() {
        return ResponseEntity.ok(ciService.getCIModules());
    }


    @GetMapping("/{id}")
    public ResponseEntity <CIModuleDto> getOneCiModule(@PathVariable Long id) {

        return ResponseEntity.ok(ciService.getOneCiModule(id));
    }



    @PostMapping("")
    public ResponseEntity<CIModuleDto> postTv(@RequestBody CIModuleDto cIModuleDto) {

        long id = ciService.createCIModule(cIModuleDto);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/televisions/" + id ).toUriString());
        return ResponseEntity.created(uri).body(cIModuleDto);


    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletedCIModule(@PathVariable Long id) {

        return ResponseEntity.ok(ciService.deletedCI(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<CIModuleDto> updateCIModule(@PathVariable Long id, @RequestBody CIModuleDto ciModuleDto) {


        return ResponseEntity.ok(ciService.updateCIModule(id, ciModuleDto));
    }


}

