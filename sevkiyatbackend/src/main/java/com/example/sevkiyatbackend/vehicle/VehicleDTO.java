package com.example.sevkiyatbackend.vehicle;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDTO {

    private Long id;
    private String vehicleType;
    private String driverName;
    private String driverTelNumber;
    private String towPlate;
    private String trailerPlate;
}
