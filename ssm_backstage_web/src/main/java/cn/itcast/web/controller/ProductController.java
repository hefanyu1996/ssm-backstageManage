package cn.itcast.web.controller;

import cn.itcast.domain.Product;
import cn.itcast.service.ProductService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page, @RequestParam(name = "pageSize",required = true,defaultValue = "5") Integer size) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Product> productList = productService.findAll(page,size);
        PageInfo<Product> pageInfo = new PageInfo<Product>(productList);

        mv.addObject("changeSize",size);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("product/product-list");
        return mv;
    }

    @RequestMapping("/save.do")
//    @Secured("ROLE_ADMIN")
    public String save(Product product) throws Exception {
        productService.save(product);
        return "redirect:/product/findAll.do";
    }

}
