package com.roya.the_new_social_network.projects.services;

import com.roya.the_new_social_network.profiles.ProfileEntity;
import com.roya.the_new_social_network.projects.ProjectEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @NoArgsConstructor @AllArgsConstructor @Builder
public record WatcherRequestDto (ProfileEntity profile, ProjectEntity project) {
}
