package controller;

import domain.Permission;
import domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.RoleService;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleContraller {

    @Autowired
    private RoleService roleService;
    //查询所以
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        ModelAndView mv=new ModelAndView();
        List<Role> roles= roleService.findAll();
        mv.addObject("roleList",roles);
        mv.setViewName("role-list");
        return mv;
    }

    //角色添加
    @RequestMapping("/save.do")
    public String save(Role role){
         roleService.save(role);
        return "redirect:findAll.do";
    }

    //详情查询
    @RequestMapping("/findById.do")
    public ModelAndView findById(String id){
        ModelAndView mv=new ModelAndView();
        Role role=roleService.findById(id);
        mv.addObject("role",role);
        mv.setViewName("role-show");
        return mv;
    }

    //角色删除
    @RequestMapping("/deleteById.do")
    public String deleteById(String id){
        roleService.deleteById(id);
        return "redirect:findAll.do";
    }

    //添加权限
    @RequestMapping("/findRoleByIdAndAllPermission.do")
    public ModelAndView findRoleByIdAndAllPermission(String id){
        ModelAndView mv=new ModelAndView();
        Role role=roleService.findById(id);
        List<Permission> permissions=roleService.findOtherPermission(id);
        mv.addObject("role",role);
        mv.addObject("permissionList",permissions);
        mv.setViewName("role-permission-add");
        return mv;
    }
    @RequestMapping("/addPermissionToRole.do")
    public String addPermissionToRole(String roleId,String[] ids){
        roleService.addPermissionToRole(roleId,ids);
        return "redirect:findAll.do";
    }
}
