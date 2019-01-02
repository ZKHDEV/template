package com.zkh.core;

import com.zkh.core.consts.Roles;
import com.zkh.core.dao.RoleDao;
import com.zkh.core.dao.UserDao;
import com.zkh.core.model.Role;
import com.zkh.core.model.User;
import com.zkh.core.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class CreateTestData implements CommandLineRunner {

    @Autowired
    UserDao userDao;

    @Autowired
    RoleDao roleDao;

    @Override
    public void run(String... args) throws Exception {
        // 用户不存在则创建测试数据
        if (userDao.findByUsername("test") == null) {
            log.info("创建测试数据.....");

            createUsers();

            // FIXME
            // createConfigs();

            log.info("创建测试数据完毕");
        }
    }

    public void createUsers() {
        log.info("---addUser---");

        // role
        Role normaleRole = new Role();

        normaleRole.setName(Roles.NORMAL_USER);
        normaleRole.setDescription("普通用户");

        normaleRole = roleDao.save(normaleRole);


        Role adminRole = new Role();

        adminRole.setName(Roles.ADMIN);
        adminRole.setDescription("管理员");

        adminRole = roleDao.save(adminRole);

        // amdin
        User admin = new User();

        admin.setUsername("test");
        admin.setNick("测试用户");

        // 密码
        admin.setPassword("123456");

        // 角色
        List<Role> roleList = new ArrayList<>();
        roleList.add(adminRole);
        roleList.add(normaleRole);
        admin.setRoles(roleList);

        userDao.save(admin);

        for (int i = 1; i <= 10; i++) {
            User user = new User();

            user.setUsername("user" + i);
            user.setNick("测试用户" + i);

            // 密码
            user.setPassword("123456");

            List<Role> roleList2 = new ArrayList<>();
            roleList.add(normaleRole);
            admin.setRoles(roleList);

            userDao.save(user);
        }
    }
}

