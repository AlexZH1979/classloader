package com.epam.zhmyd.customplugintwo;


import com.epam.zhmyd.plugin.api.Plugin;

public class CustomPluginTwo implements Plugin {
    public void init() {
        System.out.println("Init custom plugin two");
    }

    public void apply() {
        System.out.println("Run custom plugin two");
    }
}
