package controller;

import domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.ProductService;
import service.serviceImpl.ProductServiceImpl;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductContraller {
    @Autowired
    private ProductService productService;

    //查询产品信息
    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception{
        ModelAndView mv=new ModelAndView();
        List<Product> list=productService.findAll();
        mv.addObject("productList", list);
        mv.setViewName("product-list");
        return mv;
    }
    //添加产品信息
    @RequestMapping("save.do")
    public String save(Product product) throws Exception{
        productService.save(product);
        return "redirect:findAll.do";
    }
}
