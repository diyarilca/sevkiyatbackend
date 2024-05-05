package com.example.sevkiyatbackend.vehicle;

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
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vehicle_type",nullable = false)
    private String vehicleType;

    @Column(name = "driver_name",nullable = false)
    private String driverName;

    @Column(name = "driver_tel_number",nullable = false)
    private String driverTelNumber;

    @Column(name = "tow_plate",nullable = false,unique = true)
    private String towPlate;

    @Column(name = "trailer_plate",nullable = false,unique = true)
    private String trailerPlate;

}
