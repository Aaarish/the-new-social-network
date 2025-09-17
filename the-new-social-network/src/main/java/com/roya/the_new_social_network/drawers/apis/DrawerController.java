package com.roya.the_new_social_network.drawers.apis;

import com.roya.the_new_social_network.drawers.services.DrawerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/drawers")
@RequiredArgsConstructor
public class DrawerController {
    private final DrawerService drawerService;

    @DeleteMapping("/{drawerId}")
    public ResponseEntity<String> removeDrawer(@PathVariable String drawerId) {
        drawerService.removeDrawer(drawerId);
        return ResponseEntity.ok("");
    }

    @PutMapping("/{drawerId}")
    public ResponseEntity<String> addNoteToDrawer(@PathVariable String drawerId, NoteRequestDto noteRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(drawerService.addNoteToDrawer(drawerId, noteRequest.getTitle(), noteRequest.getContent()));
    }

    @PutMapping("/{drawerId}/notes/{noteId}")
    public ResponseEntity<String> updateNoteToDrawer(@PathVariable String drawerId, @PathVariable String noteId, NoteRequestDto noteRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(drawerService.updateNoteInDrawer(drawerId, noteId, noteRequest.getContent()));
    }

    @DeleteMapping("/{drawerId}/notes/{noteId}")
    public ResponseEntity<String> removeNoteFromDrawer(@PathVariable String drawerId, @PathVariable String noteId) {
        drawerService.removeNoteFromDrawer(drawerId, noteId);
        return ResponseEntity.ok("");
    }
}
