package io.github.astroneer.randomcoolstuff;

import io.github.astroneer.randomcoolstuff.Listeners.ShearItemFrameListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.stream.Stream;

public final class RandomCoolStuff extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getPluginManager().registerEvents(new ShearItemFrameListener(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
