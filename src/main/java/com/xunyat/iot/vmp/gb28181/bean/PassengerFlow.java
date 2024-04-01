package com.xunyat.iot.vmp.gb28181.bean;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PassengerFlow {
    private Long id;
    private String deviceId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer joinPeople;
    private Integer leavePeople;
    private Integer passingPeople;
    private String ip;
    private Integer port;
    private String channel;
    private String ivmsChannel;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
