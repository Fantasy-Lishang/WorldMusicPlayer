package com.ericlam.mc.worldmusicplayer;

import com.ericlam.mc.eld.ELDBukkitPlugin;
import com.ericlam.mc.eld.ManagerProvider;
import com.ericlam.mc.eld.ServiceCollection;
import com.ericlam.mc.eld.annotations.ELDPlugin;
import com.ericlam.mc.worldmusicplayer.configuration.MusicConfig;
import com.ericlam.mc.worldmusicplayer.configuration.MusicLang;

@ELDPlugin(
        registry = WorldMusicPlayerRegistry.class,
        lifeCycle = WorldMusicPlayerLifeCycle.class
)
public final class WorldMusicPlayer extends ELDBukkitPlugin {

    @Override
    protected void manageProvider(ManagerProvider provider) {
    }

    @Override
    protected void bindServices(ServiceCollection collection) {
        collection.addConfiguration(MusicConfig.class);
        collection.addConfiguration(MusicLang.class);
        collection.addSingleton(MusicService.class);
    }
}
