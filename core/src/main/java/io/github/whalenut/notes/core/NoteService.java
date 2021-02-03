package io.github.whalenut.notes.core;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class NoteService {

    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public boolean save(Note note) {
        return noteRepository.save(note);
    }

    public boolean addTags(UUID id, Set<String> tags) {
        var note = noteRepository.find(id);
        Note updated = new Note(note, tags);
        return noteRepository.update(updated);
    }

    public List<Note> getAllNotes() {
        return new ArrayList<>(noteRepository.getAll());
    }

    public Note delete(UUID id) {
        return noteRepository.delete(id);
    }

    public Set<Note> getAllWithTag(String tag) {
        return noteRepository.getNotesWithTag(tag);
    }
}
