package com.taskmanagement.domain.common.security;

import com.taskmanagement.domain.model.user.UserId;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.security.Key;
import io.jsonwebtoken.security.Keys;
@Component
public class TokenManager {
  private Key secretKey;

  public TokenManager(@Value("${app.token-secret-key}") String secretKey) {
    this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
  }

  /**
   * 사용자 ID를 제목으로 하는 JWT 생성
   * @param userId
   * @return
   */
  public String jwt(UserId userId) {
    return Jwts.builder()
      .setSubject(String.valueOf(userId.value()))
      .signWith(secretKey).compact();
  }

  /**
   * JWT 값에서 사용자 ID 가져오기
   * @param jws
   * @return
   */
  public UserId verifyJwt(String jws) {
    String userIdValue = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jws).getBody().getSubject();
    return new UserId(Long.valueOf(userIdValue));
  }
}
