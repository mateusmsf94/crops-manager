package com.betrybe.agrix.controller;

import com.betrybe.agrix.dto.PersonDto;
import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Person Controller.
 */
@RestController
@RequestMapping(value = "/persons")
public class PersonController {
  private PersonService personService;

  @Autowired
  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  /**
   * Creates a new person and returns the created person as a DTO.
   *
   * @param person the person details.
   * @return ResponseEntity containing the created person as a DTO.
   */
  @PostMapping
  public ResponseEntity<PersonDto> createPerson(@RequestBody Person person) {
    Person newPerson = personService.create(person);
    PersonDto responseDto = new PersonDto(
            newPerson.getId(),
            newPerson.getUsername(),
            newPerson.getRole().name()
    );
    return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
  }
}
