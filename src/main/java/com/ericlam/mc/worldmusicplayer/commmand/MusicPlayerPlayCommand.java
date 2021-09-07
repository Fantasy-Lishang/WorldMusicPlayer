package com.ericlam.mc.worldmusicplayer.commmand;

import com.ericlam.mc.eld.annotations.CommandArg;
import com.ericlam.mc.eld.annotations.Commander;
import com.ericlam.mc.eld.components.CommandNode;
import com.ericlam.mc.worldmusicplayer.MusicService;
import com.ericlam.mc.worldmusicplayer.configuration.MusicConfig;
import com.ericlam.mc.worldmusicplayer.configuration.MusicLang;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.inject.Inject;
import java.text.MessageFormat;

@Commander(
        name = "play",
        description = "播放音樂"
)
public class MusicPlayerPlayCommand implements CommandNode {

    @Inject
    private MusicService musicService;
    @Inject
    private MusicConfig config;
    @Inject
    private MusicLang lang;

    @CommandArg(order = 1)
    private Player player;

    @CommandArg(order = 2, labels = "該音樂的所屬世界")
    private String world;

    @Override
    public void execute(CommandSender sender) {
        var music = config.worlds.get(world);
        if (music == null) {
            sender.sendMessage(MessageFormat.format(lang.getLang().get("music-not-found"), world));
            return;
        }
        musicService.playerMusic(player, music);
        sender.sendMessage(MessageFormat.format(lang.getLang().get("playing"), music.music, player.getName()));
    }
}
