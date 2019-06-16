package cn.itcast.web.controller;


import cn.itcast.domain.Role;
import cn.itcast.domain.UserInfo;
import cn.itcast.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name="page",required = true,defaultValue = "1") Integer page,
                                @RequestParam(name="pageSize",required = true,defaultValue = "5")Integer size) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<UserInfo> userList =  userService.findAll(page,size);
        PageInfo<UserInfo> pageInfo = new PageInfo<UserInfo>(userList);

        mv.addObject("changeSize",size);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("user/user-list");
        return mv;
    }


    @RequestMapping("/save.do")
    @PreAuthorize("hasRole('ROLE_SYSTEM_ADMIN')")
    public String save(UserInfo userInfo) throws Exception {

        userService.save(userInfo);

        return "redirect:/user/findAll.do";
    }


    @RequestMapping("/findById.do")
    public ModelAndView findById(String id) throws Exception {
        UserInfo userInfo = userService.findById(id);
        ModelAndView mv = new ModelAndView();

        mv.addObject("user",userInfo);
        mv.setViewName("user/user-show");
        return mv;
    }


    /**
     * 修改密码
     * @return
     */
    @RequestMapping("/changePassword")
    public String changePassword(String username, String password, String newPassword, Model model) throws Exception{
        boolean flag = userService.changePassword(username,password,newPassword);
        if(flag){
            return "redirect:/logout.do";
        }else{
            model.addAttribute("msg","密码错误");
            return "user/user-changepwd";
        }
    }

    @RequestMapping("/changeUserInfo")
    public String changePassword(UserInfo userInfo,HttpSession session) throws Exception{

        userService.changeUserInfo(userInfo);
        UserInfo loginUser = userService.findByUsername(userInfo.getUsername());
        session.setAttribute("userInfo",loginUser);
        return "user/user-changeSuccess";
    }


    @RequestMapping("/setLoginUser.do")
    public String getLoginUser(HttpServletRequest request, HttpSession session) throws Exception {
        String username = request.getUserPrincipal().getName();
        if(username != null && !"".equals(username)){
            UserInfo userInfo = userService.findByUsername(username);
            session.setAttribute("userInfo",userInfo);
        }

        return "main";
    }


    @RequestMapping("/findByIdAndAllRole.do")
    public ModelAndView findByIdAndAllRole(String id,
                                           @RequestParam(name = "page",defaultValue = "1",required = true) Integer page,
                                           @RequestParam(name = "pageSize",defaultValue = "5",required = true) Integer size) throws Exception {
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo = userService.findById(id);
        List<Role> rolesList = userService.findOtherRoles(id,page,size);
        PageInfo<Role> pageInfo = new PageInfo<Role>(rolesList);

        mv.addObject("user_ar",userInfo);
        mv.addObject("pageInfo",pageInfo);
        mv.addObject("changeSize",size);
        mv.setViewName("user/user-role-add");
        return mv;
    }

    @RequestMapping("/addRolesToUser.do")
    @PreAuthorize("hasAnyRole('ROLE_SYSTEM_ADMIN')")
    public String addRolesToUser(String userId,@RequestParam(name = "ids") String[] roleIds) throws Exception{
        userService.addRolesToUser(userId,roleIds);

        return "redirect:/user/findByIdAndAllRole.do?id="+userId;
    }




}
