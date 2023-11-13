package me.simonfoy.tnttag;

import me.simonfoy.tnttag.instance.Game;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class TNT_Tag extends JavaPlugin {

    private Game game;

    @Override
    public void onEnable() {
        game = new Game(this);

        getCommand("kit").setExecutor(new KitCommand(this, game));
        getCommand("game").setExecutor(new GameCommand(this, game));
        Bukkit.getPluginManager().registerEvents(new ConnectListener(this), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public Game getGame() {
        return game;
    }
}
