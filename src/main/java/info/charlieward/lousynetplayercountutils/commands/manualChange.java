package info.charlieward.lousynetplayercountutils.commands;

import info.charlieward.lousynetplayercountutils.LousyNetPlayerCountUtils;
import info.charlieward.lousynetplayercountutils.files.CustomConfig;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class manualChange implements CommandExecutor {
    LousyNetPlayerCountUtils plugin;
    public manualChange(LousyNetPlayerCountUtils plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.sendMessage(ChatColor.BLUE + "[LousyNet-PlayerCount-Utils]" + ChatColor.RED + " This command is a console only command");
            return true;
        } else if (sender instanceof ConsoleCommandSender) {
            if (args.length == 0) {
                System.out.println("[LousyNet-PlayerCount-Utils] Please provide a number to set the count to.");
                System.out.println("manualCountChange <Value>");
            } else if (args.length == 1) {
                System.out.println("[LousyNet-PlayerCount-Utils] Updating server: " + CustomConfig.get().getString("Server ID - Must be unique to all other server IDs on the network") + " to have " + args[0] + " players");
                plugin.jedis.set(CustomConfig.get().getString("Server ID - Must be unique to all other server IDs on the network"), args[0]);
            } else {
                System.out.println("[LousyNet-PlayerCount-Utils] Please only provide one argument");
                System.out.println("manualCountChange <Value>");
            }
        }
        return true;
    }
}
