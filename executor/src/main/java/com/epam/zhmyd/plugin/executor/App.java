package com.epam.zhmyd.plugin.executor;

import java.io.IOException;
import java.net.URISyntaxException;

public class App {
    public static void main(String[] args) throws InterruptedException, IOException, ClassNotFoundException, NoSuchMethodException, URISyntaxException {
        String path = args.length != 0 ? args[0] : System.getProperty("user.dir");
        if (path != null) {
            Performer performer = new Performer(path);
            performer.showPlugins();

            performer.runPlugin("custom plugin one");

            performer.runPlugin("custom plugin two");
        }



    }
}
