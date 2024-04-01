package com.xunyat.iot.vmp.camera.controller;

import com.xunyat.iot.vmp.camera.dto.PassengerFlowDataDTO;
import com.xunyat.iot.vmp.camera.dto.PassengerFlowTotalDataDTO;
import com.xunyat.iot.vmp.camera.service.IPassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/third/camera")
public class CameraController {

    @Autowired
    private IPassengerService passengerService;

    @PostMapping("/receive")
    public String receiveData(@RequestBody PassengerFlowDataDTO passengerFlowDataDTO){
        System.out.println(passengerFlowDataDTO.toString());
        return passengerService.save(passengerFlowDataDTO) == 1 ?"success":"fail";
    }
}
