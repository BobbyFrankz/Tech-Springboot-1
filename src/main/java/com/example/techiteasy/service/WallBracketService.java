package com.example.techiteasy.service;
import com.example.techiteasy.Models.Television;
import com.example.techiteasy.Models.WallBracket;
import com.example.techiteasy.Repositories.WallBracketRepository;
import com.example.techiteasy.dtos.TelevisionDto;
import com.example.techiteasy.dtos.WallBracketDto;
import com.example.techiteasy.exceptions.RecordNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class WallBracketService {
    private final WallBracketRepository wallRepos;

    //logice van get mapping all tvs
    public Iterable <WallBracketDto> getWallBrackets() {
        ArrayList <WallBracketDto> allDtoWallBrackets = new ArrayList<WallBracketDto>();
        Iterable <WallBracket> allWallBrackets = wallRepos.findAll();
        for (WallBracket w: allWallBrackets) {
            WallBracketDto wallbracketdto = transferToDto(w);
            allDtoWallBrackets.add(wallbracketdto);
        }

        return allDtoWallBrackets;

    }


    public WallBracketService(WallBracketRepository tvRepos) {
        this.wallRepos = tvRepos;
    }
    //logica van get mapping voor 1 tv
    public WallBracketDto getOneWallBracket(Long id) {
        Optional<WallBracket> foundWallBracket = wallRepos.findById(id);

        if (foundWallBracket.isPresent()) {
            WallBracket wallbracket1 = foundWallBracket.get();
            return transferToDto(wallbracket1);
        } else {
            throw new RecordNotFoundException("id not found");
        }
    }

    // logica van postmapping
    public long createWallBracket(WallBracketDto wallbracketDto) {
        WallBracket wallbracket = transferFromDto(wallbracketDto);
        WallBracket savedWallBracket = wallRepos.save(wallbracket);

        return savedWallBracket.getId();

    }



    //logica van delete mapping
    public String deletedWallBracket(Long id) {
        Optional<WallBracket> deletedWallBracket = wallRepos.findById(id);
        if (deletedWallBracket.isPresent()) {
            WallBracket wallbracket1 = deletedWallBracket.get();
            wallRepos.delete(wallbracket1);
            return "Wall-Bracket Removed";
        } else {
            throw new RecordNotFoundException("Wall-Bracket(ID) not found!");
        }
    }

    //logica van put mapping
    public WallBracketDto updateWallBracket(Long id, WallBracketDto wallbracketDto) {
        Optional<WallBracket> updatedWallBracket = wallRepos.findById(id);
        if (updatedWallBracket.isPresent()) {
            // haal de television uit de database
            WallBracket updatewallbracket = updatedWallBracket.get();
            // pas de television aan met nieuwe waarde
            updatewallbracket.setSize(wallbracketDto.getSize());
            updatewallbracket.setAdjustable(wallbracketDto.isAdjustable());
            updatewallbracket.setName(wallbracketDto.getName());
            updatewallbracket.setPrice(wallbracketDto.getPrice());


            // sla de television op
            wallRepos.save(updatewallbracket);
            //return
            return transferToDto(updatewallbracket);
        } else {
            throw new RecordNotFoundException("Wall-Bracket(id) not found");
        }

    }

    public WallBracketDto transferToDto (WallBracket wallbracket) {
        WallBracketDto dto = new WallBracketDto();

        dto.setSize(wallbracket.getSize());
        dto.setAdjustable(wallbracket.isAdjustable());
        dto.setName(wallbracket.getName());
        dto.setPrice(wallbracket.getPrice());

        return dto;
    }
    public WallBracket transferFromDto (WallBracketDto wallbracketDto) {

        WallBracket wallbracket = new WallBracket();

        wallbracket.setSize(wallbracketDto.getSize());
        wallbracket.setAdjustable(wallbracketDto.isAdjustable());
        wallbracket.setName(wallbracketDto.getName());
        wallbracket.setPrice(wallbracketDto.getPrice());
        return wallbracket;
    }
}