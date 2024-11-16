package com.devin.love.music.common.enums;

import cn.hutool.http.ContentType;
import com.devin.love.music.common.utils.JsonUtil;
import com.devin.love.music.domain.vo.resp.ApiResult;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 2024/11/1 17:30
 * <p>
 * http 错误枚举
 * </p>
 *
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Getter
@AllArgsConstructor
@Slf4j
public enum HttpErrorEnum {
    SYS_ERROR(500, "系统错误"),
    ACCESS_DENIED(401, "登录失效"),
    INVALID_ERROR(400, "参数校验异常"),
    ;

    private final Integer errCode;
    private final String errMsg;

    /**
     * 发送给前端错误信息
     * @param response
     * @throws IOException
     */
    public void sendHttpError(HttpServletResponse response) throws IOException {
        response.setStatus(errCode);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JsonUtil.toStr(ApiResult.fail(errCode, errMsg)));
    }

    public Integer getErrCode() {
        return errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }
}
