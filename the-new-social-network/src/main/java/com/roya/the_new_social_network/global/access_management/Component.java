package com.roya.the_new_social_network.global.access_management;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @NoArgsConstructor @AllArgsConstructor @Builder
public class Component {
    private ComponentType componentType;
    private String componentId;

}
