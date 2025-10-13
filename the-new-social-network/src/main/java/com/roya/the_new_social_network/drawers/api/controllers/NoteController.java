package com.roya.the_new_social_network.drawers.api.controllers;

import com.roya.the_new_social_network.drawers.entities.Drawer;
import com.roya.the_new_social_network.drawers.entities.Note;
import com.roya.the_new_social_network.drawers.api.dto.request.CreateNoteRequest;
import com.roya.the_new_social_network.drawers.api.dto.request.UpdateNoteRequest;
import com.roya.the_new_social_network.drawers.api.dto.response.NoteResponse;
import com.roya.the_new_social_network.drawers.services.DrawerService;
import com.roya.the_new_social_network.drawers.services.NoteService;
import com.roya.the_new_social_network.global.dto.GlobalDeleteResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/drawers")
@RequiredArgsConstructor
public class NoteController {
    private final DrawerService drawerService;
    private final NoteService noteService;

    /**
     * Add a note to a drawer
     * POST /api/v1/drawers/{drawerId}/notes
     */
    @PostMapping("/{drawerId}/notes")
    public ResponseEntity<NoteResponse> addNoteToDrawer(@PathVariable @NotBlank String drawerId,
                                                        @RequestBody @Valid CreateNoteRequest request) {
        Note note = drawerService.addNoteToDrawer(
                drawerId,
                request.getTitle(),
                request.getContent()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(note.toNoteResponse());
    }

    /**
     * Update a note in a drawer
     * PUT /api/v1/drawers/{drawerId}/notes/{noteId}
     */
    @PutMapping("/{drawerId}/notes/{noteId}")
    public ResponseEntity<NoteResponse> updateNoteInDrawer(@PathVariable @NotBlank String drawerId,
                                                           @PathVariable @NotBlank String noteId,
                                                           @RequestBody @Valid UpdateNoteRequest request) {
        Note note = drawerService.updateNoteInDrawer(
                drawerId,
                noteId,
                request.getContent()
        );

        return ResponseEntity.ok(note.toNoteResponse());
    }

    /**
     * Remove a note from a drawer
     * DELETE /api/v1/drawers/{drawerId}/notes/{noteId}
     */
    @DeleteMapping("/{drawerId}/notes/{noteId}")
    public ResponseEntity<GlobalDeleteResponse> removeNoteFromDrawer(@PathVariable @NotBlank String drawerId,
                                                                     @PathVariable @NotBlank String noteId) {
        drawerService.removeNoteFromDrawer(drawerId, noteId);

        GlobalDeleteResponse response = GlobalDeleteResponse.builder()
                .success(true)
                .message("Note deleted successfully")
                .resourceId(noteId)
                .deletedAt(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{drawerId}/notes")
    public ResponseEntity<List<NoteResponse>> getNotesInDrawer(@PathVariable String drawerId) {
        Drawer drawer = drawerService.getDrawer(drawerId);

        List<NoteResponse> notes = drawer.getNotes().stream()
                .map(Note::toNoteResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(notes);
    }

    @GetMapping("/notes/{noteId}")
    public ResponseEntity<NoteResponse> getNote(@PathVariable String noteId) {
        return ResponseEntity.ok(noteService.getNote(Long.parseLong(noteId)).toNoteResponse());
    }

}
