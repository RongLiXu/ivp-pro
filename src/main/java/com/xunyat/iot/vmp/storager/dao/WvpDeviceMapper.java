package com.xunyat.iot.vmp.storager.dao;


import com.xunyat.iot.vmp.gb28181.bean.WvpDevice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface WvpDeviceMapper {

    @Select("<script> SELECT * FROM wvp_device WHERE device_id IN <foreach item='item' index='index' collection='deviceIds' open='(' separator=',' close=')'> #{item} </foreach> </script> ")
    List<WvpDevice> selectByDeviceIdIn(@Param("deviceIds") List<String> deviceIds);

    @Select(("SELECT * FROM wvp_device"))
    List<WvpDevice> selectAll();
}
