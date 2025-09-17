package com.roya.the_new_social_network.projects.members;

import com.roya.the_new_social_network.projects.ProjectEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectMemberServiceImpl implements ProjectMemberService {
    public final ProjectMemberDao projectMemberDao;

    @Override
    public ProjectMember addMember(ProjectEntity project, ProjectMemberRequestDto requestDto) {
        return projectMemberDao.save(requestDto.toEntity());
    }
}
