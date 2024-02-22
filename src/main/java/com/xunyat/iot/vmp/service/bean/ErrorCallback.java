package com.xunyat.iot.vmp.service.bean;

public interface ErrorCallback<T> {

    void run(int code, String msg, T data);
}
