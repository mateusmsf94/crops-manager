package com.betrybe.agrix.dto;

/**
 * Data Transfer Object for authentication.
 * Contains username and password for authentication.
 */
public record AuthenticationDto(String username, String password) {
}

