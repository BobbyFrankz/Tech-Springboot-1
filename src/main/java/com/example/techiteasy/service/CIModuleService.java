package com.example.techiteasy.service;

import com.example.techiteasy.Models.CIModule;
import com.example.techiteasy.Repositories.CIModuleRepository;
import com.example.techiteasy.dtos.CIModuleDto;
import com.example.techiteasy.exceptions.RecordNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class CIModuleService {
    private final CIModuleRepository ciRepos;

    //logice van get mapping CIModules
    public Iterable <CIModuleDto> getCIModules() {
        ArrayList <CIModuleDto> allDtoCI = new ArrayList<CIModuleDto>();
        Iterable <CIModule> allCi = ciRepos.findAll();
        for (CIModule c: allCi) {
            CIModuleDto cidto = transferToDto(c);
            allDtoCI.add(cidto);
        }

        return allDtoCI;

    }


    public CIModuleService(CIModuleRepository ciRepos) {
        this.ciRepos = ciRepos;
    }
    //logica van get mapping voor 1 CIModule
    public CIModuleDto getOneCiModule(Long id) {
        Optional<CIModule> foundCIModule = ciRepos.findById(id);

        if (foundCIModule.isPresent()) {
            CIModule cimodule1 = foundCIModule.get();
            return transferToDto(cimodule1);
        } else {
            throw new RecordNotFoundException("id not found");
        }
    }

    // logica van postmapping
    public long createCIModule(CIModuleDto cimoduleDto) {
        CIModule ci = transferFromDto(cimoduleDto);
        CIModule savedCIModule = ciRepos.save(ci);

        return savedCIModule.getId();

    }



    //logica van delete mapping
    public String deletedCI(Long id) {
        Optional<CIModule> deletedCIModule = ciRepos.findById(id);
        if (deletedCIModule.isPresent()) {
            CIModule cimodule = deletedCIModule.get();
            ciRepos.delete(cimodule);
            return "CI-Module Removed";
        } else {
            throw new RecordNotFoundException("CI-Module(ID) not found!");
        }
    }

    //logica van put mapping
    public CIModuleDto updateCIModule(Long id, CIModuleDto cimoduledto) {
        Optional<CIModule> updatedCIModule = ciRepos.findById(id);
        if (updatedCIModule.isPresent()) {
            // haal de television uit de database
            CIModule updateci = updatedCIModule.get();
            // pas de television aan met nieuwe waarde
            updateci.setType(cimoduledto.getType());
            updateci.setName(cimoduledto.getName());
            updateci.setPrice(cimoduledto.getPrice());


            // sla de Ci op
            ciRepos.save(updateci);
            //return
            return transferToDto(updateci);
        } else {
            throw new RecordNotFoundException("CI-Module(id) not found");
        }

    }

    public CIModuleDto transferToDto (CIModule cimodule) {
        CIModuleDto dto = new CIModuleDto();
        dto.setType(cimodule.getType());
        dto.setName(cimodule.getName());
        dto.setPrice(cimodule.getPrice());

        return dto;
    }
    public CIModule transferFromDto (CIModuleDto cimoduleDto) {

        CIModule cimodule = new CIModule();

        cimodule.setType(cimoduleDto.getType());
        cimodule.setName(cimoduleDto.getName());
        cimodule.setPrice(cimoduleDto.getPrice());

        return cimodule;
    }
}

