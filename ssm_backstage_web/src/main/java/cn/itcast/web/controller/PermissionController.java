package cn.itcast.web.controller;


import cn.itcast.domain.Permission;
import cn.itcast.domain.UserInfo;
import cn.itcast.service.PermissionService;
import cn.itcast.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name="page",required = true,defaultValue = "1") Integer page,
                                @RequestParam(name="pageSize",required = true,defaultValue = "5")Integer size) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Permission> permissionList =  permissionService.findAll(page,size);
        PageInfo<Permission> pageInfo = new PageInfo<Permission>(permissionList);

        mv.addObject("changeSize",size);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("permission/permission-list");
        return mv;
    }


    @RequestMapping("/save.do")
    public String save(Permission permission) throws Exception{
        permissionService.save(permission);

        return "redirect:/permission/findAll.do";
    }

}
