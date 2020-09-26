package io.github.whalenut.notes;

import java.time.LocalDateTime;

public class Note {
    private final LocalDateTime created;
    private final String note;

    public Note(LocalDateTime created, String note) {
        this.created = created;
        this.note = note;
    }
}
