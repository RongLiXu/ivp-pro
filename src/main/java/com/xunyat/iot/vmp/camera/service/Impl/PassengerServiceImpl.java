package com.xunyat.iot.vmp.camera.service.Impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.xunyat.iot.vmp.camera.dto.PassengerFlowHourDataDTO;
import com.xunyat.iot.vmp.camera.dto.PassengerFlowTotalDataDTO;
import com.xunyat.iot.vmp.gb28181.bean.PassengerFlow;
import com.xunyat.iot.vmp.camera.dto.PassengerFlowDataDTO;
import com.xunyat.iot.vmp.camera.service.IPassengerService;
import com.xunyat.iot.vmp.gb28181.bean.WvpDevice;
import com.xunyat.iot.vmp.gb28181.bean.WvpDeviceMobilePosition;
import com.xunyat.iot.vmp.storager.dao.PassengerFlowMapper;
import com.xunyat.iot.vmp.storager.dao.WvpDeviceMapper;
import com.xunyat.iot.vmp.storager.dao.WvpDeviceMobilePositionMapper;
import com.xunyat.iot.vmp.utils.DateUtil;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@DS("ivp")
public class PassengerServiceImpl implements IPassengerService {

    @Autowired
    private PassengerFlowMapper passengerFlowMapper;
    @Autowired
    private WvpDeviceMapper wvpDeviceMapper;
    @Autowired
    private WvpDeviceMobilePositionMapper wvpDeviceMobilePositionMapper;

    @Override
    public int save(PassengerFlowDataDTO passengerFlowDataDTO) {
        PassengerFlow passengerFlow = passengerFlowMapper.selectStartTimeAndEndTimeAndIpAndPort(passengerFlowDataDTO.getStartTime(), passengerFlowDataDTO.getEndTime(), passengerFlowDataDTO.getIp(), passengerFlowDataDTO.getPort());
        if (Objects.isNull(passengerFlow)){
            passengerFlow = new PassengerFlow();
            passengerFlow.setStartTime(passengerFlowDataDTO.getStartTime());
            passengerFlow.setEndTime(passengerFlowDataDTO.getEndTime());
            passengerFlow.setIp(passengerFlowDataDTO.getIp());
            passengerFlow.setPort(passengerFlowDataDTO.getPort());
            passengerFlow.setCreateTime(LocalDateTime.now());
            passengerFlow.setChannel(passengerFlowDataDTO.getChannel());
            passengerFlow.setIvmsChannel(passengerFlowDataDTO.getIvmsChannel());
            passengerFlow.setUpdateTime(LocalDateTime.now());
            passengerFlow.setJoinPeople(passengerFlowDataDTO.getJoinPeople());
            passengerFlow.setLeavePeople(passengerFlowDataDTO.getLeavePeople());
            passengerFlow.setPassingPeople(passengerFlowDataDTO.getPassingPeople());
            return passengerFlowMapper.addPassengerFlow(passengerFlow);
        }else{
            passengerFlow.setUpdateTime(LocalDateTime.now());
            passengerFlow.setJoinPeople(passengerFlowDataDTO.getJoinPeople());
            passengerFlow.setLeavePeople(passengerFlowDataDTO.getLeavePeople());
            passengerFlow.setPassingPeople(passengerFlowDataDTO.getPassingPeople());

            return passengerFlowMapper.updatePassengerFlow(passengerFlow);
        }
    }

    @Override
    public PassengerFlowTotalDataDTO getTotalData() {
        return passengerFlowMapper.getTotalPassengerFlowData();
    }

    @Override
    public PassengerFlowTotalDataDTO getTotalDataByTime(LocalDateTime startTime, LocalDateTime endTime) {
        List<PassengerFlow> passengerFlows = passengerFlowMapper.selectByBetweenStartTimeAndEndTime(startTime, endTime);
        Integer totalJoin = 0;
        Integer totalLeave = 0;
        Integer totalPassing = 0;
        for (PassengerFlow p :
                passengerFlows) {
            totalJoin += p.getJoinPeople();
            totalLeave+=p.getLeavePeople();
            totalPassing+=p.getPassingPeople();
        }
        PassengerFlowTotalDataDTO result = new PassengerFlowTotalDataDTO();
        result.setTotalJoin(totalJoin);
        result.setTotalLeave(totalLeave);
        result.setTotalPassing(totalPassing);
        return result;
    }

    @Override
    public List<PassengerFlowTotalDataDTO> getTotalDataByTimeAndCamera(LocalDateTime startTime, LocalDateTime endTime) {

        List<WvpDevice> deviceList = wvpDeviceMapper.selectAll();
        if (deviceList.size() > 0) {
            List<String> deviceIds = deviceList.stream().map(WvpDevice::getDeviceId).collect(Collectors.toList());
            List<PassengerFlowTotalDataDTO> passengerFlowTotalDataDTOList = passengerFlowMapper.getTotalDataByTimeGroupByIp_Port(startTime, endTime);
            List<WvpDeviceMobilePosition> wvpDeviceMobilePositions = wvpDeviceMobilePositionMapper.selectByDeviceIdIn(deviceIds);

            return deviceList.stream().map(wvpDevice -> {
                PassengerFlowTotalDataDTO passengerFlowTotalDataDTO = new PassengerFlowTotalDataDTO();
                passengerFlowTotalDataDTO.setDeviceId(wvpDevice.getDeviceId());
                passengerFlowTotalDataDTO.setDeviceName(wvpDevice.getCustomName());
                passengerFlowTotalDataDTO.setTotalJoin(0);
                passengerFlowTotalDataDTO.setTotalLeave(0);
                passengerFlowTotalDataDTO.setTotalPassing(0);
                wvpDeviceMobilePositions.stream()
                        .filter(wvpDeviceMobilePosition -> Objects.equals(wvpDeviceMobilePosition.getDeviceId(), passengerFlowTotalDataDTO.getDeviceId()))
                        .findAny()
                        .ifPresent(wvpDeviceMobilePosition -> {
                            passengerFlowTotalDataDTO.setLatitude(wvpDeviceMobilePosition.getLatitude());
                            passengerFlowTotalDataDTO.setLongitude(wvpDeviceMobilePosition.getLongitude());
                            passengerFlowTotalDataDTO.setAltitude(wvpDeviceMobilePosition.getAltitude());
                            passengerFlowTotalDataDTO.setSpeed(wvpDeviceMobilePosition.getSpeed());
                        });

                passengerFlowTotalDataDTOList.stream()
                        .filter(item -> Objects.equals(wvpDevice.getDeviceId(), item.getDeviceId()))
                        .findAny()
                        .ifPresent(item -> {
                            passengerFlowTotalDataDTO.setIp(item.getIp());
                            passengerFlowTotalDataDTO.setPort(item.getPort());
                            passengerFlowTotalDataDTO.setChannel(item.getChannel());
                            passengerFlowTotalDataDTO.setTotalJoin(item.getTotalJoin());
                            passengerFlowTotalDataDTO.setTotalLeave(item.getTotalLeave());
                            passengerFlowTotalDataDTO.setTotalPassing(item.getTotalPassing());
                        });

                return passengerFlowTotalDataDTO;
            }).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public List<PassengerFlowHourDataDTO> getHourlyTotalDataByTimeAndCamera(LocalDateTime startTime, LocalDateTime endTime ,String deviceId) {
        List<PassengerFlowHourDataDTO> passengerFlowHourDataDTOList = passengerFlowMapper.getHourlyTotalDataByTimeAndIpAndPort(startTime, endTime, deviceId);
        List<PassengerFlowHourDataDTO> result = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            String time = DateUtil.formatter.format(startTime.plusHours(i));
            PassengerFlowHourDataDTO item = passengerFlowHourDataDTOList.stream()
                    .filter(passengerFlowHourDataDTO -> Objects.equals(passengerFlowHourDataDTO.getHourlyStatistic() + ":00:00", time))
                    .findAny()
                    .orElse(null);
            if (Objects.isNull(item)){
                item = new PassengerFlowHourDataDTO();
                item.setHourlyStatistic(time.substring(11,16));
                item.setTotalJoin(0);
                item.setTotalLeave(0);
                item.setTotalPassing(0);
            }else{
                item.setHourlyStatistic(time.substring(11,16));
            }
            result.add(item);
        }

//        Stream<PassengerFlowHourDataDTO> sorted = result.stream().sorted(Comparator.comparing(PassengerFlowHourDataDTO::getHourlyStatistic).reversed());
        return result;
    }

}
