package com.xunyat.iot.vmp.gb28181.task;

import com.xunyat.iot.vmp.common.CommonCallback;

/**
 * @author lin
 */
public interface ISubscribeTask extends Runnable{
    void stop(CommonCallback<Boolean> callback);
}
