package com.betrybe.agrix.dto;

import com.betrybe.agrix.model.entities.Crop;
import java.time.LocalDate;

/**
 * Data Transfer Object for Crop.
 */
public record CropDto(
        Long id,
        String name,
        float plantedArea,
        Long farmId,
        LocalDate plantedDate,
        LocalDate harvestDate) {

  /**
   * Creates a CropDto instance from a given Crop entity.
   *
   * @param crop The Crop entity to convert.
   * @return A new CropDto instance containing the same data as the given Crop entity.
   */
  public static CropDto fromCrop(Crop crop) {
    return new CropDto(crop.getId(),
            crop.getName(),
            crop.getPlantedArea(),
            crop.getFarm().getId(),
            crop.getPlantedDate(),
            crop.getHarvestDate());
  }
}
