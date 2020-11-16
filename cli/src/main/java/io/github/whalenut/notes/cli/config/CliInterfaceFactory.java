package io.github.whalenut.notes.cli.config;


import dagger.Component;
import io.github.whalenut.notes.cli.CliInterface;

import javax.inject.Singleton;

/**
 * Dagger will automatically generate a DaggerCliInterfaceFactory that we can use.
 */
@Singleton
@Component(modules = {NoteRepositoryModule.class})
public interface CliInterfaceFactory {
    CliInterface cliInterface();
}
