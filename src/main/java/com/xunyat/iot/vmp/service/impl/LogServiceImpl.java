package com.xunyat.iot.vmp.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.xunyat.iot.vmp.service.ILogService;
import com.xunyat.iot.vmp.storager.dao.LogMapper;
import com.xunyat.iot.vmp.storager.dao.dto.LogDto;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@DS("master")
public class LogServiceImpl implements ILogService {

    @Autowired
    private LogMapper logMapper;

    @Override
    public PageInfo<LogDto> getAll(int page, int count, String query, String type, String startTime, String endTime) {
        PageHelper.startPage(page, count);
        List<LogDto> all = logMapper.query(query, type, startTime, endTime);
        return new PageInfo<>(all);
    }

    @Override
    public void add(LogDto logDto) {
        logMapper.add(logDto);
    }

    @Override
    public int clear() {
        return logMapper.clear();
    }
}
