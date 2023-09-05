package com.betrybe.agrix.controller;

import com.betrybe.agrix.dto.AuthenticationDto;
import com.betrybe.agrix.dto.TokenDto;
import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.service.PersonService;
import com.betrybe.agrix.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller to manage authentication.
 */
@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {

  private final AuthenticationManager authenticationManager;

  private final PersonService personService;

  private final TokenService tokenService;

  /**
   * Constructor for the AuthenticationController.
   *
   * @param authenticationManager The authentication manager.
   * @param personService         The person service.
   * @param tokenService          The token service.
   */
  @Autowired
  public AuthenticationController(AuthenticationManager authenticationManager,
                                  PersonService personService,
                                  TokenService tokenService) {
    this.authenticationManager = authenticationManager;
    this.personService = personService;
    this.tokenService = tokenService;
  }

  /**
   * Authenticate a user and returns a token.
   *
   * @param authenticationDto DTO containing the username and password.
   * @return ResponseEntity with status and token.
   */
  @PostMapping("/login")
  public ResponseEntity<TokenDto> login(@RequestBody AuthenticationDto authenticationDto) {
    UsernamePasswordAuthenticationToken usernamePassword =
            new UsernamePasswordAuthenticationToken(
                    authenticationDto.username(),
                    authenticationDto.password());
    Authentication authentication = authenticationManager.authenticate(usernamePassword);
    Person person = (Person) authentication.getPrincipal();
    String token = tokenService.generateToken(person);

    return ResponseEntity.status(HttpStatus.OK).body(new TokenDto(token));
  }
}
