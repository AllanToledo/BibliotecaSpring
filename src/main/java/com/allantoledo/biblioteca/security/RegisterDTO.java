package com.allantoledo.biblioteca.security;

public record RegisterDTO(String login, String password, UserRole role) {
}