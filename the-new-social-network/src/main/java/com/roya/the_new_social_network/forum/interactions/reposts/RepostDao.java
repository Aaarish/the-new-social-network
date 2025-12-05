package com.roya.the_new_social_network.forum.interactions.reposts;

import com.roya.the_new_social_network.profiles.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepostDao extends JpaRepository<Repost, String> {
    List<Repost> findAllByUser(ProfileEntity user);
}
