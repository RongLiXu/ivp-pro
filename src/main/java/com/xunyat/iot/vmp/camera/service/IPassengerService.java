package com.xunyat.iot.vmp.camera.service;

import com.xunyat.iot.vmp.camera.dto.PassengerFlowDataDTO;
import com.xunyat.iot.vmp.camera.dto.PassengerFlowHourDataDTO;
import com.xunyat.iot.vmp.camera.dto.PassengerFlowTotalDataDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface IPassengerService {

    int save(PassengerFlowDataDTO passengerFlowDataDTO);

    PassengerFlowTotalDataDTO getTotalData();

    PassengerFlowTotalDataDTO getTotalDataByTime(LocalDateTime startTime,LocalDateTime endTime);

    List<PassengerFlowTotalDataDTO> getTotalDataByTimeAndCamera(LocalDateTime startTime, LocalDateTime endTime);

    List<PassengerFlowHourDataDTO> getHourlyTotalDataByTimeAndCamera(LocalDateTime startTime, LocalDateTime endTime,String deviceId);
}
