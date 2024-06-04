package org.example.tlias;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
//如果不需要整个spring项目都启动，可以把@SpringBootTest注解注释掉
//@SpringBootTest
class TliasApplicationTests {

    @Test
    public void uuid() {
        String uuid = UUID.randomUUID().toString();
        System.out.println(uuid);
    }

    @Test
    public void testGenJWT() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 1);
        claims.put("name", "tom");
        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, "willchan")//签名算法
                .setClaims(claims)//自定义内容（载荷）
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000))//设置有效期位1h
                .compact();
        System.out.println(jwt);
    }
//    @Test
//    public void testParseJwt(){
//        //篡改之后就会报错，测试无法通过，一旦Jwt生成了，无法篡改任何一个字符
//        Claims claims = Jwts.parser()
//                .setSigningKey("willchan")
//                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoidG9tIiwiaWQiOjEsImV4cCI6MTcxNzIzMjgyNn0.o2hssd-oJUv0-LiH3mhorG5_rRpYXU1Kd61j8HMiUS0")
//                .getBody();
//        System.out.println(claims);
//    }

}
