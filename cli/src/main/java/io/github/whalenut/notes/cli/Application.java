package io.github.whalenut.notes.cli;

import io.github.whalenut.notes.cli.config.DaggerCliFactory;
import io.github.whalenut.notes.cli.interact.CommandInterface;

public class Application {

    public static void main(String[] args) {
        CommandInterface commandInterface = DaggerCliFactory.create().commandInterface();
        commandInterface.start();
    }

}
