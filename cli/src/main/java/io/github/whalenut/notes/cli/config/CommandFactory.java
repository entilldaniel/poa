package io.github.whalenut.notes.cli.config;


import dagger.Component;
import dagger.Provides;
import io.github.whalenut.notes.cli.commands.AddCommand;
import io.github.whalenut.notes.cli.commands.ListCommand;
import io.github.whalenut.notes.cli.commands.ListFromTagsCommand;
import io.github.whalenut.notes.cli.commands.ParentCommand;
import io.github.whalenut.notes.cli.commands.TagsCommand;

import javax.inject.Singleton;

/**
 * Dagger will automatically generate a DaggerCliInterfaceFactory that we can use.
 */
@Singleton
@Component(modules = {CliModule.class, NoteRepositoryModule.class})
public interface CommandFactory {
    ParentCommand parentCommand();
    ListCommand listCommand();
    AddCommand addCommand();
    TagsCommand tagsCommand();
    ListFromTagsCommand listFromTagsCommand();
}
