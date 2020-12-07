package io.github.whalenut.notes.cli.commands;


import io.github.whalenut.notes.core.NoteService;
import picocli.CommandLine;

import javax.inject.Inject;

@CommandLine.Command(name = "list",
        description = "Lists all the notes")
public class ListCommand implements Runnable {

    private final NoteService noteService;

    @CommandLine.ParentCommand
    ParentCommand parent;

    @Inject
    public ListCommand(NoteService noteService) {
        this.noteService = noteService;
    }

    public void run() {
        noteService.getAllNotes().stream()
                .forEach(n -> System.out.println(n.getNote()));
    }
}
