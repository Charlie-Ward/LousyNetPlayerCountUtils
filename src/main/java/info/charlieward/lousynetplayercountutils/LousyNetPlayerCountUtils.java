package info.charlieward.lousynetplayercountutils;

import info.charlieward.lousynetplayercountutils.commands.reloadCommand;
import info.charlieward.lousynetplayercountutils.commands.resetCommand;
import info.charlieward.lousynetplayercountutils.files.CustomConfig;
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

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
