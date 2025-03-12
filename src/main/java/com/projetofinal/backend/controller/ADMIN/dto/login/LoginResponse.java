package com.projetofinal.backend.controller.ADMIN.dto.login;

public record LoginResponse(String accessToken, Long expiresIn, String nome) {
}
