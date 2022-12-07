package com.example.techiteasy.service;
import com.example.techiteasy.Models.RemoteController;
import com.example.techiteasy.Models.Television;
import com.example.techiteasy.Repositories.RemoteControllerRepository;
import com.example.techiteasy.Repositories.TelevisionRepository;
import com.example.techiteasy.dtos.TelevisionDto;
import com.example.techiteasy.exceptions.RecordNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class TelevisionService {
    private final TelevisionRepository tvRepos;
    private final RemoteControllerRepository remoteRepos;

    public TelevisionService(TelevisionRepository tvRepos, RemoteControllerRepository remoteRepos) {
        this.tvRepos = tvRepos;
        this.remoteRepos = remoteRepos;
    }

    //logice van get mapping all tvs
    public Iterable<TelevisionDto> getTelevisions() {
        ArrayList<TelevisionDto> allDtoTvs = new ArrayList<TelevisionDto>();
        Iterable<Television> allTvs = tvRepos.findAll();
        for (Television t : allTvs) {
            TelevisionDto tvdto = transferToDto(t);
            allDtoTvs.add(tvdto);
        }

        return allDtoTvs;

    }


    //logica van get mapping voor 1 tv
    public TelevisionDto getOneTelevision(Long id) {
        Optional<Television> foundTelevision = tvRepos.findById(id);

        if (foundTelevision.isPresent()) {
            Television television1 = foundTelevision.get();
            return transferToDto(television1);
        } else {
            throw new RecordNotFoundException("id not found");
        }
    }

    // logica van postmapping
    public long createTelevision(TelevisionDto televisionDto) {
        Television tv = transferFromDto(televisionDto);
        Television savedTelevision = tvRepos.save(tv);

        return savedTelevision.getId();

    }


    //logica van delete mapping
    public String deletedTv(Long id) {
        Optional<Television> deletedTelevision = tvRepos.findById(id);
        if (deletedTelevision.isPresent()) {
            Television television1 = deletedTelevision.get();
            tvRepos.delete(television1);
            return "Tv Removed";
        } else {
            throw new RecordNotFoundException("Television(ID) not found!");
        }
    }

    //logica van put mapping
    public TelevisionDto updateTelevision(Long id, TelevisionDto televisionDto) {
        Optional<Television> updatedTelevision = tvRepos.findById(id);
        if (updatedTelevision.isPresent()) {
            // haal de television uit de database
            Television updatetelevision = updatedTelevision.get();
            // pas de television aan met nieuwe waarde
            updatetelevision.setType(televisionDto.getType());
            updatetelevision.setBrand(televisionDto.getBrand());
            updatetelevision.setName(televisionDto.getName());
            updatetelevision.setPrice(televisionDto.getPrice());
            updatetelevision.setAvailableSize(televisionDto.getAvailableSize());
            updatetelevision.setRefreshRate(televisionDto.getRefreshRate());
            updatetelevision.setScreenType(televisionDto.getScreenType());
            updatetelevision.setScreenQuality(televisionDto.getScreenQuality());
            updatetelevision.setSmartTv(televisionDto.isSmartTv());
            updatetelevision.setWifi(televisionDto.isWifi());
            updatetelevision.setVoiceControl(televisionDto.isVoiceControl());
            updatetelevision.setHdr(televisionDto.isHdr());
            updatetelevision.setBluetooth(televisionDto.isBluetooth());
            updatetelevision.setAmbiLight(televisionDto.isAmbiLight());
            updatetelevision.setOriginalStock(televisionDto.getOriginalStock());
            updatetelevision.setSold(televisionDto.getSold());

            // sla de television op
            tvRepos.save(updatetelevision);
            //return
            return transferToDto(updatetelevision);
        } else {
            throw new RecordNotFoundException("Television(id) not found");
        }

    }

    public TelevisionDto transferToDto(Television television) {
        TelevisionDto dto = new TelevisionDto();
        dto.setType(television.getType());
        dto.setBrand(television.getBrand());
        dto.setName(television.getName());
        dto.setPrice(television.getPrice());
        dto.setAvailableSize(television.getAvailableSize());
        dto.setRefreshRate(television.getRefreshRate());
        dto.setScreenType(television.getScreenType());
        dto.setScreenQuality(television.getScreenQuality());
        dto.setSmartTv(television.isSmartTv());
        dto.setWifi(television.isWifi());
        dto.setVoiceControl(television.isVoiceControl());
        dto.setHdr(television.isHdr());
        dto.setBluetooth(television.isBluetooth());
        dto.setAmbiLight(television.isAmbiLight());
        dto.setOriginalStock(television.getOriginalStock());
        dto.setSold(television.getSold());
        if (television.getRemoteController() !=null)
            dto.setRemoteController(television.getRemoteController());
        return dto;
    }

    public Television transferFromDto(TelevisionDto televisionDto) {

        Television television = new Television();

        television.setType(televisionDto.getType());
        television.setBrand(televisionDto.getBrand());
        television.setName(televisionDto.getName());
        television.setPrice(televisionDto.getPrice());
        television.setAvailableSize(televisionDto.getAvailableSize());
        television.setRefreshRate(televisionDto.getRefreshRate());
        television.setScreenType(televisionDto.getScreenType());
        television.setScreenQuality(televisionDto.getScreenQuality());
        television.setSmartTv(televisionDto.isSmartTv());
        television.setWifi(televisionDto.isWifi());
        television.setVoiceControl(televisionDto.isVoiceControl());
        television.setHdr(televisionDto.isHdr());
        television.setBluetooth(televisionDto.isBluetooth());
        television.setAmbiLight(televisionDto.isAmbiLight());
        television.setOriginalStock(televisionDto.getOriginalStock());
        television.setSold(televisionDto.getSold());
        if (television.getRemoteController() !=null)
            television.setRemoteController(televisionDto.getRemoteController());
        return television;
    }

    public void assignedRemoteToTv(Long id, Long remoteControllerId) {
        Optional<Television> optionalTelevision = tvRepos.findById(id);
        Optional<RemoteController> optionalRemoteController = remoteRepos.findById(remoteControllerId);

        if (optionalTelevision.isPresent() && optionalRemoteController.isPresent()) {
            Television television1 = optionalTelevision.get();
            RemoteController remotecontroller1 = optionalRemoteController.get();
            television1.setRemoteController(remotecontroller1);
            tvRepos.save(television1);

        } else {
            throw new RecordNotFoundException("id not found");
        }

        }
    }

