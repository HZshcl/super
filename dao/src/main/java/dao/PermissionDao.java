package dao;

import domain.Permission;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface PermissionDao {
    @Select("select * from permission where id in (select permissionId from role_permission where roleId=#{id})")
    List<Permission> findById(String id);

    //查询所有
    @Select("select * from permission")
    List<Permission> findAll();

    //添加权限
    @Insert("insert into permission(permissionName,url) values(#{permissionName},#{url})")
    void save(Permission permission);

    //查询详情
    @Select("select * from permission where id=#{id}")
    @Results(
            {
                    @Result(id=true,column = "id",property = "id"),
                    @Result(column = "roleName",property = "roleName"),
                    @Result(column = "roleDesc",property = "roleDesc"),
                    @Result(column = "id",property = "roles",many = @Many(select ="dao.RoleDao.findByPermissionId"))
            }
    )
    Permission findById1(String id);

    //删除用户
    @Delete("delete from role_permission where permissionId=#{id}")
    void deleterole_permissionById(String id);
    @Delete("delete from permission where id=#{id}")
    void deleteById(String id);

}
