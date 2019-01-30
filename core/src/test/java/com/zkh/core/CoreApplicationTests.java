package com.zkh.core;

import com.zkh.core.consts.Roles;
import com.zkh.core.model.Role;
import com.zkh.core.model.Test3;
import com.zkh.core.model.User;
import com.zkh.core.utils.StringUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CoreApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void jwttTest() throws InterruptedException, IOException {
        // role
        Role normaleRole = new Role();

        normaleRole.setName(Roles.NORMAL_USER);
        normaleRole.setDescription("simple");

        Role adminRole = new Role();

        adminRole.setName(Roles.ADMIN);
        adminRole.setDescription("administrator");

        // amdin
        User admin = new User();

        admin.setUsername("test");
        admin.setNick("testuser");

        // 密码
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        admin.setPassword(encoder.encode("123456"));

        // 角色
        List<Role> roleList = new ArrayList<>();
        roleList.add(adminRole);
        roleList.add(normaleRole);
        admin.setRoles(roleList);

        Test3 test = new Test3();
        test.setNick("123");
        test.setUsername("abc");

//        String jsonString = StringUtil.toJsonString(admin);
//
//        System.out.println(jsonString);

        User user2 = StringUtil.fromJsonString("{\"id\":\"8ac8a18a684a485d01684a4867da0002\",\"createTime\":1547434486000,\"updateTime\":1547434486000,\"username\":\"test\",\"nick\":\"测试用户\",\"roles\":[{\"id\":\"8ac8a18a684a485d01684a48675d0001\",\"createTime\":1547434486000,\"updateTime\":1547434486000,\"name\":\"admin\",\"description\":\"管理员\"},{\"id\":\"8ac8a18a684a485d01684a4867420000\",\"createTime\":1547434486000,\"updateTime\":1547434486000,\"name\":\"user\",\"description\":\"普通用户\"}]}", User.class);
        System.out.println(user2);
        System.out.println(user2.getUsername());
    }

}
