package com.country.service.exceptions;

/**
 * Generic exception for any entity (Country, State, City, etc.) not found.
 */
public class EntityNotFoundException extends RuntimeException {
  public EntityNotFoundException(String message) {
    super(message);
  }
}
