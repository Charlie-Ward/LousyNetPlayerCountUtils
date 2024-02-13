package info.charlieward.lousynetplayercountutils;

import info.charlieward.lousynetplayercountutils.commands.reloadCommand;
import info.charlieward.lousynetplayercountutils.files.CustomConfig;
import org.bukkit.plugin.java.JavaPlugin;

public final class LousyNetPlayerCountUtils extends JavaPlugin {

    private static LousyNetPlayerCountUtils plugin;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("LousyNet-PlayerCount-Utils v." + this.getDescription().getVersion() + " has loaded.");

        getConfig().options(). copyDefaults();
        saveDefaultConfig();

        CustomConfig.setup();
        CustomConfig.get().addDefault("Server Name", "");
        CustomConfig.get().options().copyDefaults(true);
        CustomConfig.save();

        getCommand("playerCountReload").setExecutor(new reloadCommand());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
