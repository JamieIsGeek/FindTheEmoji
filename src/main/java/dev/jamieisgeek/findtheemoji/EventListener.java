package dev.jamieisgeek.findtheemoji;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.UUID;

public class EventListener implements Listener {

    private Game game = Game.getGame();
    private String prefix = game.getPrefix();
    private boolean inProgess = game.getProgress();
    private UUID gamePlayer = game.getGamePlayer();
    private int slot = game.getSlotToFind();

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if(e.getWhoClicked() instanceof Player) {
            if(e.getWhoClicked().getUniqueId().equals(gamePlayer)) {
                if(e.getView().getTitle().equals(ChatColor.GOLD + "Emoji Game")) {
                    e.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onPlayerMessage(AsyncPlayerChatEvent e) {
        if(e.getPlayer().getUniqueId().equals(gamePlayer)) {
            if(inProgess) {
                if(e.getMessage().equals(slot)) {
                    game.setDone(true);
                    game.setInProgess(false);
                } else {
                    game.setDone(false);
                    game.setInProgess(false);
                }
            }
        }
    }
}
