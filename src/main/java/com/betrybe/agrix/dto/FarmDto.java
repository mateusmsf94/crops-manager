package com.betrybe.agrix.dto;

import com.betrybe.agrix.model.entities.Farm;

/**
 * Data Transfer Object for Farm.
 */
public record FarmDto(Long id, String name, float size) {
  public Farm toFarm() {
    return new Farm(id, name, size);
  }
}
