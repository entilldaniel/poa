package io.github.whalenut.notes.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class NoteServiceTest {

    private NoteService noteService;
    private NoteRepository noteRepository;

    @BeforeEach
    void setup() {
        noteRepository = mock(NoteRepository.class);
        noteService = new NoteService(noteRepository);
    }

    @Test
    void shouldSaveNote() {
        Note note = testNote();
        when(noteRepository.save(note)).thenReturn(true);

        boolean result = noteService.save(note);

        verify(noteRepository).save(note);
        assertTrue(result);
    }

    @Test
    public void shouldAddTagsToNote() {
        Note original = testNote();
        var newTags = Collections.singleton("foo");

        when(noteRepository.find(original.getId())).thenReturn(original);
        when(noteRepository.update(any())).thenReturn(true);

        var result = noteService.addTags(original.getId(), newTags);

        Assertions.assertTrue(result);
    }

    @Test
    void shouldDeleteNoteById() {
        Note note = testNote();
        when(noteRepository.delete(note.getId())).thenReturn(note);

        Note delete = noteService.delete(note.getId());

        assertEquals(note, delete);
    }

    @Test
    void shouldListAllNotes() {
        List<Note> notes = List.of(
                testNote(),
                testNote()
        );

        when(noteRepository.getAll()).thenReturn(notes);

        Collection<Note> result = noteService.getAllNotes();
        assertIterableEquals(notes, result);
    }


    private Note testNote() {
        return new Note("Test note", Collections.emptySet());
    }


}
