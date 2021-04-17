package dao;

import domain.Permission;
import domain.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface RoleDao {
    @Select("select * from role where id in(select roleId from users_role where userId=#{userId} )")
    List<Role> findByUserId(String userId);

    @Select("select * from role where id in (select roleId from users_role where userId=#{userId})")
    @Results(
            {
                    @Result(id=true,column = "id",property = "id"),
                    @Result(column = "roleName",property="roleName"),
                    @Result(column = "roleDesc",property = "roleDesc"),
                    @Result(column = "id",property = "permissions",many = @Many(select = "dao.PermissionDao.findById"))
            }
    )
    List<Role> findRoleByUserId(String id);

    //查询所有信息
    @Select("select * from role")
    List<Role> findAll();

    //角色添加
    @Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    void save(Role role);
    //根据id进行查询
    @Select("select * from role where id=#{id}")
    @Results(
            {
                    @Result(id=true,column = "id",property = "id"),
                    @Result(column = "roleName",property = "roleName"),
                    @Result(column = "roleDesc",property = "roleDesc"),
                    @Result(column = "id",property = "permissions",many =@Many(select = "dao.PermissionDao.findById"))
            }
    )
    Role findById(String id);

    //角色删除
    @Delete("delete from users_role where roleId=#{id}")
    void deleteUsers_roleById(String id);
    @Delete("delete from role_permission where roleId=#{id}")
    void deleteRole_permissionById(String id);
    @Delete("delete from role where id=#{id}")
    void deleteById(String id);

    //根据permissionId来查询
    @Select("select * from role where id in(select roleId from role_permission where permissionId=#{id})")
    List<Role> findByPermissionId(String id);

    //添加角色
    @Select("select * from permission where id not in(select permissionId from role_permission where roleId=#{id})")
    List<Permission> findOtherPermission(String id);
    @Insert("insert into role_permission(roleId,permissionId) values(#{roleId},#{id})")
    void addPermissionToRole(@Param(value="roleId") String roleId, @Param(value="id") String id);
}
