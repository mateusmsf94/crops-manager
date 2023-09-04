package com.betrybe.agrix.exception;

/**
 * Exception thrown when a fertilizer is not found.
 */
public class FertilizerNotFoundException extends RuntimeException {
  public FertilizerNotFoundException() {
    super("Fertilizante não encontrado!");
  }
}
