package config;

import java.io.IOException;
import java.util.Properties;

public class Config {

    private Properties configFile;

    public Config(){
        configFile = new Properties();
        try{
            configFile.load(this.getClass().getClassLoader().getResourceAsStream("config/config.cfg"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public String getProperty(String key){
        return this.configFile.getProperty(key);
    }

}
