package com.ericlam.mc.worldmusicplayer.commmand;

import com.ericlam.mc.eld.annotations.CommandArg;
import com.ericlam.mc.eld.annotations.Commander;
import com.ericlam.mc.eld.components.CommandNode;
import com.ericlam.mc.worldmusicplayer.MusicService;
import com.ericlam.mc.worldmusicplayer.configuration.MusicLang;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.inject.Inject;
import java.text.MessageFormat;

@Commander(
        name = "stop",
        description = "停止音樂播放"
)
public class MusicPlayerStopCommand implements CommandNode {

    @Inject
    private MusicService musicService;
    @Inject
    private MusicLang lang;

    @CommandArg(order = 1)
    private Player player;


    @Override
    public void execute(CommandSender sender) {
        var result = musicService.stopMusic(player);
        sender.sendMessage(MessageFormat.format(lang.getLang().get(result ? "music-stopped" : "no-music-playing"), player.getName()));

    }
}
