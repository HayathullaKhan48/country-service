package com.country.servcie.exceptions;

/**
 * Exception thrown when trying to create a country that already exists.
 */
public class CountryAlreadyExistsException extends RuntimeException {
  public CountryAlreadyExistsException(String message) {
    super(message);
  }
}
