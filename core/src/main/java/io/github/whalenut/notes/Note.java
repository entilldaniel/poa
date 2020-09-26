package io.github.whalenut.notes;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Set;
import java.util.UUID;

public class Note {
    private final UUID id;
    private final LocalDateTime created;
    private final String note;
    private final Set<String> tags;

    public Note(String note, Set<String> tags) {
        this.id = UUID.randomUUID();
        this.created = LocalDateTime.now(ZoneOffset.UTC);
        this.note = note;
        this.tags = tags;
    }

    public Note(Note note, Set<String> tags) {
        this.id = note.getId();
        this.created = note.getCreated();
        this.note = note.getNote();
        tags.addAll(note.getTags());
        this.tags = tags;
    }

    public UUID getId() {
        return id;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public String getNote() {
        return note;
    }

    public Set<String> getTags() {
        return tags;
    }
}
