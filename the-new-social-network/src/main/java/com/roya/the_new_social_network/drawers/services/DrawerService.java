package com.roya.the_new_social_network.drawers.services;

import com.roya.the_new_social_network.drawers.Drawer;
import com.roya.the_new_social_network.drawers.Note;
import com.roya.the_new_social_network.global.ComponentVisibility;

import java.util.List;

public interface DrawerService {
    Drawer createDrawer(String profileId, String projectId, String shelfId, ComponentVisibility visibility);

    Drawer getDrawer(String drawerId);

    List<Drawer> getDrawersOfProject(String projectId);

    List<Drawer> getDrawersOfProfile(String profileId);

    List<Drawer> getDrawersOfProjectMember(String profileId, String projectId);

    Drawer linkShelfToDrawer(String drawerId, String shelfId);

    //notes

    Note addNoteToDrawer(String drawerId, String title, String content);

    Note updateNoteInDrawer(String drawerId, String noteId, String content);

    void removeNoteFromDrawer(String drawerId, String noteId);

    void removeDrawer(String drawerId);

}
