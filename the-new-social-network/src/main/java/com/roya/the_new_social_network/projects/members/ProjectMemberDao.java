package com.roya.the_new_social_network.projects.members;

import com.roya.the_new_social_network.global.access_management.projects.ProjectRole;
import com.roya.the_new_social_network.projects.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectMemberDao extends JpaRepository<ProjectMember, String> {
    List<ProjectMember> findByRoleAndProject(ProjectRole role, ProjectEntity project);

}
