package com.roya.the_new_social_network.drawers.services;

import com.roya.the_new_social_network.global.ComponentVisibility;
import com.roya.the_new_social_network.drawers.Drawer;
import com.roya.the_new_social_network.drawers.DrawerDao;
import com.roya.the_new_social_network.drawers.Note;
import com.roya.the_new_social_network.drawers.NoteDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class DrawerServiceImpl implements DrawerService {
    private final DrawerDao drawerDao;
    private final NoteDao noteDao;

    @Override
    public Drawer createDrawer(String profileId, String projectId, String shelfId, ComponentVisibility visibility) {
        Drawer drawer = Drawer.builder()
                .projectId(projectId)
                .profileId(profileId)
                .shelfId(shelfId)
                .visibility(visibility)
                .build();

        return drawerDao.save(drawer);
    }

    @Override
    public Drawer getDrawer(String drawerId) {
        return returnIfDrawerExists(drawerId);
    }

    @Override
    public List<Drawer> getDrawersOfProject(String projectId) {
        return drawerDao.findByProjectId(projectId);
    }

    @Override
    public List<Drawer> getDrawersOfProfile(String profileId) {
        return drawerDao.findByProfileId(profileId);
    }

    @Override
    public List<Drawer> getDrawersOfProjectMember(String profileId, String projectId) {
        return drawerDao.findByProjectIdAndProfileId(projectId, profileId);
    }

    @Override
    @Transactional
    public Drawer linkShelfToDrawer(String drawerId, String shelfId) {
        Drawer drawer = returnIfDrawerExists(drawerId);

        drawer.setShelfId(shelfId);
        return drawerDao.save(drawer);
    }

    @Override
    @Transactional
    public Note addNoteToDrawer(String drawerId, String title, String content) {
        Drawer drawer = returnIfDrawerExists(drawerId);

        Note note = Note.builder()
                .title(title)
                .content(content)
                .drawer(drawer)
                .build();

        Note savedNote = noteDao.save(note);
        drawer.getNotes().add(savedNote);

        return savedNote;
    }

    @Override
    @Transactional
    public Note updateNoteInDrawer(String drawerId, String noteId, String content) {
        return null;
    }

    @Override
    public void removeNoteFromDrawer(String drawerId, String noteId) {
        Drawer drawer = returnIfDrawerExists(drawerId);
        Note note = returnIfNoteExistsInDrawer(noteId, drawer);

        drawer.getNotes().remove(note);
        noteDao.delete(note);
    }

    @Override
    public void removeDrawer(String drawerId) {
        Drawer drawer = returnIfDrawerExists(drawerId);
        drawerDao.delete(drawer);
    }


    private Drawer returnIfDrawerExists(String drawerId) {
        return drawerDao.findById(Long.parseLong(drawerId))
                .orElseThrow(() -> new IllegalArgumentException("Drawer not found"));
    }

    private Note returnIfNoteExists(String noteId) {
        return noteDao.findById(Long.parseLong(noteId))
                .orElseThrow(() -> new IllegalArgumentException("Note not found"));
    }


    // similar method to be implemented in ShelfServiceImpl (to check if a section belongs to a shelf)
    private Note returnIfNoteExistsInDrawer(String noteId, Drawer drawer) {
        Note note = returnIfNoteExists(noteId);

        return drawer.getNotes().stream()
                .filter(n -> n == note)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Note does not exist in this drawer"));
    }
}
