package com.roya.the_new_social_network.global.access_management;

import com.roya.the_new_social_network.profiles.ProfileDao;
import com.roya.the_new_social_network.projects.ProjectDao;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class AccessService {
    private final ProfileDao profileDao;
    private final ProjectDao projectDao;

    @Transactional
    public boolean hasAccess() {


        return false;
    }

}
