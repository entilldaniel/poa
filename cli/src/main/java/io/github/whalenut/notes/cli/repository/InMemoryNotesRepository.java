package io.github.whalenut.notes.cli.repository;


import io.github.whalenut.notes.core.Note;
import io.github.whalenut.notes.core.NoteRepository;

import javax.inject.Inject;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import static java.util.stream.Collectors.toSet;

public class InMemoryNotesRepository implements NoteRepository {

    private final Map<UUID, Note> notes;

    @Inject
    public InMemoryNotesRepository() {
        notes = new HashMap<>();
    }

    @Override
    public boolean save(Note note) {
        notes.put(UUID.randomUUID(), note);
        return true;
    }

    @Override
    public Note find(UUID id) {
        return notes.get(id);
    }

    @Override
    public Note delete(UUID id) {
        return notes.remove(id);
    }

    @Override
    public boolean update(Note note) {
        return notes.computeIfPresent(note.getId(), (a, b) -> note) != null;
    }

    @Override
    public Collection<Note> getAll() {
        return notes.values();
    }

    @Override
    public Set<Note> getNotesWithTag(String tag) {
        return notes.values().stream()
                .filter(n -> n.getTags().contains(tag))
                .collect(toSet());
    }

}
