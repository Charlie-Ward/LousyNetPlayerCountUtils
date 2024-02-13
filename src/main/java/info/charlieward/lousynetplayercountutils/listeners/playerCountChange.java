package info.charlieward.lousynetplayercountutils.listeners;

import info.charlieward.lousynetplayercountutils.LousyNetPlayerCountUtils;
import info.charlieward.lousynetplayercountutils.files.CustomConfig;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class playerCountChange implements Listener {
    LousyNetPlayerCountUtils plugin;
    public playerCountChange(LousyNetPlayerCountUtils plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void playerJoins(PlayerJoinEvent event) {
        int playerCount = Bukkit.getOnlinePlayers().size();
        plugin.jedis.set(CustomConfig.get().getString("Server ID - Must be unique to all other server IDs on the network"), Integer.toString(playerCount));
    }

    @EventHandler
    public void playerLeaves(PlayerQuitEvent event) { /* Check if this works - Google */
        int playerCount = Bukkit.getOnlinePlayers().size();
        plugin.jedis.set(CustomConfig.get().getString("Server ID - Must be unique to all other server IDs on the network"), Integer.toString(playerCount));
    }
}
