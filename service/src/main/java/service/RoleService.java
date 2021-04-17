package service;

import domain.Permission;
import domain.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAll();
    void save(Role role);
    Role findById(String id);
    void deleteById(String id);

    List<Permission> findOtherPermission(String id);

    void addPermissionToRole(String roleId, String[] ids);
}
