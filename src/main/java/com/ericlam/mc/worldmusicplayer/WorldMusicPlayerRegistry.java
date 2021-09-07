package com.ericlam.mc.worldmusicplayer;

import com.ericlam.mc.eld.registrations.CommandRegistry;
import com.ericlam.mc.eld.registrations.ComponentsRegistry;
import com.ericlam.mc.eld.registrations.ListenerRegistry;
import com.ericlam.mc.worldmusicplayer.commmand.MusicPlayerCommand;
import com.ericlam.mc.worldmusicplayer.commmand.MusicPlayerPlayCommand;
import com.ericlam.mc.worldmusicplayer.commmand.MusicPlayerStopCommand;

import java.util.List;

public class WorldMusicPlayerRegistry implements ComponentsRegistry {
    @Override
    public void registerCommand(CommandRegistry registry) {
        registry.command(MusicPlayerCommand.class, c -> {

            c.command(MusicPlayerPlayCommand.class);

            c.command(MusicPlayerStopCommand.class);

        });
    }

    @Override
    public void registerListeners(ListenerRegistry registry) {
        registry.ELDListeners(List.of(
                WorldMusicListener.class
        ));
    }
}
