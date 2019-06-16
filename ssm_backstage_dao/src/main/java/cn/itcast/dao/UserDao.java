package cn.itcast.dao;

import cn.itcast.domain.Role;
import cn.itcast.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserDao {

    @Select("select * from users where username = #{username}")
    @Results({
            @Result(id = true,property = "id" ,column = "id"),
            @Result(property = "username" ,column = "username"),
            @Result(property = "email" ,column = "email"),
            @Result(property = "password" ,column = "password"),
            @Result(property = "phoneNum" ,column = "phoneNum"),
            @Result(property = "status" ,column = "status"),
            @Result(property = "roles" ,javaType = List.class,many = @Many(select = "cn.itcast.dao.RoleDao.findByUid"),column = "id"),
    })
    UserInfo findByUsername(String username) throws Exception;


    @Select("select * from users")
    List<UserInfo> findAll() throws Exception;

    @Insert("insert into users (email,username,password,phoneNum,status) values(#{email},#{username},#{password},#{phoneNum},#{status})")
    void save(UserInfo userInfo) throws Exception;

    @Select("select * from users where id = #{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "email", column = "email"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles", javaType = java.util.List.class, many = @Many(select = "cn.itcast.dao.RoleDao.findByUid"), column = "id"),
    })
    UserInfo findById(String id) throws Exception;


    /**
     * 根据用户名修改密码
     * @param userInfo
     */
    @Update("update users set password=#{password} where username = #{username}")
    void changePassword(UserInfo userInfo) throws Exception;


    @Update("update users set email = #{email},phoneNum = #{phoneNum} where username = #{username}")
    void changeUserInfo(UserInfo userInfo) throws Exception;


    @Select("select * from role where id not in(select roleId from users_role where userId = #{uid})")
    List<Role> findOtherRoles(String id) throws Exception;

    @Insert("insert into users_role values(#{userId},#{roleId})")
    void addRolesToUser(@Param("userId") String userId, @Param("roleId") String roleId) throws Exception;
}
