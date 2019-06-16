package cn.itcast.service.impl;

import cn.itcast.dao.UserDao;
import cn.itcast.domain.Role;
import cn.itcast.domain.UserInfo;
import cn.itcast.service.UserService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserInfo userInfo = null;
        try {
            userInfo = userDao.findByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new User(userInfo.getUsername(), userInfo.getPassword(),
                userInfo.getStatus() == 1 ? true : false,
                true,
                true,
                true,
                getAuthority(userInfo.getRoles()));
    }

    /**
     * 权限集合描述
     *
     * @return
     */
    private List<GrantedAuthority> getAuthority(List<Role> roles) {
        List<GrantedAuthority> sgas = new ArrayList<>();
        SimpleGrantedAuthority sga = null;
        for (Role role : roles) {
            String roleName = "ROLE_" + role.getRoleName();
            System.out.println(roleName);
            sga = new SimpleGrantedAuthority(roleName);
            sgas.add(sga);
        }
        return sgas;
    }

    @Override
    public List<UserInfo> findAll(Integer page, Integer size) throws Exception {

        PageHelper.startPage(page, size);
        return userDao.findAll();
    }

    @Override
    public void save(UserInfo userInfo) throws Exception {

        //密码加密后重新赋值
        userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
        userDao.save(userInfo);
    }


    @Override
    public UserInfo findById(String id) throws Exception {
        return userDao.findById(id);
    }

    /**
     * 修改密码
     *
     * @param username    用户名
     * @param password    旧密码
     * @param newPassword 新密码
     * @throws Exception
     */
    @Override
    public boolean changePassword(String username, String password, String newPassword) throws Exception {
        UserInfo userInfo = userDao.findByUsername(username);
        boolean matches = bCryptPasswordEncoder.matches(password, userInfo.getPassword());
        if (matches) {
            userInfo.setPassword(bCryptPasswordEncoder.encode(newPassword));
            userDao.changePassword(userInfo);
        }

        return matches;
    }


    /**
     * 根据用户名查询用户
     * @param username
     * @return
     * @throws Exception
     */
    @Override
    public UserInfo findByUsername(String username) throws Exception {
        return userDao.findByUsername(username);
    }

    /**
     * 修改用户信息
     * @param userInfo
     */
    @Override
    public void changeUserInfo(UserInfo userInfo) throws Exception {
        userDao.changeUserInfo(userInfo);
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public List<Role> findOtherRoles(String id,Integer page,Integer size)  throws Exception{
        PageHelper.startPage(page,size);
        return userDao.findOtherRoles(id);
    }

    /**
     * 给用户添加角色
     * @param userId
     * @param roleIds
     */
    @Override
    public void addRolesToUser(String userId, String[] roleIds) throws Exception{
        for (String roleId : roleIds) {
            userDao.addRolesToUser(userId,roleId);
        }

    }


}
