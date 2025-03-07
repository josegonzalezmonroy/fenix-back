package com.projetofinal.backend.resources.dto;

public record LoginResponse(String accessToken, Long expiresIn) {
}
