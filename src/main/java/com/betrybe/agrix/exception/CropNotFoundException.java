package com.betrybe.agrix.exception;

/**
 * Exception thrown when a farm is not found.
 */
public class CropNotFoundException extends RuntimeException {
  public CropNotFoundException() {
    super("Plantação não encontrada!");
  }
}