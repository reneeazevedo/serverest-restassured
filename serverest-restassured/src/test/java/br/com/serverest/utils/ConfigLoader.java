package br.com.serverest.utils;

import java.util.Properties;

public class ConfigLoader {
    private final Properties properties;
    private static ConfigLoader configLoader;

    private ConfigLoader(){
        properties = PropertyUtils.propertyLoader("src/test/resources/config.properties");
    }

    public static ConfigLoader getInstance(){
        if(configLoader == null){
            configLoader = new ConfigLoader();
        }
        return configLoader;
    }

    public String getUrl(){
        String prop = properties.getProperty("url");
        if(prop != null) return prop;
        else throw new RuntimeException("property url is not specified in the config.properties file");
    }


}
