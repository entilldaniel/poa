package io.github.whalenut.notes.cli.commands;

import io.github.whalenut.notes.core.NoteService;
import picocli.CommandLine;

import javax.inject.Inject;
import java.util.stream.Collectors;

@CommandLine.Command(name = "tags",
        description = "List all tags")
public class TagsCommand implements Runnable {

    private final NoteService noteService;

    @Inject
    public TagsCommand(NoteService noteService) {
        this.noteService = noteService;
    }

    @Override
    public void run() {
        noteService.getAllNotes().stream()
                .flatMap(n -> n.getTags().stream())
                .collect(Collectors.toSet())
                .stream()
                .forEach(t -> System.out.println(t));
    }
}
