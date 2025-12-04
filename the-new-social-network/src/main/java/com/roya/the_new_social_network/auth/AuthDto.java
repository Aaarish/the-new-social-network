package com.roya.the_new_social_network.auth;

import com.roya.the_new_social_network.profiles.api.dto.response.ProfileResponse;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

public class AuthDto {

    @Data
    @Builder
    public static class RegisterRequest {

        @NotBlank(message = "Username is required")
        @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
        private String username;

        @NotBlank(message = "Email is required")
        @Email(message = "Email should be valid")
        private String email;

        @NotBlank(message = "Password is required")
        @Size(min = 6, max = 100, message = "Password must be between 6 and 100 characters")
        private String password;

        @Size(max = 100, message = "Name cannot exceed 100 characters")
        private String name;

        private Long contact;

        @Size(max = 500, message = "Bio cannot exceed 500 characters")
        private String bio;
    }


    @Data
    @Builder
    public static class LoginRequest {

        @NotBlank(message = "Username is required")
        private String username;

        @NotBlank(message = "Password is required")
        private String password;
    }


    @Data
    @Builder
    public static class AuthResponse {
        private ProfileResponse profileResponse;
        private String token;
        private String expiresIn;
    }

}
