package io.github.whalenut.notes.core;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.Set;
import java.util.UUID;

public class Note {
    private final UUID id;
    private final LocalDateTime created;
    private final String heading;
    private final String note;
    private final Set<String> tags;

    public Note(String heading, String note, Set<String> tags) {
        this.id = UUID.randomUUID();
        this.created = LocalDateTime.now(ZoneOffset.UTC);
        this.heading = heading;
        this.note = note;
        this.tags = tags;
    }

    // Copy constructor in order to add new tags.
    public Note(Note note, Set<String> tags) {
        this.id = note.getId();
        this.created = note.getCreated();
        this.heading = note.getHeading();
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

    public String getHeading() {
        return heading;
    }

    public String getNote() {
        return note;
    }

    public Set<String> getTags() {
        return Collections.unmodifiableSet(tags);
    }
}
