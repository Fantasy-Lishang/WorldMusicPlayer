package com.ericlam.mc.worldmusicplayer.commmand;

import com.ericlam.mc.eld.annotations.Commander;
import com.ericlam.mc.eld.components.CommandNode;
import com.ericlam.mc.eld.services.ScheduleService;
import com.ericlam.mc.worldmusicplayer.WorldMusicPlayer;
import com.ericlam.mc.worldmusicplayer.configuration.MusicConfig;
import com.ericlam.mc.worldmusicplayer.configuration.MusicLang;
import org.bukkit.command.CommandSender;

import javax.inject.Inject;

@Commander(
        name = "reload",
        description = "reload command"
)
public class MusicPlayerReloadCommand implements CommandNode {

    @Inject
    private MusicConfig config;

    @Inject
    private MusicLang musicLang;

    @Inject
    private ScheduleService scheduleService;

    @Inject
    private WorldMusicPlayer plugin;

    @Override
    public void execute(CommandSender sender) {
        scheduleService
                .callAsync(plugin, () -> config.getController().reload() && musicLang.getController().reload())
                .thenRunSync((r) -> sender.sendMessage(musicLang.getLang().get(r ? "reload-completed" : "reload-failed"))).join();
    }
}
