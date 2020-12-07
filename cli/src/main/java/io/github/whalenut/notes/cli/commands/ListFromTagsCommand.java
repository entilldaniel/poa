package io.github.whalenut.notes.cli.commands;

import io.github.whalenut.notes.core.NoteService;
import picocli.CommandLine;

import javax.inject.Inject;

@CommandLine.Command(name = "get-tag",
        description = "List notes from tags")
public class ListFromTagsCommand implements Runnable {

    private final NoteService noteService;

    @Inject
    public ListFromTagsCommand(NoteService noteService) {
        this.noteService = noteService;
    }

    @Override
    public void run() {

    }
}
