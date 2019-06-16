package cn.itcast;

import cn.itcast.dao.PermissionDao;
import cn.itcast.dao.ProductDao;
import cn.itcast.dao.RoleDao;
import cn.itcast.dao.UserDao;
import cn.itcast.domain.Role;
import cn.itcast.domain.UserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring/applicationContext-*.xml")
public class ConnTest {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private UserDao userDao;


    @Test
    public void test01() throws Exception {
        UserInfo byId = userDao.findById("1");
        System.out.println(byId);
    }

    @Autowired
    private RoleDao roleDao;

    @Test
    public void test02() throws Exception {
        List<Role> all = roleDao.findAll();

        for (Role role : all) {
            System.out.println(role);
        }
    }

}
