package com.roya.the_new_social_network.apprenticeship;

import com.roya.the_new_social_network.profiles.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApprenticeDao extends JpaRepository<Apprentice, String> {
    Optional<Apprentice> findByProfileAndMentor(ProfileEntity apprenticeProfile, Mentor mentor);
    Optional<List<Apprentice>> findByProfile(ProfileEntity apprenticeProfile);

}
