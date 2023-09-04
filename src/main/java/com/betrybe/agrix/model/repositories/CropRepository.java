package com.betrybe.agrix.model.repositories;

import com.betrybe.agrix.model.entities.Crop;
import com.betrybe.agrix.model.entities.Farm;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing Crop entities.
 */
@Repository
public interface CropRepository extends JpaRepository<Crop, Long> {
  List<Crop> findByFarm(Farm farm);

  List<Crop> findByHarvestDateBetween(LocalDate start, LocalDate end);
}
