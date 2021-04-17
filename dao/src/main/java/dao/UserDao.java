package dao;


import domain.Role;
import domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

    @Select("select * from users")
    List<UserInfo> findAll();

    @Insert("insert into users(email,username,password,phoneNum,status) " +
            "values(#{email},#{username},#{password},#{phoneNum},#{status})")
    public void save(UserInfo userInfo);

    @Select("select * from users where username=#{username}")
    @Results(
            {
                    @Result(id=true,column = "id",property = "id"),
                    @Result(column = "username",property = "username"),
                    @Result(column = "email",property = "email"),
                    @Result(column = "password",property = "password"),
                    @Result(column = "phoneNum",property = "phoneNum"),
                    @Result(column = "status",property = "status"),
                    @Result(column = "id",property = "roles",many = @Many(select = "dao.RoleDao.findByUserId"))
            }
    )
    public UserInfo findByUsername(String username) ;

    @Select("select * from users where id=#{id}")
    @Results(
            {
                    @Result(id=true,column = "id",property = "id"),
                    @Result(column = "username",property = "username"),
                    @Result(column = "email",property = "email"),
                    @Result(column = "password",property = "password"),
                    @Result(column = "phoneNum",property = "phoneNum"),
                    @Result(column = "status",property = "status"),
                    @Result(column = "id" ,property = "roles" ,many = @Many(select = "dao.RoleDao.findRoleByUserId"))
            }
    )
    UserInfo findById(String id);

    //用户添加角色
    @Select("select * from role where id not in(select roleId from users_role where userId=#{id})")
    List<Role> findOtherRole(String id);

    @Insert("insert into users_role(userId,roleId) values(#{userId},#{id})")
    void addRoleTuUser(@Param(value = "userId") String userId,@Param(value="id") String id);
}
