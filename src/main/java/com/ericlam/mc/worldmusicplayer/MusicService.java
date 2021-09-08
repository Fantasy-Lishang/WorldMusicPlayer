package com.ericlam.mc.worldmusicplayer;

import com.ericlam.mc.worldmusicplayer.configuration.MusicConfig;
import com.ericlam.mc.worldmusicplayer.configuration.MusicLang;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import javax.inject.Inject;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class MusicService {

    private final Map<Player, MusicRunnable> musicRunner = new ConcurrentHashMap<>();

    @Inject
    private WorldMusicPlayer plugin;
    @Inject
    private MusicLang lang;

    public void playerMusic(Player player, MusicConfig.WorldMusic music) {
        long duration = music.duration;
        MusicRunnable runnable = new MusicRunnable(player, music);
        stopMusic(player);
        this.musicRunner.put(player, runnable);
        runnable.runTaskTimer(plugin, 0L, duration * 20L);
        plugin.getSLF4JLogger().info("playing music {} for {} in world {}", music.music, player.getName(), player.getWorld().getName());
    }


    public boolean stopMusic(Player player) {
        var lastMusic = this.musicRunner.remove(player);
        if (lastMusic != null) {
            lastMusic.cancel();
            player.sendMessage(lang.getLang().get("notify-player-stopped"));
            return true;
        }
        return false;
    }


    private static class MusicRunnable extends BukkitRunnable {

        private final MusicConfig.WorldMusic music;
        private final Player player;

        private MusicRunnable(Player player, MusicConfig.WorldMusic music) {
            this.player = player;
            this.music = music;
        }

        @Override
        public void run() {
            player.playSound(player.getLocation(), music.music, (float) music.volume, (float) music.pitch);
        }


        @Override
        public synchronized void cancel() throws IllegalStateException {
            player.stopSound(music.music);
            super.cancel();
        }

        public MusicConfig.WorldMusic getMusic() {
            return music;
        }
    }
}
