package com.xunyat.iot.vmp.service.bean;

public interface PlayBackCallback<T> {

    void call(PlayBackResult<T> msg);

}
