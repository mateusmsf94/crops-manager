package com.betrybe.agrix.controller;

import com.betrybe.agrix.dto.CropDto;
import com.betrybe.agrix.exception.CropNotFoundException;
import com.betrybe.agrix.exception.FarmNotFoundException;
import com.betrybe.agrix.model.entities.Crop;
import com.betrybe.agrix.service.CropService;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class to manage crop-related operations.
 */
@RestController
public class CropController {

  private CropService cropService;

  @Autowired
  public CropController(CropService cropService) {
    this.cropService = cropService;
  }

  /**
   * Creates a new crop and associates it with a farm.
   *
   * @param farmId The ID of the farm to associate with the crop.
   * @param crop The crop to be created.
   * @return ResponseEntity containing the newly created crop.
   */

  @PostMapping("/farms/{farmId}/crops")
  public ResponseEntity<?> createCrop(@PathVariable Long farmId, @RequestBody Crop crop) {
    try {
      Crop newCrop = cropService.createCrop(farmId, crop);
      CropDto cropDto = CropDto.fromCrop(newCrop);
      return ResponseEntity.status(HttpStatus.CREATED).body(cropDto);
    } catch (FarmNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fazenda não encontrada!");
    }
  }

  /**
   * Retrieves all crops associated with a specific farm.
   *
   * @param farmId The ID of the farm to find crops for.
   * @return A list of crops associated with the specified farm.
   * @throws FarmNotFoundException if no farm with the given ID is found.
   */

  @GetMapping("/farms/{farmId}/crops")
  public ResponseEntity<?> getAllCropsByFarm(@PathVariable Long farmId) {
    try {
      List<CropDto> crops = cropService.getAllCropByFarm(farmId);
      return ResponseEntity.ok(crops);
    } catch (FarmNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fazenda não encontrada!");
    }
  }

  @GetMapping("/crops")
  public ResponseEntity<?> getAllCrops() {
    List<CropDto> crops = cropService.getAllCrops();
    return ResponseEntity.ok(crops);
  }

  /**
   * Retrieves a specific crop by its ID.
   *
   * @param id The ID of the crop to retrieve.
   * @return ResponseEntity containing the crop DTO.
   * @throws CropNotFoundException if no crop with the given ID is found.
   */
  @GetMapping("/crops/{id}")
  public ResponseEntity<CropDto> getCrop(@PathVariable Long id) {
    Crop crop = cropService.getCrop(id).orElseThrow(CropNotFoundException::new);
    CropDto cropDto = CropDto.fromCrop(crop);
    return ResponseEntity.ok(cropDto);
  }

  /**
   * Searches for crops harvested between the specified start and end dates.
   *
   * @param start The start date for the search in {@link DateTimeFormat.ISO.DATE} format.
   * @param end The end date for the search in {@link DateTimeFormat.ISO.DATE} format.
   * @return A {@link ResponseEntity} containing a list of {@link CropDto} objects representing
   *         crops harvested between the specified dates.
   */
  @GetMapping("/crops/search")
  public ResponseEntity<List<CropDto>> searchCropsByDateBetween(
          @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
          @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {

    List<Crop> crops = cropService.getCropsByHarvestDateBetween(start, end);
    List<CropDto> cropDtos = crops.stream()
            .map(CropDto::fromCrop)
            .collect(Collectors.toList());
    return ResponseEntity.ok(cropDtos);
  }


  @ExceptionHandler(CropNotFoundException.class)
  public ResponseEntity<String> handleCropNotFoundException(CropNotFoundException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
  }
}
