package com.roya.the_new_social_network.global.access_management.projects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @NoArgsConstructor @AllArgsConstructor
public enum ProjectRole {
    CREATOR(1),
    ADMIN(2),
    MEMBER(3),
    WATCHER(4),
    APPLICANT(5);

    private int projectRoleId;

}
