package io.github.astroneer.randomcoolstuff;

import io.github.astroneer.randomcoolstuff.Listeners.ShearItemFrameListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.stream.Stream;

public final class RandomCoolStuff extends JavaPlugin {

    private static RandomCoolStuff plugin;

    @Override
    public void onEnable() {
        // Plugin startup logic

        plugin = this;

        //Register events
        Bukkit.getPluginManager().registerEvents(new ShearItemFrameListener(), this);

        //Configuration process
        getConfig().options().copyDefaults();
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static RandomCoolStuff getPlugin() {
        return plugin;
    }
}
