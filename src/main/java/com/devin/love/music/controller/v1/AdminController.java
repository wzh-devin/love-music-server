package com.devin.love.music.controller.v1;

import com.devin.love.music.common.annotation.ApiV1;
import com.devin.love.music.domain.vo.req.LoginReq;
import com.devin.love.music.domain.vo.resp.ApiResult;
import com.devin.love.music.service.v1.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 2024/11/1 17:12
 * <p>
 * 后台用户管理控制器
 * </p>
 *
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@RestController
@ApiV1
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    /**
     * 登录接口
     * @param loginReq
     * @return
     */
    @PostMapping("/login")
    public ApiResult<String> login(@RequestBody LoginReq loginReq) {
        String token = adminService.login(loginReq);
        return ApiResult.success(token, null);
    }
}
