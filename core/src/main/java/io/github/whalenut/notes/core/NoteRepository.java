package io.github.whalenut.notes.core;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;

public interface NoteRepository {
    boolean save(Note note);

    Note find(UUID id);

    Note delete(UUID id);

    boolean update(Note note);

    Collection<Note> getAll();

    Set<Note> getNotesWithTag(String tag);
}
