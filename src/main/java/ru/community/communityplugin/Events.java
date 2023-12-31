package ru.community.communityplugin;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;
import java.util.Arrays;

public class Events implements Listener {

    public String[][] data = {{null, null}};
    //public ArrayList<String[][]> data = new ArrayList<String[][]>();

    private boolean blocksBreakChecked(Player player) {
        for (int i = 0; i < data.length; i++) {

            if (data[i][0] == player.getName()) {
                if (data[i].length > 2) {
                    for (int j = 0; j < data[i].length; j++) {
                        int again = 0;
                        String material = data[i][j];

                        for (int k = 0; k < data[i].length; k++) if (data[i][k] == material) again++;
                        if (again >= 128) return true;
                        else continue;

                    }
                }
                else continue;
            }

            else continue;
        }
        return false;
    }

    private void createDataList(Player player) {
        //data = new String[1][1];
        int index = data.length;
        data[index][0] = player.getName();
        data[index][1] = Material.AIR.toString();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        try {
            if (data.length == 0) {
                createDataList(player);
                System.out.println("COM-LOG.data: " + Arrays.deepToString(data));
                player.sendMessage("Community приветствует тебя!");
            }
            if (data.length == 1) {
                if (data[0][0] == player.getName()) System.out.println("COM-LOG.data: " + Arrays.deepToString(data));
                else {
                    createDataList(player);
                }
            }
            else {
                createDataList(null);
            }
        }
        catch (NullPointerException e) {
            createDataList(null);
        }

        System.out.println(Arrays.deepToString(data));
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        int variable = 0;
        Player player = event.getPlayer();
        Block block = event.getBlock();
        for (int i = 0; i < data.length; i++) {
            if (player.getName() == data[i][0]) {
                int masIndex = data[i].length;
                data[i][masIndex] = block.getType().toString();
            }
            else {
                variable += 1;
                continue;
            }
        }
        if (variable >= data.length) {
            int index = data.length;
            data[index][0] = player.getName();
            data[index][1] = block.getType().toString();
        }
        final boolean out = blocksBreakChecked(player);
        if (out) AdminEvents.openMenu(player, block);
        player.sendMessage("Ты сломал блок " + block.getType().toString());
    }

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        player.sendMessage("Нельзя выкидывать предметы!");
    }

}