package com.roya.the_new_social_network.projects.members;

import com.roya.the_new_social_network.profiles.ProfileEntity;
import com.roya.the_new_social_network.projects.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectMemberDao extends JpaRepository<ProjectMember, String> {
    List<ProjectMember> findByRoleAndProject(ProjectRole role, ProjectEntity project);

    Optional<ProjectMember> findByProfileAndProject(ProfileEntity profile, ProjectEntity project);

}
