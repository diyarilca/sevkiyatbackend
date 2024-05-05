package com.example.sevkiyatbackend.vessel;

import java.util.List;

public interface VesselService {
    VesselDTO getVesselById(Long vesselId);

    List<VesselDTO> getAllVessel();

    VesselDTO updateVessel(Long vesselId, VesselDTO updateVessel);

    void deleteVessel(Long vesselId);

    VesselDTO createVessel(VesselDTO vesselDTO);
}
