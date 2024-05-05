package com.example.sevkiyatbackend.vessel;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vessels")
public class Vessel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vessel_name")
    private String vesselName;

    @Column(name = "exporters")
    private String exporter;

    @Column(name = "departure_port")
    private String departurePort;

    @Column(name = "destination_port")
    private String destinationPort;

}
