package com.xunyat.iot.vmp.camera.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PassengerFlowDataDTO {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer joinPeople;
    private Integer leavePeople;
    private Integer passingPeople;
    private String ip;
    private Integer port;
    private String channel;
    private String ivmsChannel;
}
