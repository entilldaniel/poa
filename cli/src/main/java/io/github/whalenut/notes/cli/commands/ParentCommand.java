package io.github.whalenut.notes.cli.commands;

import picocli.CommandLine.Command;

import javax.inject.Inject;

@Command(name = "",
        description = "A test",
        footer = {"", "Press Ctrl-D to exit."})
public class ParentCommand implements Runnable {

    public ParentCommand() {
    }


    @Override
    public void run() {
        //Dummy implementation.
    }
}
