package info.charlieward.lousynetplayercountutils;

import info.charlieward.lousynetplayercountutils.commands.manualChange;
import info.charlieward.lousynetplayercountutils.commands.reloadCommand;
import info.charlieward.lousynetplayercountutils.commands.resetCommand;
import info.charlieward.lousynetplayercountutils.files.CustomConfig;
import info.charlieward.lousynetplayercountutils.listeners.playerCountChange;
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
        CustomConfig.get().addDefault("Server ID - Must be unique to all other server IDs on the network", "");
        CustomConfig.get().options().copyDefaults(true);
        CustomConfig.save();

        getCommand("playerCountReload").setExecutor(new reloadCommand());
        getCommand("playerCountReset").setExecutor(new resetCommand(this));
        getCommand("manualCountChanger").setExecutor(new manualChange(this));

        getServer().getPluginManager().registerEvents(new playerCountChange(this), this);

        int playerCount = Bukkit.getOnlinePlayers().size();
        jedis.set(CustomConfig.get().getString("Server ID - Must be unique to all other server IDs on the network"), Integer.toString(playerCount));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("LousyNet-PlayerCount-Utils v." + this.getDescription().getVersion() + " has been disabled.");
    }
}
