package com.xunyat.iot.vmp.storager.dao;

import com.xunyat.iot.vmp.gb28181.bean.WvpDeviceMobilePosition;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface WvpDeviceMobilePositionMapper {

    @Select("<script> SELECT * FROM wvp_device_mobile_position WHERE device_id IN <foreach item='item' index='index' collection='deviceIds' open='(' separator=',' close=')'> #{item} </foreach> </script> ")
    List<WvpDeviceMobilePosition> selectByDeviceIdIn(@Param("deviceIds") List<String> deviceIds);
}
