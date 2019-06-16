package cn.itcast.dao;

import cn.itcast.domain.Permission;
import cn.itcast.domain.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

public interface RoleDao {

    @Select("select * from role where id in(select roleId from users_role where userId = #{uid})")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc"),
            @Result(property = "permissions", javaType = java.util.List.class, many = @Many(select = "cn.itcast.dao.PermissionDao.findByRid"), column = "id")
    })
    List<Role> findByUid(String id) throws Exception;


    @Select("select * from role")
    List<Role> findAll() throws Exception;



    @Select("select * from role where id = #{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc"),
            @Result(property = "permissions", javaType = java.util.List.class, many = @Many(select = "cn.itcast.dao.PermissionDao.findByRid"), column = "id")
    })
    Role findById(String id) throws Exception;


    @Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    void save(Role role);

    @Select("select * from permission where id not in(select permissionId from role_permission where roleId = #{roleId})")
    List<Permission> findOtherPermissions(String roleId);


    @Insert("insert into role_permission values(#{permissionId},#{roleId})")
    void addPermissionsToRole(@Param("permissionId")String permissionId,@Param("roleId") String roleId) throws Exception;
}
