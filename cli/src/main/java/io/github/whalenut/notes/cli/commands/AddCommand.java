package io.github.whalenut.notes.cli.commands;

import io.github.whalenut.notes.core.Note;
import io.github.whalenut.notes.core.NoteService;
import org.jline.reader.LineReader;
import picocli.CommandLine;

import javax.inject.Inject;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;

@CommandLine.Command(name = "add",
        description = "Add a note")
public class AddCommand implements Runnable {

    private final NoteService noteService;
    private final LineReader lineReader;

    @Inject
    public AddCommand(NoteService noteService, LineReader lineReader) {
        this.noteService = noteService;
        this.lineReader = lineReader;
    }

    @Override
    public void run() {
        lineReader.printAbove("What heading?");
        String heading = lineReader.readLine();
        lineReader.printAbove("What is your note? Finish with # on a separate line");
        StringBuilder sb = new StringBuilder();
        String contents = "";
        while (true) {
            contents = lineReader.readLine();
            if (contents.equals("#")) {
                contents = contents.substring(0, contents.length() - 1);
                sb.append(contents)
                        .append(System.lineSeparator());
                break;
            } else {
                sb.append(contents)
                        .append(System.lineSeparator());
            }
        }
        contents = sb.toString();
        System.out.println(contents);
        lineReader.printAbove("Add a comma separated list of tags:");
        String rawTags = lineReader.readLine();
        Set<String> tags = Stream.of(rawTags.split(",")).collect(toSet());
        noteService.save(new Note(heading, contents, tags));
        System.out.println("Note saved.");
    }
}
