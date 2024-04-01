package com.xunyat.iot.vmp.camera.dto;

import lombok.Data;

@Data
public class PassengerFlowHourDataDTO {
    private String hourlyStatistic;
    private Integer totalJoin;
    private Integer totalLeave;
    private Integer totalPassing;
}
