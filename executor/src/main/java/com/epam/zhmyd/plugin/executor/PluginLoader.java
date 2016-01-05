package com.epam.zhmyd.plugin.executor;

import com.epam.zhmyd.plugin.api.Plugin;

import java.io.InputStream;
import java.lang.reflect.Method;


public class PluginLoader {

    private static final String DEFINE_CLASS = "defineClass";
    private static final String RESOLVE_CLASS = "resolveClass";
    private ClassLoader loader;
    private Method resolve;
    private Method define;

    public PluginLoader(ClassLoader loader) throws NoSuchMethodException {
        Class c = ClassLoader.class;
        define = c.getDeclaredMethod(DEFINE_CLASS, String.class, byte[].class, int.class, int.class);
        resolve = c.getDeclaredMethod(RESOLVE_CLASS, Class.class);
        define.setAccessible(true);
        resolve.setAccessible(true);
        this.loader=loader;
    }

    public Plugin loadPlugin(InputStream reader) {
        Plugin plugin = null;
        Class<?> clazz;
        Object instance;

        try {
            byte[] b = new byte[reader.available()];
            reader.read(b);
            reader.close();
            clazz = (Class<?>) define.invoke(loader, null, b, 0, b.length);
            resolve.invoke(loader, clazz);
            instance = clazz.newInstance();

            if (instance instanceof Plugin) {
                plugin = (Plugin) instance;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return plugin;
    }
}
