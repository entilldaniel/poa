package io.github.whalenut.notes.cli.commands;

import io.github.whalenut.notes.core.NoteService;
import picocli.CommandLine;

import javax.inject.Inject;

@CommandLine.Command(name = "add",
        description = "Add a note")
public class AddCommand implements Runnable {

    private final NoteService noteService;

    @Inject
    public AddCommand(NoteService noteService) {
        this.noteService = noteService;
    }

    @Override
    public void run() {

    }
}
