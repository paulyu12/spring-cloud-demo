package com.study.jjwt;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.Base64Codec;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class JjwtApplicationTest {

    /**
     * 生成 JWT
     */
    @Test
    public void testJwt() {

        JwtBuilder jwtBuilder = Jwts.builder()
                // 唯一 ID
                .setId("888")
                // 接受的用户 {“sub": "Rose" }
                .setSubject("Rose")
                // 签发时间 {”iat”: "xxxx-xx-xx"}
                .setIssuedAt(new Date())
                // 签名算法及密钥（盐）
                .signWith(SignatureAlgorithm.HS256, "salt_secret_xxxx");
                // jwt 包含的标准声明，包括： iss, sub, aud, exp, nbf, iat, jti 等

        String token = jwtBuilder.compact();
        System.out.println(token);

        System.out.println("==============================");

        String[] split = token.split("\\.");
        System.out.println(Base64Codec.BASE64.decodeToString(split[0]));
        System.out.println(Base64Codec.BASE64.decodeToString(split[1]));
        // 签名部分会乱码
        System.out.println(Base64Codec.BASE64.decodeToString(split[2]));
    }

}
