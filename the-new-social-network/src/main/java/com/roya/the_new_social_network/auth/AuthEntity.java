package com.roya.the_new_social_network.auth;

import com.roya.the_new_social_network.profiles.ProfileEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class AuthEntity extends ProfileEntity implements UserDetails {
    private ProfileEntity profile;

    public AuthEntity(ProfileEntity profile) {
        this.profile = profile;
    }

    public ProfileEntity getProfile() {
        return profile;
    }

    @Override
    public String getPassword() {
        return profile.getPassword();
    }

    @Override
    public String getUsername() {
        return profile.getUsername();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
