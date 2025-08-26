package com.roya.the_new_social_network.apprentices;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApprenticeDao extends JpaRepository<Apprentice, String> {
}
