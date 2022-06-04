package dev.jamieisgeek.findtheemoji;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class FindTheEmoji extends JavaPlugin {

    private final Logger logger = Bukkit.getLogger();

    @Override
    public void onEnable() {
        new Game();
        getCommand("egame").setExecutor(new CommandHandler());
        getServer().getPluginManager().registerEvents(new EventListener(), this);

        logger.info("");
        logger.info("FindTheEmoji has Enabled!");
        logger.info("Version: 1.0");
        logger.info("");
    }

    @Override
    public void onDisable() {
        logger.info("");
        logger.info("FindTheEmoji has Disabled!");
        logger.info("Version: 1.0");
        logger.info("");
    }
}
