package com.example.techiteasy.dtos;


import com.example.techiteasy.Models.RemoteController;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class TelevisionDto {
    private String type;
    private String brand;
    private String name;
    private double price;
    private double availableSize;
    private double refreshRate;
    private String screenType;
    private String screenQuality;
    private boolean smartTv;
    private boolean wifi;
    private boolean voiceControl;
    private boolean hdr;
    private boolean bluetooth;
    private boolean ambiLight;
    private int originalStock;
    private int sold;
    @JsonIncludeProperties("id")
    private RemoteController remoteController;

}