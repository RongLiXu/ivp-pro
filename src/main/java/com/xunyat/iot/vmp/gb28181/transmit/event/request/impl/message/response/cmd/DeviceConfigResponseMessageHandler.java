package com.xunyat.iot.vmp.gb28181.transmit.event.request.impl.message.response.cmd;

import com.alibaba.fastjson2.JSONObject;
import com.xunyat.iot.vmp.gb28181.bean.Device;
import com.xunyat.iot.vmp.gb28181.bean.ParentPlatform;
import com.xunyat.iot.vmp.gb28181.transmit.callback.DeferredResultHolder;
import com.xunyat.iot.vmp.gb28181.transmit.callback.RequestMessage;
import com.xunyat.iot.vmp.gb28181.transmit.event.request.SIPRequestProcessorParent;
import com.xunyat.iot.vmp.gb28181.transmit.event.request.impl.message.IMessageHandler;
import com.xunyat.iot.vmp.gb28181.transmit.event.request.impl.message.response.ResponseMessageHandler;
import com.xunyat.iot.vmp.gb28181.utils.XmlUtil;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sip.RequestEvent;

import static com.xunyat.iot.vmp.gb28181.utils.XmlUtil.getText;

@Component
public class DeviceConfigResponseMessageHandler extends SIPRequestProcessorParent implements InitializingBean, IMessageHandler {

    private Logger logger = LoggerFactory.getLogger(DeviceConfigResponseMessageHandler.class);
    private final String cmdType = "DeviceConfig";

    @Autowired
    private ResponseMessageHandler responseMessageHandler;

    @Autowired
    private DeferredResultHolder deferredResultHolder;

    @Override
    public void afterPropertiesSet() throws Exception {
        responseMessageHandler.addHandler(cmdType, this);
    }

    @Override
    public void handForDevice(RequestEvent evt, Device device, Element element) {
        JSONObject json = new JSONObject();
        XmlUtil.node2Json(element, json);
        String channelId = getText(element, "DeviceID");
        if (logger.isDebugEnabled()) {
            logger.debug(json.toJSONString());
        }
        String key = DeferredResultHolder.CALLBACK_CMD_DEVICECONFIG + device.getDeviceId() + channelId;
        RequestMessage msg = new RequestMessage();
        msg.setKey(key);
        msg.setData(json);
        deferredResultHolder.invokeAllResult(msg);
    }

    @Override
    public void handForPlatform(RequestEvent evt, ParentPlatform parentPlatform, Element rootElement) {
    }
}
