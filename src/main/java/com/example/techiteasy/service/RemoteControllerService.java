package com.example.techiteasy.service;
import com.example.techiteasy.Models.RemoteController;
import com.example.techiteasy.Repositories.RemoteControllerRepository;
import com.example.techiteasy.dtos.RemoteControllerDto;
import com.example.techiteasy.exceptions.RecordNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class RemoteControllerService {
    private final RemoteControllerRepository remoteRepos;

    //logice van get mapping all remotes
    public Iterable <RemoteControllerDto> getRemoteControllers() {
        ArrayList <RemoteControllerDto> allDtoRemotes = new ArrayList<RemoteControllerDto>();
        Iterable <RemoteController> allRemotes = remoteRepos.findAll();
        for (RemoteController r: allRemotes) {
            RemoteControllerDto remotedto = transferToDto(r);
            allDtoRemotes.add(remotedto);
        }

        return allDtoRemotes;

    }


    public RemoteControllerService(RemoteControllerRepository remoteRepos) {
        this.remoteRepos = remoteRepos;
    }
    //logica van get mapping voor 1 tv
    public RemoteControllerDto getOneRemote(Long id) {
        Optional<RemoteController> foundRemote = remoteRepos.findById(id);

        if (foundRemote.isPresent()) {
            RemoteController remotecontroller = foundRemote.get();
            return transferToDto(remotecontroller);
        } else {
            throw new RecordNotFoundException("id not found");
        }
    }

    // logica van postmapping
    public long createRemote(RemoteControllerDto remotecontrollerDto) {
        RemoteController remote = transferFromDto(remotecontrollerDto);
        RemoteController savedRemote = remoteRepos.save(remote);

        return savedRemote.getId();

    }



    //logica van delete mapping
    public String deletedRemote(Long id) {
        Optional<RemoteController> deletedRemote = remoteRepos.findById(id);
        if (deletedRemote.isPresent()) {
            RemoteController remotecontroller = deletedRemote.get();
            remoteRepos.delete(remotecontroller);
            return "Remote-Controller Removed";
        } else {
            throw new RecordNotFoundException("Remote-Controller(ID) not found!");
        }
    }

    //logica van put mapping
    public RemoteControllerDto updateRemote(Long id, RemoteControllerDto remotecontrollerDto) {
        Optional<RemoteController> updatedRemote = remoteRepos.findById(id);
        if (updatedRemote.isPresent()) {
            // haal de television uit de database
            RemoteController updateremote = updatedRemote.get();
            // pas de television aan met nieuwe waarde


            updateremote.setCompatibleWith(remotecontrollerDto.getCompatibleWith());
            updateremote.setBatteryType(remotecontrollerDto.getBatteryType());
            updateremote.setName(remotecontrollerDto.getName());
            updateremote.setBrand(remotecontrollerDto.getBrand());
            updateremote.setPrice(remotecontrollerDto.getPrice());
            updateremote.setOriginalStock(remotecontrollerDto.getOriginalStock());


            // sla de television op
            remoteRepos.save(updateremote);
            //return
            return transferToDto(updateremote);
        } else {
            throw new RecordNotFoundException("Remote-Controller(id) not found");
        }

    }

    public RemoteControllerDto transferToDto (RemoteController remotecontroller) {
        RemoteControllerDto dto = new RemoteControllerDto();

        dto.setCompatibleWith(remotecontroller.getCompatibleWith());
        dto.setBatteryType(remotecontroller.getBatteryType());
        dto.setName(remotecontroller.getName());
        dto.setBrand(remotecontroller.getBrand());
        dto.setPrice(remotecontroller.getPrice());
        dto.setOriginalStock(remotecontroller.getOriginalStock());

        return dto;
    }
    public RemoteController transferFromDto (RemoteControllerDto remotecontrollerDto) {

        RemoteController remotecontroller = new RemoteController();

        remotecontroller.setCompatibleWith(remotecontrollerDto.getCompatibleWith());
        remotecontroller.setBatteryType(remotecontrollerDto.getBatteryType());
        remotecontroller.setName(remotecontrollerDto.getName());
        remotecontroller.setBrand(remotecontrollerDto.getBrand());
        remotecontroller.setPrice(remotecontrollerDto.getPrice());
        remotecontroller.setOriginalStock(remotecontrollerDto.getOriginalStock());

        return remotecontroller;
    }
}