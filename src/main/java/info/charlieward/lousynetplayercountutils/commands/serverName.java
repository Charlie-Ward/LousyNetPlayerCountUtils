package info.charlieward.lousynetplayercountutils.commands;

import info.charlieward.lousynetplayercountutils.LousyNetPlayerCountUtils;
import info.charlieward.lousynetplayercountutils.files.CustomConfig;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class serverName implements CommandExecutor {

    LousyNetPlayerCountUtils plugin;
    public serverName(LousyNetPlayerCountUtils plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        System.out.println("Server Name " + CustomConfig.get().getString("Server ID"));
        System.out.println("Player Count " + plugin.jedis.get(CustomConfig.get().getString("Server ID")));

        return true;
    }
}
