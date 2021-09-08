package com.ericlam.mc.worldmusicplayer;

import com.ericlam.mc.eld.components.ELDListener;
import com.ericlam.mc.eld.components.EventListeners;
import com.ericlam.mc.worldmusicplayer.configuration.MusicConfig;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import javax.inject.Inject;

public class WorldMusicListener implements ELDListener {


    @Inject
    private MusicConfig config;

    @Inject
    private MusicService musicService;

    @Override
    public void defineNodes(EventListeners listeners) {
        handle(listeners.listen(PlayerChangedWorldEvent.class));
        handle(listeners.listen(PlayerJoinEvent.class));
    }

    private <E extends PlayerEvent> void handle(EventListeners.EventSubscriber<E> subscriber) {
        subscriber
                .filter(e -> config.worlds.containsKey(e.getPlayer().getWorld().getName()))
                .filter(e -> !config.enableException || e.getPlayer().hasPermission(config.exceptionPermission))
                .handle(e -> {
                    Player player = e.getPlayer();
                    String world = player.getWorld().getName();
                    MusicConfig.WorldMusic music = config.worlds.get(world);
                    musicService.playerMusic(player, music);
                });
    }


}
