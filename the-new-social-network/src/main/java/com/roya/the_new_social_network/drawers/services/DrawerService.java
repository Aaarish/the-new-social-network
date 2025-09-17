package com.roya.the_new_social_network.drawers.services;

import com.roya.the_new_social_network.global.resources.ResourceReference;

public interface DrawerService {
    String createDrawer(ResourceReference resource, String shelfId);

    void removeDrawer(String drawerId);

    //notes

    String addNoteToDrawer(String drawerId, String title, String content);

    String updateNoteInDrawer(String drawerId, String noteId, String content);

    void removeNoteFromDrawer(String drawerId, String noteId);

}
