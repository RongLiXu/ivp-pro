package com.xunyat.iot.vmp.gb28181.task.impl;

import com.xunyat.iot.vmp.common.CommonCallback;
import com.xunyat.iot.vmp.gb28181.task.ISubscribeTask;
import com.xunyat.iot.vmp.service.IPlatformService;
import com.xunyat.iot.vmp.utils.SpringBeanFactory;

/**
 * 向已经订阅(移动位置)的上级发送MobilePosition消息
 * @author lin
 */
public class MobilePositionSubscribeHandlerTask implements ISubscribeTask {


    private IPlatformService platformService;
    private String platformId;


    public MobilePositionSubscribeHandlerTask(String platformId) {
        this.platformService = SpringBeanFactory.getBean("platformServiceImpl");
        this.platformId = platformId;
    }

    @Override
    public void run() {
        platformService.sendNotifyMobilePosition(this.platformId);
    }

    @Override
    public void stop(CommonCallback<Boolean> callback) {

    }
}
