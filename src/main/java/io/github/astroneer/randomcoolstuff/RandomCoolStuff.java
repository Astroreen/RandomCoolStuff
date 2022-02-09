package io.github.astroneer.randomcoolstuff;

import io.github.astroneer.randomcoolstuff.Listeners.PlayerBreakItemFrameListener;
import io.github.astroneer.randomcoolstuff.Listeners.PlayerInteractWithItemFrameListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

public final class RandomCoolStuff extends JavaPlugin {

    private static RandomCoolStuff plugin;
    Logger log = getLogger();

    @Override
    public void onEnable() {
        // Plugin startup logic

        //
        log.log(Level.INFO, "RandomCoolStuff was enabled!");

        plugin = this;

        //Registering all events
        Stream.of(
                        new PlayerInteractWithItemFrameListener(),
                        new PlayerBreakItemFrameListener())
                .forEach(l -> Bukkit.getPluginManager().registerEvents(l, this));

        //Configuration process
        getConfig().options().copyDefaults();
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        log.log(Level.INFO, "RandomCoolStuff was disabled!");
    }

    public static RandomCoolStuff getPlugin() {
        return plugin;
    }
}
