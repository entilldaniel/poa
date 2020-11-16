package io.github.whalenut.notes.core;

import java.util.List;
import java.util.UUID;

public interface NoteRepository {
    boolean save(Note note);

    Note find(UUID id);

    Note delete(UUID id);

    boolean update(Note note);

    List<Note> getAll();
}
