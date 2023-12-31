package ru.community.communityplugin;

import org.bukkit.plugin.java.JavaPlugin;

public final class CommunityPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new Events(), this);
    }

}
