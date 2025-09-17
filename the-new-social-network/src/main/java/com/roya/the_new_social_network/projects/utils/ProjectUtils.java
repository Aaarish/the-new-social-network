package com.roya.the_new_social_network.projects.utils;

import com.roya.the_new_social_network.global.access_management.ComponentType;
import com.roya.the_new_social_network.global.access_management.Permission;
import com.roya.the_new_social_network.projects.ProjectDao;
import com.roya.the_new_social_network.projects.ProjectEntity;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class ProjectUtils {
    private final ProjectDao projectDao;

    public static Map<Permission, ComponentType> populateProjectAccessMap() {
        Map<Permission, ComponentType> map = new HashMap<>();

        for (Permission permission : Permission.values()) {
            for (ComponentType componentType : ComponentType.values()) {
                map.put(permission, componentType);
            }
        }

        return map;
    }

}
