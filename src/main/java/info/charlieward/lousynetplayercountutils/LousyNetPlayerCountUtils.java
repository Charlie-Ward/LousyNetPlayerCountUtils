package info.charlieward.lousynetplayercountutils;

import info.charlieward.lousynetplayercountutils.commands.reloadCommand;
import info.charlieward.lousynetplayercountutils.commands.resetCommand;
import info.charlieward.lousynetplayercountutils.files.CustomConfig;
import info.charlieward.lousynetplayercountutils.listeners.playerCountChangeAdd;
import info.charlieward.lousynetplayercountutils.listeners.playerCountChangeDown;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import redis.clients.jedis.Jedis;

public final class LousyNetPlayerCountUtils extends JavaPlugin {

    private static LousyNetPlayerCountUtils plugin;
    public Jedis jedis = new Jedis();

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("LousyNet-PlayerCount-Utils v." + this.getDescription().getVersion() + " has loaded.");

        getConfig().options(). copyDefaults();
        saveDefaultConfig();

        CustomConfig.setup();
        CustomConfig.get().addDefault("Server ID", "");
        CustomConfig.get().options().copyDefaults(true);
        CustomConfig.save();

        getCommand("playerCountReload").setExecutor(new reloadCommand());
        getCommand("playerCountReset").setExecutor(new resetCommand(this));

        getServer().getPluginManager().registerEvents(new playerCountChangeAdd(this), this);
        getServer().getPluginManager().registerEvents(new playerCountChangeDown(this), this);

        int playerCount = Bukkit.getOnlinePlayers().size();
        jedis.set(CustomConfig.get().getString("Server ID"), Integer.toString(playerCount));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        jedis.set(CustomConfig.get().getString("Server ID"), "offline");
        getLogger().info("LousyNet-PlayerCount-Utils v." + this.getDescription().getVersion() + " has been disabled.");
    }
}
