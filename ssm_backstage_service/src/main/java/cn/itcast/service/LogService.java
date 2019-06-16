package cn.itcast.service;

import cn.itcast.domain.SysLog;


public interface LogService {

    void save(SysLog sysLog) throws Exception;

}
