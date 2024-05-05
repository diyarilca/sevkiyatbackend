package com.example.sevkiyatbackend.vehicle;

import java.util.List;

public interface VehicleService {

    VehicleDTO getVehicleById(Long vehicleId);

    List<VehicleDTO> getAllVehicles();

    VehicleDTO updateVehicle(Long vehicleId, VehicleDTO updatedVehicle);

    void deleteVehicle(Long vehicleId);

    VehicleDTO createVehicle(VehicleDTO vehicleDto);
}
