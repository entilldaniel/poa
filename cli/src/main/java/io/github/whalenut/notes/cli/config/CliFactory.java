package io.github.whalenut.notes.cli.config;

import dagger.Component;
import io.github.whalenut.notes.cli.interact.CommandInterface;

import javax.inject.Singleton;

@Singleton
@Component(modules = {CliModule.class, NoteRepositoryModule.class})
public interface CliFactory {
    CommandInterface commandInterface();
}
