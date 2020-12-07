package io.github.whalenut.notes.cli.interact;

import org.jline.console.SystemRegistry;
import org.jline.console.impl.Builtins;
import org.jline.reader.EndOfFileException;
import org.jline.reader.LineReader;
import org.jline.reader.MaskingCallback;
import org.jline.reader.UserInterruptException;

import javax.inject.Inject;

public class CommandInterface {

    private final static String prompt = "pao> ";
    private final static String rightPrompt = "";

    private final SystemRegistry systemRegistry;
    private final LineReader reader;

    @Inject
    public CommandInterface(SystemRegistry systemRegistry, LineReader reader, Builtins builtins) {
        this.systemRegistry = systemRegistry;
        this.reader = reader;

        builtins.setLineReader(reader);
    }

    public void start() {
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
        }}
}
