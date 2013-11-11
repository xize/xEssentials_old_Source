package tv.mineinthebox.essentials.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import tv.mineinthebox.essentials.events.pluginEnableEvent.TPS;
import tv.mineinthebox.essentials.permissions.playerPermission;

public class cmdgc {
	
	public static Long max_ram = Runtime.getRuntime().maxMemory();
	public static Long total_ram = Runtime.getRuntime().totalMemory();
	public static Long free_ram = Runtime.getRuntime().freeMemory();
	
	public static boolean execute(CommandSender sender, Command cmd, String[] args) {
		if(cmd.getName().equalsIgnoreCase("gc")) {
			if(sender.hasPermission("xEssentials.command.gc")) {
				sender.sendMessage(ChatColor.GOLD + ".oO___[Garbage collector]___Oo.");
				sender.sendMessage(ChatColor.DARK_GRAY + "Ram usage: " + (max_ram / 10485760 - free_ram / 10485760) + "kb");
				sender.sendMessage(ChatColor.DARK_GRAY + "ticks rate: " + TPS.getServerTicks());
			} else {
				playerPermission.getPermissionError(sender, cmd, args);
			}
		}
		return false;
	}

}
