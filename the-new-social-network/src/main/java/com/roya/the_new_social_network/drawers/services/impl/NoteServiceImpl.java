package com.roya.the_new_social_network.drawers.services.impl;

import com.roya.the_new_social_network.drawers.entities.Note;
import com.roya.the_new_social_network.drawers.dao.NoteDao;
import com.roya.the_new_social_network.drawers.services.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {
    private final NoteDao noteDao;

    public Note getNote(Long noteId) {
        return returnNoteFromId(noteId);
    }

    private Note returnNoteFromId(Long noteId) {
        return noteDao.findById(noteId)
                .orElseThrow(() -> new RuntimeException("Note not found"));
    }

}
