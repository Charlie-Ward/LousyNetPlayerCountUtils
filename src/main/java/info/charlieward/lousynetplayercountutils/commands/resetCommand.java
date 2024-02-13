package info.charlieward.lousynetplayercountutils.commands;

import info.charlieward.lousynetplayercountutils.LousyNetPlayerCountUtils;
import info.charlieward.lousynetplayercountutils.files.CustomConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class resetCommand implements CommandExecutor {

    LousyNetPlayerCountUtils plugin;
    public resetCommand(LousyNetPlayerCountUtils plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("LousyNetPlayerCountUtils.admin")){
                int playerCount = Bukkit.getOnlinePlayers().size();
                plugin.jedis.set(CustomConfig.get().getString("Server ID - Must be unique to all other server IDs on the network"), Integer.toString(playerCount));
                player.sendMessage(ChatColor.BLUE + "[LousyNet-PlayerCount-Utils]" + ChatColor.GREEN + " Player count reset sent for this server");
            } else {
                player.sendMessage(ChatColor.BLUE + "[LousyNet-PlayerCount-Utils]" + ChatColor.RED + " You do not have the correct permissions to run this command");
            }
        } else if (sender instanceof ConsoleCommandSender) {
            int playerCount = Bukkit.getOnlinePlayers().size();
            plugin.jedis.set(CustomConfig.get().getString("Server ID"), Integer.toString(playerCount));
            System.out.println("[LousyNet-PlayerCount-Utils] Player count reset sent for this server");
        }
        return true;
    }

}
