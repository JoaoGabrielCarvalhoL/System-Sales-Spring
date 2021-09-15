package br.com.carv.sales.sales.security.jwt;

import br.com.carv.sales.sales.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class JWTService {

    private final String expiration = "30";

    private String subscriptionKey = "Sm/Do28gR2FicmllbCBDYXJ2YWxobyBMb3BlcyBkYSBDcnV6";

    public String generateToken(User user) {
        long expString = Long.valueOf(expiration);
        LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(expString);
        Date date = Date.from(expirationTime.atZone(ZoneId.systemDefault()).toInstant());

        return Jwts.builder().
                setSubject(user.getLoginUser()).
                setExpiration(date).
                signWith(SignatureAlgorithm.HS512, subscriptionKey).
                compact();
    }

    private Claims getClaims(String token) throws ExpiredJwtException {
        return Jwts.parser().setSigningKey(subscriptionKey).parseClaimsJws(token).getBody();

    }

    public Boolean validToken(String token) {
        try {
            Claims claims = getClaims(token);
            Date expirationDate = claims.getExpiration();
            LocalDateTime localDateTime = expirationDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            return !LocalDateTime.now().isAfter(localDateTime);

        } catch (Exception e) {
            return false;
        }
    }

    public String getLoginUser(String token) throws ExpiredJwtException {
        return (String) getClaims(token).getSubject();

    }
}
