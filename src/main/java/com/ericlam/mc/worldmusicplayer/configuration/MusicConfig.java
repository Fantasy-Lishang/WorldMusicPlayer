package com.ericlam.mc.worldmusicplayer.configuration;

import com.ericlam.mc.eld.annotations.Resource;
import com.ericlam.mc.eld.components.Configuration;

import java.util.Map;

@Resource(locate = "config.yml")
public class MusicConfig extends Configuration {

    public Map<String, WorldMusic> worlds;
    public boolean enableException;
    public String exceptionPermission;

    public static class WorldMusic {

        public String music;
        public double volume;
        public double pitch;
        public long duration;

    }
}
