package io.github.whalenut.notes.cli.config;


import dagger.Component;
import io.github.whalenut.notes.cli.commands.ParentCommand;
import io.github.whalenut.notes.cli.commands.ListCommand;

import javax.inject.Singleton;

/**
 * Dagger will automatically generate a DaggerCliInterfaceFactory that we can use.
 */
@Singleton
@Component(modules = {NoteRepositoryModule.class})
public interface CommandFactory {
    ParentCommand parentCommand();
    ListCommand listCommand();
}
