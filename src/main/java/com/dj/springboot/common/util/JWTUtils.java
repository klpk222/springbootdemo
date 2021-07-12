package com.dj.springboot.common.util;

import com.dj.springboot.common.enums.ResponseCode;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * API调用认证工具类，采用RSA加密
 *
 * @author yinjihuan
 */
@Component
public class JWTUtils {
    private static RSAPrivateKey priKey;
    private static RSAPublicKey pubKey;

    private static class SingletonHolder {
        private static final JWTUtils INSTANCE = new JWTUtils();
    }

    public synchronized static JWTUtils getInstance(String modulus, String privateExponent, String publicExponent) {
        if (priKey == null && pubKey == null) {
            priKey = RSAUtils.getPrivateKey(modulus, privateExponent);
            pubKey = RSAUtils.getPublicKey(modulus, publicExponent);
        }
        return SingletonHolder.INSTANCE;
    }

    public synchronized static void reload(String modulus, String privateExponent, String publicExponent) {
        priKey = RSAUtils.getPrivateKey(modulus, privateExponent);
        pubKey = RSAUtils.getPublicKey(modulus, publicExponent);
    }

    public synchronized static JWTUtils getInstance() {
        if (priKey == null && pubKey == null) {
            priKey = RSAUtils.getPrivateKey(RSAUtils.modulus, RSAUtils.private_exponent);
            pubKey = RSAUtils.getPublicKey(RSAUtils.modulus, RSAUtils.public_exponent);
        }
        return SingletonHolder.INSTANCE;
    }

    /**
     * 获取Token
     *
     * @param uid 用户ID
     * @param exp 失效时间，单位分钟
     * @return
     */
    public static String getToken(String uid, int exp) {
        long endTime = System.currentTimeMillis() + 1000 * 60 * exp;
        return Jwts.builder().setSubject(uid).setExpiration(new Date(endTime))
                .signWith(SignatureAlgorithm.RS512, priKey).compact();
    }

    /**
     * 获取Token
     *
     * @param userId 用户ID
     * @return
     */
    public String getToken(String userId, String userCompanyCode, String userName, String userType) {
        long endTime = System.currentTimeMillis() + 1000 * 60 * 1440;
        System.err.println(endTime);
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("userId", userId);
        claims.put("userCompanyCode", userCompanyCode);
        claims.put("userName", userName);
        claims.put("userType", userType);
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(endTime))
                .signWith(SignatureAlgorithm.RS512, priKey).compact();
    }

    /**
     * 获取Token
     * @param
     * @return
     */
    public String getToken(String username, String password) {
        long endTime = System.currentTimeMillis() + 1000 * 60 * 1440 * 365 * 100;//有效时间
        System.err.println(endTime);
        Map<String,Object> claims = new HashMap<String,Object>();
        claims.put("username", username);
        claims.put("password", password);
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.RS512, priKey).compact();
    }

    /**
     * 检查Token是否合法
     *
     * @param token
     * @return JWTResult
     */
    public JWTResult checkToken(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(pubKey).parseClaimsJws(token).getBody();
//			String sub = claims.get("sub", String.class);
            return new JWTResult(true, claims, "合法请求", ResponseCode.OK.getCode());
        } catch (ExpiredJwtException e) {
            // 在解析JWT字符串时，如果‘过期时间字段’已经早于当前时间，将会抛出ExpiredJwtException异常，说明本次请求已经失效
            return new JWTResult(false, null, "token已过期", ResponseCode.TOKEN_TIMEOUT_CODE.getCode());
        } catch (Exception e) {
            return new JWTResult(false, null, "非法请求", ResponseCode.NO_AUTH_CODE.getCode());
        }
    }

    @Data
    public static class JWTResult {
        private boolean status;
        private Claims claims;
        private String msg;
        private int code;

        public JWTResult() {
            super();
        }

        public JWTResult(boolean status, Claims claims, String msg, int code) {
            super();
            this.status = status;
            this.claims = claims;
            this.msg = msg;
            this.code = code;
        }
    }
}
