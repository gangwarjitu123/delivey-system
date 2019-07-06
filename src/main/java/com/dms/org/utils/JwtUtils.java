package com.dms.org.utils;
import com.dms.org.exception.CustomDeliveryException;
import io.jsonwebtoken.*;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;

import java.util.*;

@UtilityClass
public class JwtUtils {

    public  String createJWT(String subject,String userType,Long userId ) throws Exception {
        JwtUser jwtUser=JwtUser.builder()
                .userName(subject)
                .userId(userId)
                .userType(userType)
                .build();

        JwtBuilder builder = Jwts.builder();
        builder.setHeaderParam("alg", SignatureAlgorithm.HS512);
        builder.setHeaderParam("typ", "JWT");
        Map<String,Object> map= new HashMap<String,Object>();
        map.put("jwtUser",jwtUser);
        builder.setClaims(map);
        builder.setSubject(subject);
        builder.setIssuedAt(new Date());
        builder.setExpiration(generateExpirationDate());
        builder.signWith(SignatureAlgorithm.HS512, "abc@Delivery");
        return builder.compact();

    }

    private Date generateExpirationDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 720);
        return calendar.getTime();
    }

    public JwtUser getJwtUser(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey("abc@Delivery").parseClaimsJws(token).getBody();
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new CustomDeliveryException("invalid token ", HttpStatus.UNAUTHORIZED);
        }
        LinkedHashMap<String,Object> linkedHashMap= (LinkedHashMap<String,Object>)claims.get("jwtUser");
        Integer userId=(Integer)linkedHashMap.get("userId");
        return JwtUser.builder()
                .userType(linkedHashMap.get("userType").toString())
                .userName(linkedHashMap.get("userName").toString())
                .userId(Long.valueOf(userId))
                .build();
    }

}
