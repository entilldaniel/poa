package io.github.whalenut.notes.cli.commands;


import io.github.whalenut.notes.core.NoteRepository;
import picocli.CommandLine;

import javax.inject.Inject;

@CommandLine.Command(name = "list",
        description = "Lists all the notes")
public class ListCommand implements Runnable {

    private final NoteRepository noteRepository;

    @CommandLine.ParentCommand
    ParentCommand parent;

    @Inject
    public ListCommand(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public void run() {
        System.out.println("Listing all the notes");
    }
}
