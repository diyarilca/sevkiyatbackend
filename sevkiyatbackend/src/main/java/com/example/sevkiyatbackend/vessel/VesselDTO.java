package com.example.sevkiyatbackend.vessel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VesselDTO {

    private Long id;
    private String vesselName;
    private String exporter;
    private String departurePort;
    private String destinationPort;
}
