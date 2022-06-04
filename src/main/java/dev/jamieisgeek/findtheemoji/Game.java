package dev.jamieisgeek.findtheemoji;

import me.arcaniax.hdb.api.HeadDatabaseAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

public class Game {
    private static Game game;
    private Inventory gameInv;
    private ArrayList<ItemStack> Emojis;
    private HeadDatabaseAPI api = new HeadDatabaseAPI();
    private int RandomInventorySlot;
    private ItemStack randomEmoji;
    private int openTimer = 6;
    private int gameTimer = 16;
    private String prefix = ChatColor.WHITE + "[" + ChatColor.GOLD + "FindTheEmoji" + ChatColor.WHITE + "] ";
    private Plugin plugin = FindTheEmoji.getPlugin(FindTheEmoji.class);
    private boolean inProgess;
    private boolean done;
    private UUID gamePlayer;
    private int SlotToFind;

    public void mainGame(Player player) {
        gamePlayer = player.getUniqueId();
        makeInventory(player);
        player.openInventory(gameInv);

        new BukkitRunnable() {
            @Override
            public void run() {
                if(openTimer != 0) {
                    openTimer--;
                } else {
                    player.closeInventory();
                    findTheEmoji(player);
                }
            }
        }.runTaskTimer(plugin, 0, 20);
    }

    private void findTheEmoji(Player player) {
        player.sendMessage(prefix + "You now have " + ChatColor.GOLD + "15 seconds" + ChatColor.WHITE + " to say the number slot that the " + randomEmojiName() + " was in!");
        inProgess = true;
        new BukkitRunnable() {
            @Override
            public void run() {
                if(gameTimer != 0 && inProgess == true) {
                    gameTimer--;
                } else {
                    inProgess = false;
                    endGame(player);
                }
            }
        }.runTaskTimer(plugin, 0, 20);
    }

    private void endGame(Player player){
        if(done) {
            player.sendMessage(prefix + "You have states the correct slot. You win!");
        } else {
            player.sendMessage(prefix + "You did not specify the right slot. You lose!");
        }
    }

    private String randomEmojiName() {
        String randomEmojiName;
        int Random = new Random(Emojis.size()).nextInt();
        SlotToFind = Random;
        randomEmojiName = Emojis.get(Random).getItemMeta().getDisplayName();

        return randomEmojiName;
    }

    private void makeInventory(Player player) {
        gameInv = Bukkit.createInventory(player, InventoryType.DROPPER, ChatColor.GOLD + "Emoji Game");
        getEmojis();

        for(int i = 0; i != 9; i++) {
            int randomSlot = RandomInventorySlot();
            gameInv.setItem(randomSlot, RandomEmoji());
        }
    }

    private ItemStack RandomEmoji() {
        int random = new Random(Emojis.size()).nextInt();
        randomEmoji = Emojis.get(random);

        return randomEmoji;
    }
    private int RandomInventorySlot() {
        int random = new Random(Emojis.size()).nextInt();

        while(gameInv.getItem(random).equals(Material.AIR) || gameInv.getItem(random).equals(null)) {
            random = new Random(Emojis.size()).nextInt();
        }

        return RandomInventorySlot;
    }
    private ArrayList<ItemStack> getEmojis() {
        Emojis.add(api.getItemHead("25626")); // Sad Emoji
        Emojis.add(api.getItemHead("25622")); // Sunglasses Emoji
        Emojis.add(api.getItemHead("25625")); // Neutral Emoji
        Emojis.add(api.getItemHead("25620")); // Surprised Emoji
        Emojis.add(api.getItemHead("25607")); // Smiling Emoji
        Emojis.add(api.getItemHead("25587")); // Scared Emoji
        Emojis.add(api.getItemHead("25582")); // Crying Emoji
        Emojis.add(api.getItemHead("25574")); // Wink Emoji
        Emojis.add(api.getItemHead("42502")); // Laughing Emoji

        return Emojis;
    }

    public String getPrefix() {
        return prefix;
    }

    public boolean getProgress() {
        return inProgess;
    }

    public void setInProgess(boolean isInProgess) {
        inProgess = isInProgess;
    }

    public void setDone(boolean isDone) {
        done = isDone;
    }

    public UUID getGamePlayer() {
        return gamePlayer;
    }

    public int getSlotToFind() {
        return SlotToFind;
    }

    public void setGame() {
        game = this;
    }
    public static Game getGame() {
        return game;
    }
}
