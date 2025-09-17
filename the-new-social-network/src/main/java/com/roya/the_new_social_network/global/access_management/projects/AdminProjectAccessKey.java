package com.roya.the_new_social_network.global.access_management.projects;

import com.roya.the_new_social_network.global.access_management.Component;
import com.roya.the_new_social_network.global.access_management.ComponentType;
import com.roya.the_new_social_network.projects.members.ProjectMember;
import jakarta.persistence.Transient;


public class AdminProjectAccessKey extends ProjectAccessKey {
    private String accessKey = generateAdminAccessKey();
    private ProjectMember projectMember;

    @Transient
    private Component component;


    private ComponentType componentType = component.getComponentType();
    private String componentId = component.getComponentId();

    private String generateAdminAccessKey() {
        if (projectMember == null) {
            throw new RuntimeException("ERROR : project cannot be null");
        }

        int permissionId = this.getPermission() == null ? 1 : this.getPermission().getPreference();

        return permissionId*10 + component.getComponentType().getTypeId() + "@" + component.getComponentId() + "@" + this.getProject().getProjectId();
    }

}
