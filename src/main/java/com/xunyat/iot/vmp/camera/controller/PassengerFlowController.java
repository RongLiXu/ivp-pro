package com.xunyat.iot.vmp.camera.controller;

import com.xunyat.iot.vmp.camera.dto.PassengerFlowHourDataDTO;
import com.xunyat.iot.vmp.camera.dto.PassengerFlowTotalDataDTO;
import com.xunyat.iot.vmp.camera.service.IPassengerService;
import com.xunyat.iot.vmp.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/api/passengerFlow")
public class PassengerFlowController{

    @Autowired
    private IPassengerService passengerService;

    @GetMapping(value = "/total")
    public PassengerFlowTotalDataDTO getTotalData(){
        return passengerService.getTotalData();
    }

    @GetMapping(value = "/year/total")
    public PassengerFlowTotalDataDTO getTotalDataByTime(){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startTime = now.withMonth(1).withDayOfMonth(1).with(LocalTime.MIN).truncatedTo(ChronoUnit.SECONDS);
        LocalDateTime endTime = now.withMonth(12).withDayOfMonth(31).with(LocalTime.MAX).truncatedTo(ChronoUnit.SECONDS);
        return passengerService.getTotalDataByTime(startTime, endTime);
    }

    @GetMapping(value = "/camera/total")
    public List<PassengerFlowTotalDataDTO> getTotalDataByTimeAndCamera(Integer timeType,String day){

        LocalDateTime startTime;
        LocalDateTime endTime;
        LocalDateTime now = LocalDateTime.now();
        switch (timeType){
            case 1:
                if (StringUtils.hasText(day)){
                    String toYyyyMmDd = DateUtil.timestampTo_yyyy_MM_dd(Long.parseLong(day));

                    startTime = LocalDateTime.of(LocalDate.parse(toYyyyMmDd),LocalTime.MIN);
                    endTime = LocalDateTime.of(LocalDate.parse(toYyyyMmDd),LocalTime.MAX);
                }else{
                    startTime = now.with(LocalTime.MIN).truncatedTo(ChronoUnit.SECONDS);
                    endTime = now.with(LocalTime.MAX).truncatedTo(ChronoUnit.SECONDS);
                }
                break;
            case 2:
                startTime = now.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)).with(LocalTime.MIN).truncatedTo(ChronoUnit.SECONDS);
                endTime = now.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY)).with(LocalTime.MAX).truncatedTo(ChronoUnit.SECONDS).plusDays(7);
                break;
            case 3:
                startTime = now.withDayOfMonth(1).with(LocalTime.MIN).truncatedTo(ChronoUnit.SECONDS);
                endTime = now.with(TemporalAdjusters.lastDayOfMonth()).with(LocalTime.MAX).truncatedTo(ChronoUnit.SECONDS);
                break;
            default:
                startTime = now.with(LocalTime.MIN).truncatedTo(ChronoUnit.SECONDS);
                endTime = now.with(LocalTime.MAX).truncatedTo(ChronoUnit.SECONDS);
        }
        return passengerService.getTotalDataByTimeAndCamera(startTime, endTime);
    }

    @GetMapping(value = "/camera/hourly/total")
    public List<PassengerFlowHourDataDTO> getHourlyTotalDataByTimeAndCamera(String deviceId,String time){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startTime;
        LocalDateTime endTime;
        if (StringUtils.hasText(time)){
//            String dateTime = DateUtil.timestampTo_yyyy_MM_dd_HH_mm_ss(Long.parseLong(time));
            Instant instant = Instant.ofEpochSecond(Long.parseLong(time));
            endTime = LocalDateTime.ofInstant(instant, ZoneId.of(DateUtil.zoneStr)).withMinute(0).withSecond(0);
            startTime = endTime.minusHours(8);
        }else{
            LocalDateTime nearestHour = now.truncatedTo(ChronoUnit.HOURS);
            // 如果当前时间是整点以后的时间，则不变，如果是前，则向上取整到下一个整点
            if (now.getMinute() > 0 || now.getSecond() > 0 || now.getNano() > 0) {
                nearestHour = nearestHour.plusHours(1);
            }
            endTime = nearestHour.truncatedTo(ChronoUnit.SECONDS);
            startTime = endTime.minusHours(8);
        }
        return passengerService.getHourlyTotalDataByTimeAndCamera(startTime, endTime, deviceId);
    }
}
