package io.github.whalenut.spring.controllers;


import io.github.whalenut.notes.core.NoteService;
import org.springframework.stereotype.Controller;

@Controller
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }
}
