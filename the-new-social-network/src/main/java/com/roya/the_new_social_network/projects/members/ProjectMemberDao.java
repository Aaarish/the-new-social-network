package com.roya.the_new_social_network.projects.members;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectMemberDao extends JpaRepository<ProjectMember, String> {
}
