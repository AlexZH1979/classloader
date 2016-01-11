package com.epam.zhmyd.plugin.executor;

import java.util.Scanner;

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
        while (!exit) {
            Scanner in = new Scanner(System.in);
            System.out.print("Enter plugin name (or 'exit') : ");
            String name = in.nextLine();
            if (!name.equalsIgnoreCase(EXIT)) {
                performer.runPlugin(name);
            } else {
                exit = true;
            }
        }
    }
}
