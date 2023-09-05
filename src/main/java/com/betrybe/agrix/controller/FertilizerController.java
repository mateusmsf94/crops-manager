package com.betrybe.agrix.controller;

import com.betrybe.agrix.dto.FertilizerDto;
import com.betrybe.agrix.exception.FertilizerNotFoundException;
import com.betrybe.agrix.model.entities.Fertilizer;
import com.betrybe.agrix.service.FertilizerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Controller Fertilizers.
 */
@RestController
@RequestMapping(value = "/fertilizers")
public class FertilizerController {
  @Autowired
  private FertilizerService fertilizerService;

  public FertilizerController(FertilizerService fertilizerService) {
    this.fertilizerService = fertilizerService;
  }

  /**
   * Creates a new Fertilizer and saves it to the database.
   *
   * @param fertilizerDto the Fertilizer DTO containing the data.
   * @return ResponseEntity containing the created Fertilizer DTO.
   */
  @PostMapping
  public ResponseEntity<FertilizerDto> createFertilizer(@RequestBody FertilizerDto fertilizerDto) {
    Fertilizer newFertilizer = fertilizerService.insertFertilizer(fertilizerDto.toFertilizer());
    return ResponseEntity.status(HttpStatus.CREATED)
            .body(FertilizerDto.fromFertilizer(newFertilizer));
  }

  @GetMapping
  @Secured({"ADMIN"})
  public ResponseEntity<?> getAllFertilizers() {
    List<Fertilizer> fertilizers = fertilizerService.getAllFertilizers();
    return ResponseEntity.ok(fertilizers);
  }

  /**
   * Fetches a Fertilizer entity by its ID.
   *
   * @param id The ID of the Fertilizer entity to fetch.
   * @return ResponseEntity containing the Fertilizer entity if found,
   *         otherwise throws FertilizerNotFoundException.
   * @throws FertilizerNotFoundException if the Fertilizer entity is not found.
   */
  @GetMapping("/{id}")
  @Secured({"ADMIN"})
  public ResponseEntity<Fertilizer> getFertilizer(@PathVariable Long id) {
    Fertilizer fertilizer = fertilizerService.getFertilizer(id)
            .orElseThrow(FertilizerNotFoundException::new);
    return ResponseEntity.ok(fertilizer);
  }

  @ExceptionHandler(FertilizerNotFoundException.class)
  public ResponseEntity<String> handleFertilizerNotFoundException(FertilizerNotFoundException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
  }

}
