package cn.itcast.service.impl;

import cn.itcast.dao.LogDao;
import cn.itcast.domain.SysLog;
import cn.itcast.service.LogService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("logService")
@Transactional
public class LogServiceImpl implements LogService {

    @Autowired
    private LogDao logDao;

    @Override
    public void save(SysLog sysLog) throws Exception {
        logDao.save(sysLog);
    }

    @Override
    public List<SysLog> findAll(Integer page,Integer size) throws Exception {

        PageHelper.startPage(page,size);
        return logDao.findAll();
    }
}
