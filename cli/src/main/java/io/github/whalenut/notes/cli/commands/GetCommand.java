package io.github.whalenut.notes.cli.commands;

import io.github.whalenut.notes.core.Note;
import io.github.whalenut.notes.core.NoteService;
import org.jline.reader.LineReader;
import picocli.CommandLine;

import javax.inject.Inject;
import java.util.Optional;
import java.util.stream.Collectors;

@CommandLine.Command(name = "get",
        description = "Get a note")
public class GetCommand implements Runnable {

    private final NoteService noteService;
    private final LineReader lineReader;

    @Inject
    public GetCommand(NoteService noteService, LineReader lineReader) {
        this.noteService = noteService;
        this.lineReader = lineReader;
    }

    @Override
    public void run() {
        lineReader.printAbove("Enter the heading?");
        String heading = lineReader.readLine();
        Optional<Note> note = noteService.getAllNotes()
                .stream()
                .filter(n -> n.getHeading().equals(heading))
                .findFirst();
        note.ifPresentOrElse(this::printNote, () -> System.out.println("Could not find that note"));
    }

    private void printNote(Note note) {
        System.out.println(note.getHeading());
        System.out.println("id: " + note.getId());
        System.out.println(note.getNote());
        System.out.println(note.getTags().stream().collect(Collectors.joining(",")));
        System.out.println("\n");
    }
}
