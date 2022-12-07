package com.example.techiteasy.Models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "RemoteControllers")
public class RemoteController {

    @Id
    @GeneratedValue
    private Long id;
    private String compatibleWith;
    private String batteryType;
    private String name ;
    private String brand;
    Double price;
    Integer originalStock;
    @OneToOne (mappedBy = "remoteController")
    private Television television;

}
