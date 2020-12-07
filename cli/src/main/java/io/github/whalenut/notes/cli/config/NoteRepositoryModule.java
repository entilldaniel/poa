package io.github.whalenut.notes.cli.config;

import dagger.Module;
import dagger.Provides;
import io.github.whalenut.notes.cli.repository.InMemoryNotesRepository;
import io.github.whalenut.notes.core.Note;
import io.github.whalenut.notes.core.NoteRepository;
import io.github.whalenut.notes.core.NoteService;

import javax.inject.Singleton;
import java.util.Set;

@Module
public abstract class NoteRepositoryModule {

    @Provides
    @Singleton
    public static NoteRepository noteRepository() {
        NoteRepository repository = new InMemoryNotesRepository();
        repository.save(new Note("Test", Set.of("foo", "bar", "baz")));
        repository.save(new Note("Test", Set.of("fizz")));
        repository.save(new Note("Test", Set.of("buzz")));
        repository.save(new Note("Test", Set.of("fizzbuzz")));


        return repository;
    }

    @Provides
    @Singleton
    public static NoteService notesService(NoteRepository noteRepository) {
        return new NoteService(noteRepository);
    }
}
