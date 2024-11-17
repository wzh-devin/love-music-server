package com.devin.love.music.common.exception;

import com.devin.love.music.common.enums.HttpErrorEnum;
import com.devin.love.music.common.utils.RequestContext;
import com.devin.love.music.domain.vo.resp.ApiResult;
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 2024/11/16 21:16
 * <p>
 * 全局异常处理器
 * </p>
 *
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final HttpServletResponse response;

    /**
     * 方法参数错误
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public void methodArgumentNotValidException(MethodArgumentNotValidException e) throws IOException {
        log.error("MethodArgumentNotValidException ===> {}", e.getMessage());
        HttpErrorEnum.INVALID_ERROR.sendHttpError(response);
    }
}
