package io.hedwig.modules.engine;

import net.javacrumbs.jsonunit.core.Configuration;

public class CheckConfiguration {

    private Configuration jsonConfig;

    public static CheckConfiguration jsonCheckConfiguration(Configuration config){
        CheckConfiguration conf = new CheckConfiguration();
        conf.jsonConfig=config;
        return conf;
    }

    public Configuration getJsonConfig() {
        return jsonConfig;
    }

    public void setJsonConfig(Configuration jsonConfig) {
        this.jsonConfig = jsonConfig;
    }
}
