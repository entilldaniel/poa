package io.github.whalenut.notes.cli.config;

import dagger.Module;
import dagger.Provides;
import io.github.whalenut.notes.cli.repository.InMemoryNotesRepository;
import io.github.whalenut.notes.core.NoteRepository;

import javax.inject.Singleton;

@Module
public abstract class NoteRepositoryModule {

    @Provides
    @Singleton
    public static NoteRepository noteRepository() {
        return new InMemoryNotesRepository();
    }
}
