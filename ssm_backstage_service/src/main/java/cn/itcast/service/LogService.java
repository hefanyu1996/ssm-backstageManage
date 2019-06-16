package cn.itcast.service;

import cn.itcast.domain.SysLog;

import java.util.List;


public interface LogService {

    void save(SysLog sysLog) throws Exception;

    List<SysLog> findAll(Integer page,Integer size) throws Exception;
}
