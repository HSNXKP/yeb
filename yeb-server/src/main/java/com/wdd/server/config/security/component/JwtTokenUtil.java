package com.wdd.server.config.security.component;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: wdd
 * @date: 2022/9/22 15:52
 */
@Component
public class JwtTokenUtil {

    private static final String CLAIM_KEY_USERNAME="sub";
    private static final String CLAIM_KEY_CREATED="created";
    @Value("${jwt.secret}")
    private String secret;  //签名私钥
    @Value("${jwt.expiration}")
    private Long expiration;  //到期时间

    /**
     * 根据用户信息生成token
     * @param userDetails
     * @return
     */
    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME,userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED,new Date());
        return generateToken(claims);
    }


    /**
     * 根据token获得username
     * @param token
     * @return
     */
    public String getUserNameFormToken(String token){
        String username;
        try {
            Claims claims= getClaimsFormToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username=null;
        }
        return username;
    }


    /**
     * 从token获取荷载
     * @param token
     * @return
     */
    private Claims getClaimsFormToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            e.printStackTrace();


        }
        return claims;
    }




    /**
     * 根据荷载生成JWT json web token
     * @param claims
     * @return
     */
    private String generateToken(Map<String,Object> claims){
        return Jwts.builder()
                .setClaims(claims)  //放入荷载
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();

    }

    /**
     * 验证token是否有效
     * @param token
     * @param userDetails
     * @return
     */
    public Boolean validateToken(String token,UserDetails userDetails){
        String username = getUserNameFormToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);

    }

    /**
     * 判断token是否可以被刷新
     * @param token
     * @return
     */
    public Boolean canRefresh(String token){
        return !isTokenExpired(token);
    }

    /**
     * 刷新token
     * @param token
     * @return
     */
    public String refreshToken(String token){
        Claims claims = getClaimsFormToken(token);
        claims.put(CLAIM_KEY_CREATED,new Date());
        return generateToken(claims);
    }




    /**
     * 判断token是否到期
     * @param token
     * @return
     */
    private boolean isTokenExpired(String token) {
       Date expiration = getExpirationDateFormToken(token);
       return expiration.before(new Date());  //比较之前的时间
    }

    /**
     * 从token获取到期时间
     * @param token
     * @return
     */
    private Date getExpirationDateFormToken(String token) {
        Claims claims = getClaimsFormToken(token);
        return claims.getExpiration();

    }

    /**
     * 生成token的失效时间
     * @return
     */
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis()+expiration * 1000);
    }


}
