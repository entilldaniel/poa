package io.github.whalenut.notes.cli;

import io.github.whalenut.notes.core.NoteRepository;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.ParentCommand;

import javax.inject.Inject;

@Command(name = "",
        description = "A test",
        footer = {"", "Press Ctrl-D to exit."},
        subcommands = {CliInterface.FooCommand.class})
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

    @Command(name = "test",
            description = "A simple test")
    public static class FooCommand implements Runnable {

        @ParentCommand CliInterface parent;

        public FooCommand() {
            System.out.println("Creating the FooCommand.");
        }

        @Override
        public void run() {
            System.out.println("Hello World!");
        }
    }

}
