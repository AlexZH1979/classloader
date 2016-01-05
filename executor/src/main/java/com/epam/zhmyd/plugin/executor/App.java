package com.epam.zhmyd.plugin.executor;

import java.io.Console;
import java.io.IOException;
import java.net.URISyntaxException;

public class App {
    private static final String EXIT = "exit";

    public static void main(String[] args) {
        String path = args.length != 0 ? args[0] : System.getProperty("user.dir");
        if (path != null) {
            Performer performer = new Performer(path);
            performer.showPlugins();

            dispath(performer);
        }
    }

    public static void dispath(Performer performer) {
        boolean exit = false;
        String name = "";
        Console console = System.console();

        if (console == null) {
            System.out.println("Unable to fetch console");
            return;
        }

        while (!exit) {
            System.out.println("Enter plugin name");
            name = console.readLine();
            if(!name.equalsIgnoreCase(EXIT)){
                performer.runPlugin(name);
            } else {
                exit = true;
            }
        }
    }
}
