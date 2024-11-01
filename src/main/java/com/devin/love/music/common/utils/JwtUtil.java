package com.devin.love.music.common.utils;

import com.alibaba.druid.util.StringUtils;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

/**
 * 2024/11/1 18:59
 * <p>
 * jwt工具类
 * </p>
 *
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Component
public class JwtUtil {

    /**
     * Token密钥
     */
    @Value("${jwt.secret}")
    private String secret;

    public static final String UID_CLAIM = "uid";
    public static final String CREATE_TIME = "createTime";

    /**
     * 生成Token
     * @param uid
     * @return
     */
    public String createToken(Long uid) {
        return JWT.create()
                .withClaim(UID_CLAIM, uid)
                .withClaim(CREATE_TIME, new Date())
                .sign(Algorithm.HMAC256(secret));
    }

    /**
     * 解密token
     * @param token
     * @return
     */
    public Map<String, Claim> verifyToken(String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        return JWT.require(Algorithm.HMAC256(secret))
                .build()
                .verify(token)
                .getClaims();
    }

    /**
     * 从token中获取uid
     * @param token
     * @return
     */
    public Long getUidOrNull(String token) {
        return Optional.ofNullable(verifyToken(token))
                .map(claim -> claim.get("uid"))
                .map(Claim::asLong)
                .orElse(null);
    }
}
