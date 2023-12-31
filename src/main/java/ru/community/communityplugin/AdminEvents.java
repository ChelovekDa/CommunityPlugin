package ru.community.communityplugin;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class AdminEvents implements Listener {

    public static void openMenu(Player player, Block block) {
        if (player.isOp()) {
            if (player.getGameMode() == GameMode.SURVIVAL) {
                Inventory inv = Bukkit.createInventory(null, 54, "Блоки");
                ItemStack item = new ItemStack(Material.getMaterial(block.toString()), 64);
                for (int i = 0; i <= 54; i++) inv.setItem(i, item);
                player.openInventory(inv);

            }
            if (player.getGameMode() == GameMode.CREATIVE) player.sendMessage("Возможно стоит использовать wordedit?");
            if (player.getGameMode() == GameMode.SPECTATOR) player.kick();
            if (player.getGameMode() == GameMode.ADVENTURE) player.kick();

        }
        if (!player.isOp()) {
            if (player.getGameMode() == GameMode.CREATIVE) player.kick();
            if (player.getGameMode() == GameMode.SURVIVAL) player.kick();
        }
    }
}
