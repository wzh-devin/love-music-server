package com.devin.love.music.controller.v1;

import com.devin.love.music.common.annotation.ApiV1;
import com.devin.love.music.domain.entity.Admin;
import com.devin.love.music.domain.vo.req.LoginReq;
import com.devin.love.music.domain.vo.resp.ApiResult;
import com.devin.love.music.service.v1.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

@ApiV1
@RestController
@Api(tags = "后台用户管理")
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    /**
     * 登录接口
     *
     * @param loginReq
     * @return
     */
    @ApiOperation("登录接口")
    @PostMapping("/login")
    public ApiResult<String> login(@RequestBody LoginReq loginReq) {
        String token = adminService.login(loginReq);
        return ApiResult.success(token, null);
    }
}
