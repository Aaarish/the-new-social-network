package com.roya.the_new_social_network.profiles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileDao extends JpaRepository<ProfileEntity, String> {
}
