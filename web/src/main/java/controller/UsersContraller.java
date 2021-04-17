package controller;


import domain.Role;
import domain.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import service.IUserService;


import java.util.List;

@Controller
@RequestMapping("/user")
public class UsersContraller {

    @Autowired
    private  IUserService userService;
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        List<UserInfo> userInfos=userService.findAll();
          ModelAndView mv=new ModelAndView();
          mv.addObject("userList",userInfos);
          mv.setViewName("user-list");
          return mv;
     }
     @RequestMapping("/save.do")
    public String save(UserInfo user){
        userService.save(user);
        return "redirect:findAll.do";
     }

     @RequestMapping("/findById.do")
     public ModelAndView findById(String id){
        ModelAndView mv=new ModelAndView();
        UserInfo userInfo= userService.findById(id);
        mv.addObject("user",userInfo);
        mv.setViewName("user-show");
        return mv;
     }
     //添加角色
    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(String id){
        ModelAndView mv=new ModelAndView();
        UserInfo userInfo=userService.findById(id);
        List<Role> roles=userService.findOtherRole(id);
        mv.addObject("user",userInfo);
        mv.addObject("roleList",roles);
        mv.setViewName("user-role-add");
         return mv;
    }

    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(@RequestParam(required = true,name= "userId")String userId
            ,@RequestParam(required = true,name="ids") String[] ids){
        userService.addRoleToUser(userId,ids);
        return "redirect:findAll.do";
    }
}
