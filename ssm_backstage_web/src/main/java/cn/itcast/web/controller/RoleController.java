package cn.itcast.web.controller;


import cn.itcast.domain.Permission;
import cn.itcast.domain.Role;
import cn.itcast.service.RoleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 分页查询所有角色
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",defaultValue = "1",required = true) Integer page,
                                @RequestParam(name = "pageSize",defaultValue = "5",required = true) Integer size) throws Exception {

        ModelAndView mv = new ModelAndView();
        if(size !=null){
            mv.addObject("changeSize",size);
        }
        List<Role> roleList = roleService.findAll(page, size);
        PageInfo<Role> pageInfo = new PageInfo<Role>(roleList);

        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("role/role-list");

        return mv;
    }


    @RequestMapping("/save")
    public String save(Role role) throws Exception{

        roleService.save(role);

        return "redirect:/role/findAll.do";
    }


    @RequestMapping("/findById.do")
    public ModelAndView findById(String id) throws Exception {
        Role role = roleService.findById(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("role",role);
        mv.setViewName("role/role-show");

        return mv;
    }

    @RequestMapping("/findByIdAndAllPermission.do")
    public ModelAndView findByIdAndAllPermission(@RequestParam("id") String roleId,
                                                 @RequestParam(value = "page",required = true,defaultValue = "1") String page,
                                                 @RequestParam(value = "page",required = true,defaultValue = "5") String size) throws Exception {
        ModelAndView mv = new ModelAndView();
        Role role = roleService.findById(roleId);
        List<Permission> permissionList = roleService.findOtherPermissions(roleId);
        PageInfo<Permission> pageInfo = new PageInfo<Permission>(permissionList);
        mv.addObject("role",role);
        mv.addObject("pageInfo",pageInfo);
        mv.addObject("changeSize",size);
        mv.setViewName("role/role-permission-add");
        return mv;
    }


    @RequestMapping("/addPermissionsToRole.do")
    public String addPermissionsToRole(String roleId,@RequestParam(name ="ids") String[] permissionIds) throws Exception{
        roleService.addPermissionsToRole(roleId,permissionIds);
        return "redirect:/role/findByIdAndAllPermission.do?id="+roleId;
    }



}

