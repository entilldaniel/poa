package io.github.whalenut.notes.cli.commands;

import io.github.whalenut.notes.core.Note;
import io.github.whalenut.notes.core.NoteService;
import org.jline.reader.LineReader;
import picocli.CommandLine;

import javax.inject.Inject;
import java.util.Set;

@CommandLine.Command(name = "get-tag",
        description = "List notes from tags")
public class ListFromTagsCommand implements Runnable {

    private final NoteService noteService;
    private final LineReader lineReader;

    @Inject
    public ListFromTagsCommand(NoteService noteService, LineReader lineReader) {
        this.noteService = noteService;
        this.lineReader = lineReader;
    }

    @Override
    public void run() {
        lineReader.printAbove("enter the tag:");
        String tag = lineReader.readLine();
        Set<Note> notes = noteService.getAllWithTag(tag);
        notes.stream()
                .forEach(n -> System.out.println(n.getHeading()));

    }
}
