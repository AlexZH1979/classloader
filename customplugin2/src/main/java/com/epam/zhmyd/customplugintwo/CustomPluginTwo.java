package com.epam.zhmyd.customplugintwo;


import com.epam.zhmyd.plugin.api.Plugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomPluginTwo implements Plugin {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomPluginTwo.class);

    public void init() {
        LOGGER.info("Init custom plugin two");
    }

    public void apply() {
       LOGGER.info("Run custom plugin two");
    }
}
