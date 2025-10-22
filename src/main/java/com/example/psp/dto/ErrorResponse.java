package com.example.psp.dto;

import java.time.LocalDateTime;

public record ErrorResponse(String error, String message, LocalDateTime timestamp, String path) {
}