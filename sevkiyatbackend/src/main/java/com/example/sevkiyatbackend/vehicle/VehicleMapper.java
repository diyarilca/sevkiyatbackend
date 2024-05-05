package com.example.sevkiyatbackend.vehicle;

public class VehicleMapper {

    public static VehicleDTO mapToVehicleDto(Vehicle vehicle) {
        return new VehicleDTO(
                vehicle.getId(),
                vehicle.getVehicleType(),
                vehicle.getDriverName(),
                vehicle.getDriverTelNumber(),
                vehicle.getTowPlate(),
                vehicle.getTrailerPlate()
        );
    }

    public static Vehicle mapToVehicle(VehicleDTO vehicleDTO) {
        return new Vehicle(
                vehicleDTO.getId(),
                vehicleDTO.getVehicleType(),
                vehicleDTO.getDriverName(),
                vehicleDTO.getDriverTelNumber(),
                vehicleDTO.getTowPlate(),
                vehicleDTO.getTrailerPlate()
        );
    }
}
