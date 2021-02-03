package io.github.whalenut.notes.cli.config;

import dagger.Module;
import dagger.Provides;
import io.github.whalenut.notes.cli.commands.AddCommand;
import io.github.whalenut.notes.cli.commands.GetCommand;
import io.github.whalenut.notes.cli.commands.ListCommand;
import io.github.whalenut.notes.cli.commands.ListFromTagsCommand;
import io.github.whalenut.notes.cli.commands.ParentCommand;
import io.github.whalenut.notes.cli.commands.TagsCommand;
import org.jline.console.SystemRegistry;
import org.jline.console.impl.Builtins;
import org.jline.console.impl.SystemRegistryImpl;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.Parser;
import org.jline.reader.impl.DefaultParser;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import picocli.CommandLine;
import picocli.shell.jline3.PicocliCommands;

import javax.inject.Singleton;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;

@Module
public abstract class CliModule {

    @Provides
    @Singleton
    public static SystemRegistry systemRegistry(Parser parser,
                                                Terminal terminal,
                                                Path workDir,
                                                Builtins builtins) {
        SystemRegistry systemRegistry = new SystemRegistryImpl(parser, terminal, () -> workDir, null);
        systemRegistry.setCommandRegistries(builtins);
        return systemRegistry;
    }

    @Singleton
    @Provides
    public static LineReader lineReader(Terminal terminal,
                                        SystemRegistry systemRegistry,
                                        Parser parser) {
        return LineReaderBuilder.builder()
                .terminal(terminal)
                .completer(systemRegistry.completer())
                .parser(parser)
                .variable(LineReader.LIST_MAX, 50)
                .build();
    }

    @Provides
    @Singleton
    public static Terminal terminal() {
        try {
            return TerminalBuilder.builder().build();
        } catch (IOException e) {
            throw new RuntimeException("Could not start.", e);
        }
    }

    @Provides
    @Singleton
    public static Parser parser() {
        return new DefaultParser();
    }

    @Provides
    @Singleton
    public static Path workDir() {
        return Paths.get(System.getProperty("user.dir"));
    }


    @Provides
    @Singleton
    public static Builtins builtins(Path workDir) {
        return new Builtins(Collections.emptySet(), workDir, null, null);
    }

    @Provides
    @Singleton
    public static PicocliCommands picocliCommands(Path workDir,
                                                  ParentCommand parentCommand,
                                                  ListCommand listCommand,
                                                  AddCommand addCommand,
                                                  TagsCommand tagsCommand,
                                                  ListFromTagsCommand listFromTagsCommand,
                                                  GetCommand getCommand) {
        CommandLine commandLine = new CommandLine(parentCommand);
        commandLine.addSubcommand(listCommand);
        commandLine.addSubcommand(addCommand);
        commandLine.addSubcommand(tagsCommand);
        commandLine.addSubcommand(listFromTagsCommand);
        commandLine.addSubcommand(getCommand);

        return new PicocliCommands(workDir, commandLine);
    }


}
