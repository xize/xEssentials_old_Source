package tv.mineinthebox.essentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tv.mineinthebox.essentials.permissions.consolePermission;
import tv.mineinthebox.essentials.permissions.playerPermission;

public class cmdhand {

	@SuppressWarnings("deprecation")
	public static boolean execute(CommandSender sender, Command cmd, String[] args) {
		if(cmd.getName().equalsIgnoreCase("hand")) {
			if(sender.hasPermission("xEssentials.command.hand")) {
				if(args.length == 0) {
					if(sender instanceof Player) {
						Player p = (Player) sender;
						String materialName = p.getItemInHand().getType().name();
						String DataValue = ""+p.getItemInHand().getTypeId();
						String subData = ""+p.getItemInHand().getDurability();
						sender.sendMessage(ChatColor.GREEN + "the item in your hand is: " + ChatColor.GRAY + materialName + ChatColor.GREEN +  ", datavalue: " + ChatColor.GRAY + DataValue + ChatColor.GREEN +  " and sub datavalue: " + ChatColor.GRAY + subData);
					} else {
						consolePermission.getConsoleMessage(sender);
					}
				} else if(args.length == 1) {
					Player p = Bukkit.getPlayer(args[0]);
					String materialName = p.getItemInHand().getType().name();
					String DataValue = ""+p.getItemInHand().getTypeId();
					String subData = ""+p.getItemInHand().getDurability();
					sender.sendMessage(ChatColor.GREEN + "the item in " + p.getName() + "'s hand is: " + ChatColor.GRAY + materialName + ChatColor.GREEN +  ", datavalue: " + ChatColor.GRAY + DataValue + ChatColor.GREEN +  " and sub datavalue: " + ChatColor.GRAY + subData);
				}
			} else {
				playerPermission.getPermissionError(sender, cmd, args);
			}
		}
		return false;
	}

}
