package com.roya.the_new_social_network.auth;

import com.roya.the_new_social_network.forum.feed.FeedEntity;
import com.roya.the_new_social_network.profiles.ProfileDao;
import com.roya.the_new_social_network.profiles.ProfileEntity;
import com.roya.the_new_social_network.profiles.ProfileService;
import com.roya.the_new_social_network.profiles.api.dto.response.ProfileResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AuthService {
    private final ProfileDao profileDao;
    private final ProfileService profileService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @Value("${jwt.expiration}")
    private Long jwtExpiration;

    @Transactional
    public AuthDto.AuthResponse register(AuthDto.RegisterRequest request) {
        if (profileDao.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }

        if (profileDao.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        ProfileEntity profileRequest = createProfileRequest(request);
        ProfileEntity profile = profileService.createProfile(profileRequest);

        // Generate JWT token and return auth response
        return authenticate(
                AuthDto.LoginRequest.builder()
                        .username(profile.getUsername())
                        .password(request.getPassword())
                        .build(), profile
        );
    }

    private ProfileEntity createProfileRequest(AuthDto.RegisterRequest request) {
        String profileUrl = "www.roya.com/" + request.getUsername();

        ProfileEntity profile = ProfileEntity.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .contact(request.getContact())
                .bio(request.getBio())
                .profileUrl(profileUrl)
                .createdAt(LocalDateTime.now())
                .build();

        new FeedEntity(profile);
        return profile;
    }

    @Transactional
    public AuthDto.AuthResponse login(AuthDto.LoginRequest request) {
        ProfileEntity profile = profileDao.findByUsername(request.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // needs to update the last login time

        return authenticate(request, profile);
    }

    private AuthDto.AuthResponse authenticate(AuthDto.LoginRequest authRequest, ProfileEntity profile) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        String token = jwtUtil.generateToken(authentication);

        return AuthDto.AuthResponse.builder()
                .token(token)
                .expiresIn(LocalDateTime.now().plusSeconds(jwtExpiration).toString())
                .profileResponse(profile.toProfileResponse())
                .build();
    }

    public ProfileResponse getAuthenticatedUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<ProfileEntity> user = profileDao.findByUsername(username);

        if (user.isEmpty()) throw new IllegalStateException("Something is messed up in auth logic !!!");

        return user.get().toProfileResponse();
    }
}
