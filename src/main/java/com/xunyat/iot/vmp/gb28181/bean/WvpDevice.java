package com.xunyat.iot.vmp.gb28181.bean;

import lombok.Data;

@Data
public class WvpDevice {
    private Long id;
    private String deviceId;
    private String name;
    private String manufacturer;
    private String model;
    private String firmware;
    private String transport;
    private String streamMode;
    private Integer onLine;
    private String registerTime;
    private String keepaliveTime;
    private String ip;
    private String createTime;
    private String updateTime;
    private Integer port;
    private Integer expires;
    private Integer subscribeCycleForCatalog;
    private Integer subscribeCycleForMobilePosition;
    private Integer mobilePositionSubmissionInterval;
    private Integer subscribeCycleForAlarm;
    private String hostAddress;
    private String charset;
    private Integer ssrcCheck;
    private String geoCoordSys;
    private String mediaServerId;
    private String customName;
    private String sdpIp;
    private String localIp;
    private String password;
    private Integer asMessageChannel;
    private Integer keepaliveIntervalTime;
    private Integer switchPrimarySubStream;
    private Integer broadcastPushAfterAck;
}
