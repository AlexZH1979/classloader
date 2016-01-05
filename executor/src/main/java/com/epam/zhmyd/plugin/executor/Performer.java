package com.epam.zhmyd.plugin.executor;


import com.epam.zhmyd.plugin.api.Plugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Performer {

    private static final String PROPERTIES_FILE = "info.properties";
    public static final String PLUGIN_INFO = "plugin.info";
    public static final String PLUGIN_CLASS = "plugin.class";
    public static final String JAR_POSTFIX = ".jar";
    public static final String CLASS_POSTFIX = ".class";
    public static final String DOT = ".";
    public static final String SLASH = "/";
    public static final String PLUGIN = "Plugin: ";
    public static final String PLUGIN_NOT_FOUND = "Plugin not found";

    private Map<String, Plugin> plugins = new HashMap<>();


    public Performer(String name) {

        init(name);
    }

    public void showPlugins(){
        for (String key : plugins.keySet()) {
            System.out.println(PLUGIN + key);
        }
    }

    public void runPlugin(String id) {
        Plugin plugin = plugins.get(id);
        if (plugin != null) {
            plugin.init();
            plugin.apply();
        } else {
            System.out.println(PLUGIN_NOT_FOUND);
        }
    }

    private void init(String path) {
        File file = new File(path);
        if (file.isDirectory()) {
            for (File child : file.listFiles()) {
                parseFile(child);
            }
        } else {
            parseFile(file);
        }
    }

    private void parseFile(File file) {
        if (file.isFile() && file.getName().endsWith(JAR_POSTFIX)) {

            Properties properties = new Properties();
            URLClassLoader classLoader = null;

            PluginLoader pluginLoader = null;
            try {
                classLoader = new URLClassLoader(new URL[]{file.toURL()});

                pluginLoader = new PluginLoader(classLoader);
                InputStream url = classLoader.getResourceAsStream(PROPERTIES_FILE);
                if (url != null) {
                    properties.load(url);

                    String info = properties.getProperty(PLUGIN_INFO);
                    String pluginPath = properties.getProperty(PLUGIN_CLASS).replace(DOT, SLASH) + CLASS_POSTFIX;

                    InputStream is = classLoader.getResourceAsStream(pluginPath);

                    Plugin plugin = pluginLoader.loadPlugin(is);

                    plugins.put(info, plugin);

                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
