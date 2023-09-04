package com.betrybe.agrix.dto;

/**
 * Data Transfer Object Wrapper.
 */
public record ResponseDto<T>(String message, T data) {
}
