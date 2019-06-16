package cn.itcast.service;

import cn.itcast.domain.Role;
import cn.itcast.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<UserInfo> findAll(Integer page, Integer size) throws Exception;

    void save(UserInfo userInfo) throws Exception;

    UserInfo findById(String id) throws Exception;

    boolean changePassword(String username, String password, String newPassword)throws Exception;

    UserInfo findByUsername(String username) throws Exception;

    void changeUserInfo(UserInfo userInfo) throws Exception;

    List<Role> findOtherRoles(String id,Integer page,Integer size) throws Exception;

    void addRolesToUser(String userId, String[] roleIds) throws Exception;

}
