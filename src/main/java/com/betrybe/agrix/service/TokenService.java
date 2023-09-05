package com.betrybe.agrix.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.betrybe.agrix.ebytr.staff.entity.Person;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Service class responsible for generating JWT tokens.
 */
@Service
public class TokenService {

  /**
   * Generates a JWT token for a given person.
   *
   * @param person the person for whom the token is generated.
   * @return the generated JWT token.
   */
  @Value("${api.security.token.secret}")
  private String secret;

  /**
   * Generates the expiration date for the JWT token.
   *
   * @return the expiration date in Instant format.
   */
  public String generateToken(Person person) {
    Algorithm algorithm = Algorithm.HMAC256(secret);
    return JWT.create()
            .withIssuer("agrix")
            .withSubject(person.getUsername())
            .withExpiresAt(generateExpirationDate())
            .sign(algorithm);
  }

  private Instant generateExpirationDate() {
    return LocalDateTime.now()
            .plusHours(2)
            .toInstant(ZoneOffset.of("-03:00"));
  }

  /**
   * Validates a given JWT token.
   *
   * @param token the JWT token to validate.
   * @return the username extracted from the token if it's valid.
   * @throws com.auth0.jwt.exceptions.JWTVerificationException if the token is invalid.
   */
  public String validateToken(String token) {
    Algorithm algorithm = Algorithm.HMAC256(secret);
    return JWT.require(algorithm)
            .withIssuer("agrix")
            .build()
            .verify(token)
            .getSubject();
  }
}
