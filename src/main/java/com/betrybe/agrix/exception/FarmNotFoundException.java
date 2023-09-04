package com.betrybe.agrix.exception;

/**
 * Exception thrown when a farm is not found.
 */
public class FarmNotFoundException extends RuntimeException {
  public FarmNotFoundException() {
    super("Fazenda não encontrada!");
  }
}
