package com.devin.love.music.common.exception;

import com.devin.love.music.common.enums.HttpErrorEnum;
import com.devin.love.music.domain.vo.resp.ApiResult;
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
public class GlobalExceptionHandler {

    /**
     * 参数校验异常
     * @param e
     * @return
     */
    @ExceptionHandler(InvalidDefinitionException.class)
    public ApiResult<String> invalidDefinitionException(InvalidDefinitionException e) {
        log.error("InvalidDefinitionException ===> {}", e.getMessage());
        return ApiResult.fail(HttpErrorEnum.INVALID_ERROR.getErrCode(), e.getMessage());
    }

    /**
     * 方法参数错误
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResult<String> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("MethodArgumentNotValidException ===> {}", e.getMessage());
        return ApiResult.fail(HttpErrorEnum.INVALID_ERROR.getErrCode(), e.getMessage());
    }
}
