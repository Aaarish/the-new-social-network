package com.roya.the_new_social_network.forum.feed;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedDao extends JpaRepository<FeedEntity, String> {

    @Query("SELECT f FROM FeedEntity f WHERE f.profile.profileId = :profileId")
    FeedEntity findByProfileId(@Param("profileId") String profileId);
}
