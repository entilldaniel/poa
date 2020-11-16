package io.github.whalenut.notes.cli.repository;


import io.github.whalenut.notes.core.Note;
import io.github.whalenut.notes.core.NoteRepository;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class InMemoryNotesRepository implements NoteRepository {

    private final Map<UUID, Note> notes;

    @Inject
    public InMemoryNotesRepository() {
        notes = new HashMap<>();
    }

    @Override
    public boolean save(Note note) {
        return false;
    }

    @Override
    public Note find(UUID id) {
        return null;
    }

    @Override
    public Note delete(UUID id) {
        return null;
    }

    @Override
    public boolean update(Note note) {
        return false;
    }

    @Override
    public List<Note> getAll() {
        return null;
    }
}
