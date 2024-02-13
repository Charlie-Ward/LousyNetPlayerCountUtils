package info.charlieward.lousynetplayercountutils.listeners;

import info.charlieward.lousynetplayercountutils.LousyNetPlayerCountUtils;
import info.charlieward.lousynetplayercountutils.files.CustomConfig;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class playerCountChangeDown implements Listener {

    LousyNetPlayerCountUtils plugin;
    public playerCountChangeDown(LousyNetPlayerCountUtils plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void playerLeaves(PlayerQuitEvent event) {
        int playerCount = Bukkit.getOnlinePlayers().size();
        playerCount = playerCount - 1;
        plugin.jedis.set(CustomConfig.get().getString("Server ID"), Integer.toString(playerCount));
    }
}
