package com.devin.love.music.service.v1;

import com.devin.love.music.domain.entity.Admin;
import com.devin.love.music.domain.vo.req.LoginReq;

import java.util.List;

/**
 * 2024/11/1 17:47
 * <p></p>
 *
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
public interface AdminService {

    /**
     * 登录验证
     * @param loginReq
     * @return
     */
    String login(LoginReq loginReq);
}
