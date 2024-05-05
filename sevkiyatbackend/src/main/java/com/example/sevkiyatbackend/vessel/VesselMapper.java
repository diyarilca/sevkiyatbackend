package com.example.sevkiyatbackend.vessel;

public class VesselMapper {

    public static VesselDTO mapToVesselDto(Vessel vessel) {

        return new VesselDTO(
                vessel.getId(),
                vessel.getVesselName(),
                vessel.getExporter(),
                vessel.getDeparturePort(),
                vessel.getDestinationPort()
        );
    }

    public static Vessel mapToVessel(VesselDTO vesselDTO) {

        return new Vessel(
                vesselDTO.getId(),
                vesselDTO.getVesselName(),
                vesselDTO.getExporter(),
                vesselDTO.getDeparturePort(),
                vesselDTO.getDestinationPort()
        );
    }

}
