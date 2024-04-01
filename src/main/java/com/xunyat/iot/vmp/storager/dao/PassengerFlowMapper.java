package com.xunyat.iot.vmp.storager.dao;

import com.xunyat.iot.vmp.camera.dto.PassengerFlowHourDataDTO;
import com.xunyat.iot.vmp.camera.dto.PassengerFlowTotalDataDTO;
import com.xunyat.iot.vmp.gb28181.bean.ParentPlatform;
import com.xunyat.iot.vmp.gb28181.bean.PassengerFlow;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
@Repository
public interface PassengerFlowMapper {
    @Insert("INSERT INTO device_passenger_flow (device_id,start_time, end_time, join_people, leave_people, passing_people, ip,port,channel,"+
            "ivms_channel,create_time,update_time) " +
            "            VALUES (#{deviceId},#{startTime}, #{endTime}, #{joinPeople}, #{leavePeople}, #{passingPeople}, #{ip}, #{port}, #{channel}, " +
            "            #{ivmsChannel}, #{createTime}, #{updateTime})")
    int addPassengerFlow(PassengerFlow passengerFlow);

    @Update("UPDATE device_passenger_flow " +
            "SET device_id=#{deviceId}, " +
            "start_time=#{startTime}, " +
            "end_time=#{endTime}," +
            "join_people=#{joinPeople}," +
            "leave_people=#{leavePeople}, " +
            "passing_people=#{passingPeople}, " +
            "ip=#{ip}," +
            "port=#{port}, " +
            "channel=#{channel}, " +
            "ivms_channel=#{ivmsChannel}, " +
            "create_time=#{createTime}, " +
            "update_time=#{updateTime} " +
            "WHERE id=#{id}")
    int updatePassengerFlow(PassengerFlow passengerFlow);

    @Select("SELECT sum(join_people) as total_join,sum(leave_people) as total_leave,sum(passing_people) as total_passing FROM device_passenger_flow")
    PassengerFlowTotalDataDTO getTotalPassengerFlowData();

    @Select("SELECT * FROM device_passenger_flow WHERE start_time = #{startTime} and end_time = #{endTime} and ip = #{ip} and port = #{port}")
    PassengerFlow selectStartTimeAndEndTimeAndIpAndPort(LocalDateTime startTime,LocalDateTime endTime,String ip,Integer port);

    @Select("SELECT * FROM device_passenger_flow WHERE start_time > #{startTime} and end_time < #{endTime}")
    List<PassengerFlow> selectByBetweenStartTimeAndEndTime(LocalDateTime startTime,LocalDateTime endTime);

    @Select("SELECT device_id,ip,port,sum(join_people) as total_join,sum(leave_people) as total_leave,sum(passing_people) as total_passing FROM device_passenger_flow WHERE start_time >= #{startTime} and end_time <= #{endTime} group by device_id,ip,port")
    List<PassengerFlowTotalDataDTO> getTotalDataByTimeGroupByIp_Port(LocalDateTime startTime, LocalDateTime endTime);

    @Select("SELECT DATE_FORMAT(start_time, '%Y-%m-%d %H') AS hourly_statistic, sum(join_people) as total_join,sum(leave_people) as total_leave,sum(passing_people) as total_passing " +
            "FROM device_passenger_flow " +
            "WHERE start_time >= #{startTime} and end_time <= #{endTime} and device_id = #{deviceId} " +
            "GROUP BY hourly_statistic")
    List<PassengerFlowHourDataDTO> getHourlyTotalDataByTimeAndIpAndPort(LocalDateTime startTime,LocalDateTime endTime,String deviceId);
}
