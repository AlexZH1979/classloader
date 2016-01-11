package com.epam.zhmyd.custompluginone;


import com.epam.zhmyd.plugin.api.Plugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomPluginOne implements Plugin {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomPluginOne.class);

    public void init() {
        LOGGER.info("Init custom plugin one");
    }

    public void apply() {
        LOGGER.info("Run custom plugin one");
    }
}
