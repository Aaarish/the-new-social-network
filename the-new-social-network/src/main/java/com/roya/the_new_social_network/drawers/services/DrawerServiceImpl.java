package com.roya.the_new_social_network.drawers.services;

import com.roya.the_new_social_network.global.resources.ResourceReference;
import com.roya.the_new_social_network.drawers.Drawer;
import com.roya.the_new_social_network.drawers.DrawerDao;
import com.roya.the_new_social_network.drawers.Note;
import com.roya.the_new_social_network.drawers.NoteDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class DrawerServiceImpl implements DrawerService {
    private final DrawerDao drawerDao;
    private final NoteDao noteDao;

    @Override
    public String createDrawer(ResourceReference resource, String shelfId) {
        Drawer drawer = Drawer.builder()
                .resourceTypeId(resource.getResourceTypeId())
                .resourceId(resource.getResourceId())
                .shelfId(shelfId)
                .build();

        Drawer savedDrawer = drawerDao.save(drawer);

        return savedDrawer.getId();
    }

    @Override
    public String addNoteToDrawer(String drawerId, String title, String content) {
        Drawer drawer = returnIfDrawerExists(drawerId);

        Note note = Note.builder()
                .drawer(drawer)
                .title(title)
                .content(content)
                .build();

        Note savedNote = noteDao.save(note);

        drawer.getNotes().add(note);
        drawerDao.save(drawer);

        return savedNote.getId();
    }

    @Override
    public String updateNoteInDrawer(String drawerId, String noteId, String content) {
        Drawer drawer = returnIfDrawerExists(drawerId);
        Note note = returnIfNoteExistsInDrawer(noteId, drawer);
        note.setContent(content);
        Note savedNote = noteDao.save(note);

        return savedNote.getId();
    }

    @Override
    public void removeNoteFromDrawer(String drawerId, String noteId) {
        Drawer drawer = returnIfDrawerExists(drawerId);
        Note note = returnIfNoteExistsInDrawer(noteId, drawer);

        Note found = drawer.getNotes().stream()
                .filter(n -> n.equals(note))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Note does not exist in this drawer"));

        drawer.getNotes().remove(found);
        drawerDao.save(drawer);

        noteDao.delete(found);
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

    private Note returnIfNoteExistsInDrawer(String noteId, Drawer drawer) {
        Note note = returnIfNoteExists(noteId);

        return drawer.getNotes().stream()
                .filter(n -> n.equals(note))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Note does not exist in this drawer"));
    }
}
