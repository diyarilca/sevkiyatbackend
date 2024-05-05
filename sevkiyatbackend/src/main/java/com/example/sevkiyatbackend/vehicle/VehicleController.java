package com.example.sevkiyatbackend.vehicle;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
@CrossOrigin("*")
public class VehicleController {

    private VehicleService vehicleService;

    @GetMapping()
    public ResponseEntity<List<VehicleDTO>> getAllVehicles(){

        List<VehicleDTO> vehicle = vehicleService.getAllVehicles();

        return ResponseEntity.ok(vehicle);

    }

    @GetMapping("{id}")
    public ResponseEntity<VehicleDTO> getVehicleById(@PathVariable("id") Long vehicleId){

        VehicleDTO vehicleDto = vehicleService.getVehicleById(vehicleId);

        return ResponseEntity.ok(vehicleDto);

    }

    @PutMapping("{id}")
    public ResponseEntity<VehicleDTO> updateVehicle(@PathVariable("id") Long vehicleId,@RequestBody VehicleDTO updatedVehicle){

        VehicleDTO vehicleDto = vehicleService.updateVehicle(vehicleId, updatedVehicle);

        return ResponseEntity.ok(vehicleDto);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteVehicle(@PathVariable Long vehicleId){

        vehicleService.deleteVehicle(vehicleId);

        return ResponseEntity.ok("Araç silme işlemi başarılı");

    }

    @PostMapping()
    public ResponseEntity<VehicleDTO> createVehicle(@RequestBody VehicleDTO vehicleDto){

        VehicleDTO savedVehicle = vehicleService.createVehicle(vehicleDto);

        return new ResponseEntity<>(savedVehicle, HttpStatus.CREATED);

    }
}
