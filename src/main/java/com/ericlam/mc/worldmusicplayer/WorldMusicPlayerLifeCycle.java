package com.ericlam.mc.worldmusicplayer;

import com.ericlam.mc.eld.ELDLifeCycle;
import org.bukkit.plugin.java.JavaPlugin;

public class WorldMusicPlayerLifeCycle implements ELDLifeCycle {

    @Override
    public void onEnable(JavaPlugin plugin) {
        plugin.getLogger().info("音樂播放器插件已啟用。");
    }

    @Override
    public void onDisable(JavaPlugin plugin) {

    }
}
