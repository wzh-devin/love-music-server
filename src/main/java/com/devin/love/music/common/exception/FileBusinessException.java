package com.devin.love.music.common.exception;

/**
 * 2024/11/22 17:28
 * <p>
 * 文件异常
 * </p>
 *
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
public class FileBusinessException extends RuntimeException {
    public FileBusinessException(String message) {
        super(message);
    }
}
