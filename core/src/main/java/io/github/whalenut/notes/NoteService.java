package io.github.whalenut.notes;

import java.util.List;
import java.util.Set;
import java.util.UUID;

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
        return noteRepository.getAll();
    }

    public Note delete(UUID id) {
        return noteRepository.delete(id);
    }
}
