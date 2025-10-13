package com.roya.the_new_social_network.profiles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileDao extends JpaRepository<ProfileEntity, String> {
    Optional<ProfileEntity> findByUsername(String username);

    Optional<ProfileEntity> findByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

}
