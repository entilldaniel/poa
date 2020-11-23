package io.github.whalenut.notes.cli;

import io.github.whalenut.notes.cli.config.CommandFactory;
import io.github.whalenut.notes.cli.config.DaggerCommandFactory;
import org.jline.console.SystemRegistry;
import org.jline.console.impl.Builtins;
import org.jline.console.impl.SystemRegistryImpl;
import org.jline.reader.EndOfFileException;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.MaskingCallback;
import org.jline.reader.Parser;
import org.jline.reader.UserInterruptException;
import org.jline.reader.impl.DefaultParser;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import picocli.CommandLine;
import picocli.shell.jline3.PicocliCommands;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;

public class Application {

    private final static String prompt = "pao-cli> ";
    private final static String rightPrompt = null;

    public static void main(String[] args) {
        new Application(args);
    }

    public Application(String[] args) {
        CommandFactory commandFactory = DaggerCommandFactory.create();
        Parser parser = new DefaultParser();
        Path workDir = Paths.get(System.getProperty("user.dir"));

        Builtins builtins = new Builtins(Collections.emptySet(), workDir, null, null);

        CommandLine cmd = createCommands(commandFactory);
        PicocliCommands commands = new PicocliCommands(workDir, cmd);
        try (Terminal terminal = TerminalBuilder.builder().build()) {
            SystemRegistry systemRegistry = new SystemRegistryImpl(parser, terminal, () -> workDir, null);
            systemRegistry.setCommandRegistries(builtins, commands);

            LineReader reader = LineReaderBuilder.builder()
                    .terminal(terminal)
                    .completer(systemRegistry.completer())
                    .parser(parser)
                    .variable(LineReader.LIST_MAX, 50)   // max tab completion candidates
                    .build();
            builtins.setLineReader(reader);

            String line;
            while(true) {
                try {
                    systemRegistry.cleanUp();
                    line = reader.readLine(prompt, rightPrompt, (MaskingCallback) null, null);
                    systemRegistry.execute(line);
                } catch (UserInterruptException e) {
                    System.out.println(e.getMessage());
                } catch (EndOfFileException e) {
                    return;
                } catch (Exception e) {
                    systemRegistry.trace(e);
                }
            }

        } catch (IOException e) {
            System.out.println("bummer...");
        }
    }

    private CommandLine createCommands(CommandFactory commandFactory) {
        CommandLine parentCommand = new CommandLine(commandFactory.parentCommand());
        parentCommand.addSubcommand(commandFactory.listCommand());



        return parentCommand;
    }
}
