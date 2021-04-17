package service.serviceImpl;

import dao.RoleDao;
import domain.Permission;
import domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.RoleService;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;
    //查询所有
    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public void save(Role role) {
        roleDao.save(role);
    }
   //详情查询
    @Override
    public Role findById(String id) {
        return roleDao.findById(id);
    }

    //删除角色
    @Override
    public void deleteById(String id) {
        roleDao.deleteUsers_roleById(id);
        roleDao.deleteRole_permissionById(id);
        roleDao.deleteById(id);
    }

    //添加权限
    @Override
    public List<Permission> findOtherPermission(String id) {
        return roleDao.findOtherPermission(id);
    }

    @Override
    public void addPermissionToRole(String roleId, String[] ids) {
        for(String id:ids){
            roleDao.addPermissionToRole(roleId,id);
        }
    }
}
