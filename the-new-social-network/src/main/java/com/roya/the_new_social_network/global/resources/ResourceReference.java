package com.roya.the_new_social_network.global.resources;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @NoArgsConstructor @AllArgsConstructor @Builder
public class ResourceReference {
    private Integer resourceTypeId;
    private String resourceId;

}
