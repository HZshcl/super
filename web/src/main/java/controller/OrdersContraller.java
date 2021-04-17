package controller;

import com.github.pagehelper.PageInfo;
import dao.OrdersDao;
import domain.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import service.OrdersService;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersContraller {
    @Autowired
    private OrdersService ordersService;

//    @RequestMapping("/findAll.do")
//    public ModelAndView findAll() throws Exception{
//        ModelAndView mv=new ModelAndView();
//        List<Orders> orders=ordersService.findAll();
//        mv.addObject("ordersList",orders);
//        mv.setViewName("orders-list");
//        return mv;
//     }

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name="page",required =  true,defaultValue = "1") Integer page,
                                @RequestParam(name="size",required = true,defaultValue = "4") Integer size) throws Exception{
       List<Orders> ordersList=ordersService.findAllByPage(page,size);
        PageInfo pageInfo=new PageInfo(ordersList);
        ModelAndView mv=new ModelAndView();
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("orders-page-list");
        return mv;
    }
    @RequestMapping("/findById.do")
    public ModelAndView findById(String id)throws Exception{
        Orders orders=ordersService.findById(id);
        ModelAndView mv=new ModelAndView();
        mv.addObject("orders",orders);
        mv.setViewName("orders-show");
        return mv;
    }
}
