package com.epam.zhmyd.custompluginone;


import com.epam.zhmyd.plugin.api.Plugin;

public class CustomPluginOne implements Plugin {
    public void init() {
        System.out.println("Init custom plugin one");
    }

    public void apply() {
        System.out.println("Run custom plugin one");
    }
}
