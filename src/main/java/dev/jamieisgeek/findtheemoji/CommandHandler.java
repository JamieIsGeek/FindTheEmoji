package dev.jamieisgeek.findtheemoji;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHandler implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Game game = Game.getGame();

        if(sender instanceof Player) {
            Player player = (Player) sender;
            game.mainGame(player);
        }

        return true;
    }
}
