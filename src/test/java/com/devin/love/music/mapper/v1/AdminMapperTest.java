package com.devin.love.music.mapper.v1;

import cn.hutool.crypto.digest.MD5;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.devin.love.music.common.utils.SnowFlake;
import com.devin.love.music.dao.v1.AdminDao;
import com.devin.love.music.domain.entity.Admin;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 2024/11/1 17:59
 * <p></p>
 *
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@SpringBootTest
public class AdminMapperTest {

    @Autowired
    private AdminDao adminDao;

    @Test
    public void test() {
        Admin devin = adminDao.getOne(new LambdaQueryWrapper<Admin>().eq(Admin::getUsername, "devin"));
        System.out.println(devin);
    }

    @Test
    public void addUser() {
        Admin admin = new Admin();
        admin.setId(SnowFlake.nextId());
        admin.setUsername("admin");
        admin.setPassword(MD5.create().digestHex("123"));
        adminDao.save(admin);
        System.out.println(admin);
    }
}
