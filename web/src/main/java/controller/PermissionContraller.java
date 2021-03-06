package controller;


import domain.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.PermissionService;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionContraller {
    @Autowired
    private PermissionService permissionService;
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        ModelAndView mv=new ModelAndView();
        List<Permission> permissions=permissionService.findAll();
        mv.addObject("permissionList",permissions);
        mv.setViewName("permission-list");
        return mv;
    }

    @RequestMapping("/save.do")
    public String save(Permission permission)
    {
        permissionService.save(permission);
        return "redirect:findAll.do";
    }

    //详情查询
    @RequestMapping("/findById.do")
    public ModelAndView findById(String id){
        ModelAndView mv=new ModelAndView();
        Permission permission=permissionService.findById(id);
        mv.addObject("permission",permission);
        mv.setViewName("permission-show");
        return mv;
    }

    //用户的删除
    @RequestMapping("/deleteById")
    public String deleteById(String id){
       permissionService.deleteById(id);
        return "redirect:findAll.do";
    }

}
