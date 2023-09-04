package com.betrybe.agrix.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

/**
 * Represents a farm entity.
 */

@Entity
@Table(name = "farms")
public class Farm {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private float size;

  @OneToMany(mappedBy = "farm")
  private List<Crop> crops;

  public Farm() {
  }

  /**
   * Parameterized constructor.
   *
   * @param id   the ID of the farm
   * @param name the name of the farm
   * @param size the size of the farm
   */
  public Farm(Long id, String name, float size) {
    this.id = id;
    this.name = name;
    this.size = size;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public float getSize() {
    return size;
  }

  public void setSize(float size) {
    this.size = size;
  }
}
