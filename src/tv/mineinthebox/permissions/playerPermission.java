package tv.mineinthebox.permissions;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class playerPermission {
	
	public static void getPermissionError(CommandSender sender, Command cmd, String[] args) {
		if(cmd.getName().equalsIgnoreCase("sethome")) {
			sender.sendMessage(ChatColor.RED + "you are not allowed to use this command! /" + cmd.getName() + "\n" + ChatColor.GRAY + "permission: xEssentials.command.sethome");
		} else if(cmd.getName().equalsIgnoreCase("home")) {
			sender.sendMessage(ChatColor.RED + "you are not allowed to use this command! /" + cmd.getName() + "\n" + ChatColor.GRAY + "permission: xEssentials.command.home");
		} else if(cmd.getName().equalsIgnoreCase("setspawn")) {
			sender.sendMessage(ChatColor.RED + "you are not allowed to use this command! /" + cmd.getName() + "\n" + ChatColor.GRAY + "permission: xEssentials.command.setspawn");
		} else if(cmd.getName().equalsIgnoreCase("spawn")) {
			sender.sendMessage(ChatColor.RED + "you are not allowed to use this command! /" + cmd.getName() + "\n" + ChatColor.GRAY + "permission: xEssentials.command.spawn");
		} else if(cmd.getName().equalsIgnoreCase("vanish")) {
			if(args.length == 0) {
				sender.sendMessage(ChatColor.RED + "you are not allowed to use this command! /" + cmd.getName() + "\n" + ChatColor.GRAY + "permission: xEssentials.command.vanish");
			} else if(args.length == 1) {
				if(args[0].equalsIgnoreCase("nopickup")) {
					sender.sendMessage(ChatColor.RED + "you are not allowed to use this command! /" + cmd.getName() + " " + args[0] + "\n" + ChatColor.GRAY + "permission: xEssentials.command.vanish.nopickup");
				}
			}
		} else if(cmd.getName().equalsIgnoreCase("firefly")) {
			sender.sendMessage(ChatColor.RED + "you are not allowed to use this command! /" + cmd.getName() + "\n" + ChatColor.GRAY + "permission: xEssentials.command.firefly");
		} else if(cmd.getName().equalsIgnoreCase("spawner")) {
			sender.sendMessage(ChatColor.RED + "you are not allowed to use this command! /" + cmd.getName() + "\n" + ChatColor.GRAY + "permission: xEssentials.command.spawner");
		} else if(cmd.getName().equalsIgnoreCase("gamemode")) {
			sender.sendMessage(ChatColor.RED + "you are not allowed to use this command! /" + cmd.getName() + "\n" + ChatColor.GRAY + "permission: xEssentials.command.gamemode");
		} else if(cmd.getName().equalsIgnoreCase("modreq")) {
			sender.sendMessage(ChatColor.RED + "you are not allowed to use this command! /" + cmd.getName() + "\n" + ChatColor.GRAY + "permission: xEssentials.command.modreq");
		} else if(cmd.getName().equalsIgnoreCase("spawnmob")) {
			sender.sendMessage(ChatColor.RED + "you are not allowed to use this command! /" + cmd.getName() + "\n" + ChatColor.GRAY + "permission: xEssentials.command.spawnmob");
		} else if(cmd.getName().equalsIgnoreCase("invsee")) {
			sender.sendMessage(ChatColor.RED + "you are not allowed to use this command! /" + cmd.getName() + "\n" + ChatColor.GRAY + "permission: xEssentials.command.invsee");
		} else if(cmd.getName().equalsIgnoreCase("warp")) {
			if(args.length == 0) {
				sender.sendMessage(ChatColor.RED + "you are not allowed to use this command! /" + cmd.getName() + "\n" + ChatColor.GRAY + "permission: xEssentials.command.warp");	
			} else if(args.length == 1) {
				sender.sendMessage(ChatColor.RED + "you are not allowed to use this command! /" + cmd.getName() + "\n" + ChatColor.GRAY + "permission: xEssentials.command.warp");
			} else if(args.length == 2) {
				sender.sendMessage(ChatColor.RED + "you are not allowed to use this command! /" + cmd.getName() + "\n" + ChatColor.GRAY + "permission: xEssentials.command.warp.admin");
			}
		} else if(cmd.getName().equalsIgnoreCase("setwarp")) {
			sender.sendMessage(ChatColor.RED + "you are not allowed to use this command! /" + cmd.getName() + "\n" + ChatColor.GRAY + "permission: xEssentials.command.setwarp");
		} else if(cmd.getName().equalsIgnoreCase("torch")) {
			sender.sendMessage(ChatColor.RED + "you are not allowed to use this command! /" + cmd.getName() + "\n" + ChatColor.GRAY + "permission: xEssentials.command.torch");
		} else if(cmd.getName().equalsIgnoreCase("warps")) {
			sender.sendMessage(ChatColor.RED + "you are not allowed to use this command! /" + cmd.getName() + "\n" + ChatColor.GRAY + "permission: xEssentials.command.warps");
		} else if(cmd.getName().equalsIgnoreCase("delwarp")) {
			sender.sendMessage(ChatColor.RED + "you are not allowed to use this command! /" + cmd.getName() + "\n" + ChatColor.GRAY + "permission: xEssentials.command.delwarp");
		} else if(cmd.getName().equalsIgnoreCase("ban")) {
			sender.sendMessage(ChatColor.RED + "you are not allowed to use this command! /" + cmd.getName() + "\n" + ChatColor.GRAY + "permission: xEssentials.command.ban");
		} else if(cmd.getName().equalsIgnoreCase("unban")) {
			sender.sendMessage(ChatColor.RED + "you are not allowed to use this command! /" + cmd.getName() + "\n" + ChatColor.GRAY + "permission: xEssentials.command.unban");
		} else if(cmd.getName().equalsIgnoreCase("claim")) {
			sender.sendMessage(ChatColor.RED + "you are not allowed to use this command! /" + cmd.getName() + "\n" + ChatColor.GRAY + "permission: xEssentials.command.claim");
		}
	}

}
