package io.github.whalenut.notes.cli;

import io.github.whalenut.notes.core.NoteRepository;
import picocli.CommandLine.Command;

import javax.inject.Inject;

@Command(name = "test",
        description = "A test",
        footer = {"", "Press Ctrl-D to exit."})
public class CliInterface implements Runnable {

    private final NoteRepository noteRepository;

    @Inject
    public CliInterface(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
        System.out.println("Hello, World.");
    }


    @Override
    public void run() {
        System.out.println("Command is run.");
    }
}
