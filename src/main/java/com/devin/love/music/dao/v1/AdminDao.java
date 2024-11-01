package com.devin.love.music.dao.v1;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.devin.love.music.domain.entity.Admin;
import com.devin.love.music.domain.vo.req.LoginReq;
import com.devin.love.music.mapper.v1.AdminMapper;
import org.springframework.stereotype.Service;

/**
 * 2024/11/1 17:50
 * <p></p>
 *
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Service
public class AdminDao extends ServiceImpl<AdminMapper, Admin> {

    /**
     * 根据username获取用户信息
     * @param loginReq
     * @return
     */
    public Admin getByUsername(LoginReq loginReq) {
        return lambdaQuery()
                .eq(Admin::getUsername, loginReq.getUsername())
                .one();
    }
}
