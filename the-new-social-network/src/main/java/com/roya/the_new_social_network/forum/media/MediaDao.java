package com.roya.the_new_social_network.forum.media;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaDao extends JpaRepository<Media, String> {
}
