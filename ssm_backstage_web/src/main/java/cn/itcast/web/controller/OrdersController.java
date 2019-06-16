package cn.itcast.web.controller;

import cn.itcast.domain.Orders;
import cn.itcast.service.OrdersService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @RequestMapping("/findAll.do")
//    @RolesAllowed("ADMIN")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SYSTEM_ADMIN','ROLE_USER')")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page,@RequestParam(name="pageSize",required = true,defaultValue = "5")Integer size) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Orders> ordersList = ordersService.findAll(page,size);
        PageInfo<Orders> pageInfo = new PageInfo<Orders>(ordersList);

        mv.addObject("changeSize",size);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("orders/orders-list");
        return mv;
    }

    @RequestMapping("/findById.do")
    public String findById(String id, Model model) throws Exception {
        Orders orders= ordersService.findById(id);
        model.addAttribute("orders",orders);
        return "orders/orders-show";
    }


    @RequestMapping("/findByPid.do")
    public ModelAndView findByPid(@RequestParam(name ="id") String pid,
                                  @RequestParam(name = "page",required = true,defaultValue = "1") Integer page ,
                                  @RequestParam(name = "pageSize",required = true,defaultValue = "5")Integer size) throws Exception{
        List<Orders> ordersList = ordersService.findByPid(pid,page,size);
        ModelAndView mv = new ModelAndView();
        if(size !=null){
            mv.addObject("changeSize",size);
        }
        PageInfo<Orders> pageInfo = new PageInfo<Orders>(ordersList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("product/product-orders-list");
        return mv;
    }
}
