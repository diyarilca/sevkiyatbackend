package com.example.sevkiyatbackend.vehicle;

import com.example.sevkiyatbackend.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private VehicleRepository vehicleRepository;

    @Override
    public VehicleDTO getVehicleById(Long vehicleId) {

        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new ResourceNotFoundException("Verilen kimliğe sahip araç mevcut değil" + vehicleId));

        return VehicleMapper.mapToVehicleDto(vehicle);
    }

    @Override
    public List<VehicleDTO> getAllVehicles() {

        List<Vehicle> vehicles = vehicleRepository.findAll();

        return vehicles.stream().map((vehicle) -> VehicleMapper.mapToVehicleDto(vehicle)).collect(Collectors.toList());
    }

    @Override
    public VehicleDTO updateVehicle(Long vehicleId, VehicleDTO updatedVehicle) {

        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new ResourceNotFoundException("Verilen kimliğe sahip araç mevcut değil" + vehicleId));

        vehicle.setVehicleType(updatedVehicle.getVehicleType());
        vehicle.setDriverName(updatedVehicle.getDriverName());
        vehicle.setDriverTelNumber(updatedVehicle.getDriverTelNumber());
        vehicle.setTowPlate(updatedVehicle.getTowPlate());
        vehicle.setTrailerPlate(updatedVehicle.getTrailerPlate());

        Vehicle newVehicle = vehicleRepository.save(vehicle);


        return VehicleMapper.mapToVehicleDto(newVehicle);
    }

    @Override
    public void deleteVehicle(Long vehicleId) {

        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new ResourceNotFoundException("Verilen kimliğe sahip araç mevcut değil" + vehicleId));

        vehicleRepository.deleteById(vehicleId);

    }

    @Override
    public VehicleDTO createVehicle(VehicleDTO vehicleDto) {

        Vehicle vehicle = VehicleMapper.mapToVehicle(vehicleDto);

        Vehicle savedVehicle = vehicleRepository.save(vehicle);

        return VehicleMapper.mapToVehicleDto(savedVehicle);
    }
}
