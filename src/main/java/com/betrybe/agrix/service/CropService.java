package com.betrybe.agrix.service;

import com.betrybe.agrix.dto.CropDto;
import com.betrybe.agrix.exception.FarmNotFoundException;
import com.betrybe.agrix.model.entities.Crop;
import com.betrybe.agrix.model.entities.Farm;
import com.betrybe.agrix.model.repositories.CropRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class to manage crop-related operations.
 */
@Service
public class CropService {

  private CropRepository cropRepository;
  private FarmService farmService;

  @Autowired
  public CropService(CropRepository cropRepository, FarmService farmService) {
    this.cropRepository = cropRepository;
    this.farmService = farmService;
  }

  /**
   * Creates a new crop and associates it with a farm.
   *
   * @param farmId The ID of the farm to associate with the crop.
   * @param crop The crop to be created.
   * @return The newly created crop.
   */

  public Crop createCrop(Long farmId, Crop crop) {
    Farm farm = farmService.getFarm(farmId)
            .orElseThrow(FarmNotFoundException::new);
    crop.setFarm(farm);
    return cropRepository.save(crop);
  }

  /**
   * Retrieves all crops associated with the specified farm.
   *
   * @param farmId The ID of the farm for which crops are to be retrieved.
   * @return A list of {@link CropDto} objects representing crops associated with the farm.
   * @throws FarmNotFoundException If the specified farm is not found.
   */
  public List<CropDto> getAllCropByFarm(Long farmId) {
    Farm farm = farmService.getFarm(farmId)
            .orElseThrow(FarmNotFoundException::new);
    return cropRepository.findByFarm(farm)
            .stream()
            .map(CropDto::fromCrop)
            .collect(Collectors.toList());
  }

  /**
   * Retrieves all crops.
   */
  public List<CropDto> getAllCrops() {
    return cropRepository.findAll()
            .stream()
            .map(CropDto::fromCrop)
            .collect(Collectors.toList());
  }

  public Optional<Crop> getCrop(Long id) {
    return cropRepository.findById(id);
  }

  public List<Crop> getCropsByHarvestDateBetween(LocalDate begin, LocalDate end) {
    return cropRepository.findByHarvestDateBetween(begin, end);
  }
}
