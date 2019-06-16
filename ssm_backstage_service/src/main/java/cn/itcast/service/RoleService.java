package cn.itcast.service;


import cn.itcast.domain.Permission;
import cn.itcast.domain.Role;

import java.util.List;

public interface RoleService {

    List<Role> findAll(Integer page,Integer size) throws Exception;

    Role findById(String id) throws Exception;

    void save(Role role) throws Exception;

    List<Permission> findOtherPermissions(String roleId) throws Exception;

    void addPermissionsToRole(String roleId, String[] permissionIds) throws Exception;
}
