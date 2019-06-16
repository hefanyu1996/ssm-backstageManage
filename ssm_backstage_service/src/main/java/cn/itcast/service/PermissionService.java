package cn.itcast.service;

import cn.itcast.domain.Permission;

import java.util.List;


public interface PermissionService {

    List<Permission> findAll(Integer page,Integer size) throws Exception;

    void save(Permission permission) throws Exception;
}
