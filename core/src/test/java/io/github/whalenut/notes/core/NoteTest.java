package io.github.whalenut.notes.core;

import io.github.whalenut.notes.core.Note;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class NoteTest {

    @Test
    void shouldContainAllFields() {
        Note note = new Note("Heading", "Note", Collections.emptySet());

        assertNotNull(note.getId());
        assertEquals(note.getHeading(), "Heading");
        assertEquals(note.getNote(), "Note");
        assertTrue(note.getTags().isEmpty());
        assertTrue(note.getCreated().isBefore(LocalDateTime.now(ZoneOffset.UTC)));
    }

    @Test
    void shouldCreateNewNoteWithAddedTags() {
        Note note = new Note("Heading", "Note", new HashSet<>());
        var tags = new HashSet<>(Set.of("foo", "bar"));
        Note updated = new Note(note, tags);

        assertEquals(updated.getTags(), tags);
        assertEquals(note.getId(), updated.getId());
        assertEquals(note.getCreated(), updated.getCreated());
    }

}
