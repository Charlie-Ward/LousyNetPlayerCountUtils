package info.charlieward.lousynetplayercountutils.commands;

import info.charlieward.lousynetplayercountutils.files.CustomConfig;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class reloadCommand implements CommandExecutor {
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission("LousyNetPlayerCountUtils.admin")) {
                CustomConfig.reload();
                p.sendMessage(ChatColor.BLUE + "[LousyNet-PlayerCount-Utils]" + ChatColor.GREEN + " Request for config to be reloaded has been sent");
            } else {
                p.sendMessage(ChatColor.BLUE + "[LousyNet-PlayerCount-Utils]" + ChatColor.RED + " You do not have the correct permissions to run this command");
            }
        } else if (sender instanceof ConsoleCommandSender) {
            CustomConfig.reload();
            System.out.println("[LousyNet-PlayerCount-Utils] Request for config to be reloaded has been sent");
        }

        return true;
    }
}
