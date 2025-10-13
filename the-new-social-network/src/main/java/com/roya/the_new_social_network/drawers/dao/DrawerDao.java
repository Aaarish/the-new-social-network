package com.roya.the_new_social_network.drawers.dao;

import com.roya.the_new_social_network.drawers.entities.Drawer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DrawerDao extends MongoRepository<Drawer, Long> {
    List<Drawer> findByProjectId(String projectId);

    List<Drawer> findByProfileId(String profileId);

    List<Drawer> findByProjectIdAndProfileId(String projectId, String profileId);
}
