package com.example.sevkiyatbackend.vessel;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/vessel")
public class VesselController {

    private VesselService vesselService;

    @GetMapping()
    public ResponseEntity<List<VesselDTO>> getAllShip(){

        List<VesselDTO> ship = vesselService.getAllVessel();

        return ResponseEntity.ok(ship);

    }

    @GetMapping("{id}")
    public ResponseEntity<VesselDTO> getShipById(@PathVariable("id") Long shipId){

        VesselDTO shipDto = vesselService.getVesselById(shipId);

        return ResponseEntity.ok(shipDto);

    }

    @PutMapping("{id}")
    public ResponseEntity<VesselDTO> updateShip(@PathVariable("id") Long shipId,@RequestBody VesselDTO updateShip){

        VesselDTO shipDto = vesselService.updateVessel(shipId, updateShip);

        return ResponseEntity.ok(shipDto);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteShip(@PathVariable("id") Long shipId){

        vesselService.deleteVessel(shipId);

        return ResponseEntity.ok("Gemi silme işlemi başarılı");

    }

    @PostMapping()
    public ResponseEntity<VesselDTO> createShip(@RequestBody VesselDTO shipDto){

        VesselDTO savedShip = vesselService.createVessel(shipDto);

        return new ResponseEntity<>(savedShip, HttpStatus.CREATED);

    }



}
