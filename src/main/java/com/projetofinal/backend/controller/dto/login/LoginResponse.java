package com.projetofinal.backend.controller.dto.login;

public record LoginResponse(String accessToken, Long expiresIn) {
}
