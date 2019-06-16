package cn.itcast.web.controller;

import cn.itcast.domain.SysLog;
import cn.itcast.service.LogService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/sysLog")
public class SyslogController {

    @Autowired
    private LogService logService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1") Integer page,
                                 @RequestParam(name = "pageSize",required = true,defaultValue = "5") Integer size) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<SysLog> sysLogList = logService.findAll(page, size);
        PageInfo<SysLog> pageInfo = new PageInfo<SysLog>(sysLogList);
        mv.addObject("pageInfo",pageInfo);
        mv.addObject("changeSize",size);
        mv.setViewName("syslog-list");
        return mv;
    }
}
