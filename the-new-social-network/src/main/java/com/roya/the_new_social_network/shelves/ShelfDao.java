package com.roya.the_new_social_network.shelves;

import com.roya.the_new_social_network.profiles.ProfileEntity;
import com.roya.the_new_social_network.projects.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShelfDao extends JpaRepository<Shelf, String> {
    List<Shelf> findByManager(ProfileEntity manager);

    List<Shelf> findByProject(ProjectEntity project);

    List<Shelf> findByProjectAndManager(ProjectEntity project, ProfileEntity manager);
}
