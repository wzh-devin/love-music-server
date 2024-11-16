package com.devin.love.music.service.v1.impl;

import cn.hutool.crypto.digest.MD5;
import com.devin.love.music.common.constant.RedisKey;
import com.devin.love.music.common.domain.dto.RequestInfo;
import com.devin.love.music.common.utils.JwtUtil;
import com.devin.love.music.common.utils.RedisUtil;
import com.devin.love.music.common.utils.RequestContext;
import com.devin.love.music.dao.v1.AdminDao;
import com.devin.love.music.domain.entity.Admin;
import com.devin.love.music.domain.vo.req.LoginReq;
import com.devin.love.music.service.v1.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * 2024/11/1 17:47
 * <p></p>
 *
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminDao adminDao;
    private final JwtUtil jwtUtil;

    @Override
    public String login(LoginReq loginReq) {
        Admin admin = adminDao.getByUsername(loginReq);
        if (Objects.isNull(admin)) {
            // 没有用户，需要通知注册
            return null;
        }
        String signPwd = MD5.create().digestHex(loginReq.getPassword().getBytes());
        if (!signPwd.equals(admin.getPassword())) {
            // 密码错误
            return null;
        }
        // 登录成功，将用户保存到自定义的RequestContext中
        RequestContext.set(new RequestInfo(admin.getId()));

        // 创建token，并将token信息保存到redis中
        String token = jwtUtil.createToken(admin.getId());
        RedisUtil.set(RedisKey.getKey(RedisKey.ADMIN_INFO_STRING, admin.getId()), admin);

        return token;
    }
}
