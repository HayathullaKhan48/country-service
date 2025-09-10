package com.country.servcie.exceptions;

/**
 * Exception thrown when a country does not exist.
 */
public class CountryNotFoundException extends RuntimeException {
  public CountryNotFoundException(String message) {
    super(message);
  }
}
