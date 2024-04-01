package com.xunyat.iot.vmp.camera.dto;

import lombok.Data;

@Data
public class PassengerFlowTotalDataDTO {
    private String deviceId;
    private String deviceName;
    private String ip;
    private Integer port;
    private String channel;
    private Integer totalJoin;
    private Integer totalLeave;
    private Integer totalPassing;
    private Double longitude;
    private Double latitude;
    private Double altitude;
    private Double speed;
}
