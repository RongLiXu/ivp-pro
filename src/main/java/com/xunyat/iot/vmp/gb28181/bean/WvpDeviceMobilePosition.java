package com.xunyat.iot.vmp.gb28181.bean;

import lombok.Data;

@Data
public class WvpDeviceMobilePosition {
    private Long id;
    private String deviceId;
    private String channelId;
    private String deviceName;
    private String time;
    private Double longitude;
    private Double latitude;
    private Double altitude;
    private Double speed;
    private Double direction;
    private String reportSource;
    private Double longitudeGcj02;
    private Double latitudeGcj02;
    private Double longitudeWgs84;
    private Double latitudeWgs84;
    private String createTime;
}
