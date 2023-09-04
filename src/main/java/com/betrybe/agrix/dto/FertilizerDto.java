package com.betrybe.agrix.dto;

import com.betrybe.agrix.model.entities.Fertilizer;

/**
 * Data Transfer Object for Fertilizer.
 */
public record FertilizerDto(Long id, String name, String brand, String composition) {

  /**
   * Converts a Fertilizer entity to a FertilizerDto object.
   *
   * @param fertilizer the Fertilizer entity to be converted.
   * @return the corresponding FertilizerDto object.
   */
  public static FertilizerDto fromFertilizer(Fertilizer fertilizer) {
    return new FertilizerDto(
            fertilizer.getId(),
            fertilizer.getName(),
            fertilizer.getBrand(),
            fertilizer.getComposition());
  }

  /**
   * Converts a FertilizerDto object to a Fertilizer entity.
   *
   * @return the corresponding Fertilizer entity.
   */
  public Fertilizer toFertilizer() {
    return new Fertilizer(id, name, brand, composition);
  }
}
