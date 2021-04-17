package service;

import domain.Role;
import domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {
    List<UserInfo> findAll();
    void save(UserInfo userInfo);
    UserInfo findById(String id);
    List<Role> findOtherRole(String id);

    void addRoleToUser(String userId, String[] ids);
}
