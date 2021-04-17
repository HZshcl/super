package service.serviceImpl;

import dao.UserDao;
import domain.Role;
import domain.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.IUserService;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private PasswordEncoder passwordEncoder;
    //查询时的安全认证
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo=userDao.findByUsername(username);
        User user=new User(userInfo.getUsername(),new BCryptPasswordEncoder().encode(userInfo.getPassword())
                ,userInfo.getStatus()==1?true:false,
                true,true,true,getAuthority(userInfo.getRoles()));
        return user;
    }
    private List<SimpleGrantedAuthority> getAuthority(List<Role> roles){
        List<SimpleGrantedAuthority> authoritys=new ArrayList<>();
        for(Role role: roles){
            authoritys.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return authoritys;
    }
   //查询所以user用户
    @Override
    public List<UserInfo> findAll() {
        return userDao.findAll();
    }
  //保存用户
    @Override
    public void save(UserInfo userInfo) {
        //加密操作
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userDao.save(userInfo);
        return;
    }

    //查询用户详情
    @Override
    public UserInfo findById(String id) {
        return userDao.findById(id);
    }

    @Override
    public List<Role> findOtherRole(String id) {
        return userDao.findOtherRole(id);
    }

    @Override
    public void addRoleToUser(String userId, String[] ids) {
        for(String id:ids){
            userDao.addRoleTuUser(userId,id);
        }
    }
}
