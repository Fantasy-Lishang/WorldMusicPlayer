package com.ericlam.mc.worldmusicplayer.commmand;

import com.ericlam.mc.eld.annotations.Commander;
import com.ericlam.mc.eld.components.CommandNode;
import org.bukkit.command.CommandSender;

@Commander(
        name = "musicplayer",
        description = "音樂播放器指令",
        alias = {"music", "mp"}
)
public class MusicPlayerCommand implements CommandNode {
    @Override
    public void execute(CommandSender sender) {
    }
}
