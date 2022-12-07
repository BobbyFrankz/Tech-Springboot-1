package com.example.techiteasy.dtos;

import com.example.techiteasy.Models.RemoteController;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Getter
@Setter

public class TelevisionInputDto {
    private Long id;
    private String type;
    private String brand;
    @NotNull
    @Size(min = 1, max = 30)
    private String name;
    @Positive
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

