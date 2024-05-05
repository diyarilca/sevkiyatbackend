package com.example.sevkiyatbackend.vessel;

import com.example.sevkiyatbackend.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class VesselServiceImpl implements VesselService{

    private VesselRepository vesselRepository;

    @Override
    public VesselDTO getVesselById(Long vesselId) {

        Vessel vessel = vesselRepository.findById(vesselId)
                .orElseThrow(() -> new ResourceNotFoundException("Verilen kimliğe sahip gemi mevcut değil" + vesselId));

        return VesselMapper.mapToVesselDto(vessel);


    }

    @Override
    public List<VesselDTO> getAllVessel() {

        List<Vessel> vessels = vesselRepository.findAll();

        return vessels.stream().map((vessel) -> VesselMapper.mapToVesselDto(vessel)).collect(Collectors.toList());
    }

    @Override
    public VesselDTO updateVessel(Long vesselId, VesselDTO updateVessel) {

        Vessel vessel = vesselRepository.findById(vesselId)
                .orElseThrow(() -> new ResourceNotFoundException("Verilen kimliğe sahip gemi mevcut değil" + vesselId));

        vessel.setVesselName(updateVessel.getVesselName());
        vessel.setExporter(updateVessel.getExporter());
        vessel.setDeparturePort(updateVessel.getDeparturePort());
        vessel.setDestinationPort(updateVessel.getDestinationPort());

        Vessel newVessel = vesselRepository.save(vessel);


        return VesselMapper.mapToVesselDto(newVessel);

    }

    @Override
    public void deleteVessel(Long vesselId) {

        Vessel vessel = vesselRepository.findById(vesselId)
                .orElseThrow(() -> new ResourceNotFoundException("Verilen kimliğe sahip gemi mevcut değil" + vesselId));

        vesselRepository.deleteById(vesselId);


    }

    @Override
    public VesselDTO createVessel(VesselDTO vesselDTO) {

        Vessel vessel = VesselMapper.mapToVessel(vesselDTO);

        Vessel savedVessel= vesselRepository.save(vessel);

        return VesselMapper.mapToVesselDto(savedVessel);
    }
}
