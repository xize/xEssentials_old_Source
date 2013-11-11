package tv.mineinthebox.essentials.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import tv.mineinthebox.essentials.events.broadcast.broadcast;
import tv.mineinthebox.essentials.permissions.playerPermission;

public class cmdbroadcast {
	
	public static boolean execute(CommandSender sender, Command cmd, String[] args) {
		if(cmd.getName().equalsIgnoreCase("broadcast")) {
			if(sender.hasPermission("xEssentials.command.broadcast")) {
				if(args.length == 0) {
					sender.sendMessage(ChatColor.GOLD + ".oO___[broadcast help]___Oo.");
					sender.sendMessage(ChatColor.RED + "Admin: " + ChatColor.GRAY + "/broadcast list" + ChatColor.WHITE + " : shows all broadcast ids");
					sender.sendMessage(ChatColor.RED + "Admin: " + ChatColor.GRAY + "/broadcast stop" + ChatColor.WHITE + " : stops the broadcast");
					sender.sendMessage(ChatColor.RED + "Admin: " + ChatColor.GRAY + "/broadcast reload" + ChatColor.WHITE + " : reloads broadcast");
					sender.sendMessage(ChatColor.RED + "Admin: " + ChatColor.GRAY + "/broadcast delete <id>" + ChatColor.WHITE + " : delete a broadcast");
					sender.sendMessage(ChatColor.RED + "Admin: " + ChatColor.GRAY + "/broadcast add <message>" + ChatColor.WHITE + " add a broadcast message");
					sender.sendMessage(ChatColor.RED + "Admin: " + ChatColor.GRAY + "/broadcast setprefix <prefix>" + ChatColor.WHITE + " : set the broadcast prefix");
					sender.sendMessage(ChatColor.RED + "Admin: " + ChatColor.GRAY + "/broadcast setsuffix <suffix>" + ChatColor.WHITE + " : set the broadcast suffix");
				} else if(args.length == 1) {
					if(args[0].equalsIgnoreCase("list")) {
						sender.sendMessage(ChatColor.GOLD + ".oO___[broadcast message ids]___Oo.");
						sender.sendMessage(broadcast.showIDs());
					} else if(args[0].equalsIgnoreCase("stop")) {
						broadcast.broadcastStop();
						sender.sendMessage(ChatColor.GREEN + "the broadcast has been stopped!");
					} else if(args[0].equalsIgnoreCase("reload")) {
						broadcast.broadcastReload();
						sender.sendMessage(ChatColor.GREEN + "the broadcast has been reloaded successfully!");
					} else if(args[0].equalsIgnoreCase("help")) {
						sender.sendMessage(ChatColor.GOLD + ".oO___[broadcast help]___Oo.");
						sender.sendMessage(ChatColor.RED + "Admin: " + ChatColor.GRAY + "/broadcast list" + ChatColor.WHITE + " : shows all broadcast ids");
						sender.sendMessage(ChatColor.RED + "Admin: " + ChatColor.GRAY + "/broadcast stop" + ChatColor.WHITE + " : stops the broadcast");
						sender.sendMessage(ChatColor.RED + "Admin: " + ChatColor.GRAY + "/broadcast reload" + ChatColor.WHITE + " : reloads broadcast");
						sender.sendMessage(ChatColor.RED + "Admin: " + ChatColor.GRAY + "/broadcast delete <id>" + ChatColor.WHITE + " : delete a broadcast");
						sender.sendMessage(ChatColor.RED + "Admin: " + ChatColor.GRAY + "/broadcast add <message>" + ChatColor.WHITE + " add a broadcast message");
						sender.sendMessage(ChatColor.RED + "Admin: " + ChatColor.GRAY + "/broadcast setprefix <prefix>" + ChatColor.WHITE + " : set the broadcast prefix");
						sender.sendMessage(ChatColor.RED + "Admin: " + ChatColor.GRAY + "/broadcast setsuffix <suffix>" + ChatColor.WHITE + " : set the broadcast suffix");
					}
				} else if(args.length == 2){
					if(args[0].equalsIgnoreCase("delete")) {
						try {
							if(broadcast.deleteBroadcast(Integer.parseInt(args[1]))) {
								sender.sendMessage(ChatColor.GREEN + "you successfully deleted id " + args[1] + " from the broadcasts!");
							} else {
								sender.sendMessage(ChatColor.RED + "this id seems not to be exist inside the broadcasts!");
							}
						} catch(NumberFormatException e) {
							sender.sendMessage(ChatColor.RED + args[1] + " needs to be a number!");
						}
					} else if(args[0].equalsIgnoreCase("add")) {
						broadcast.addNewBroadcast(args[1]);
						sender.sendMessage(ChatColor.GREEN + "you've successfully added a new broadcast message!");
					} else if(args[0].equalsIgnoreCase("setprefix")) {
						broadcast.setPrefix(args[1]);
						sender.sendMessage(ChatColor.GREEN + "you've successfully changed the broadcast prefix to " + args[1]);
					} else if(args[0].equalsIgnoreCase("setsuffix")) {
						broadcast.setSuffix(args[1]);
						sender.sendMessage(ChatColor.GREEN + "you've successfully changed the broadcast suffix to " + args[1]);
					}
				} else if(args.length > 1) {
					if(args[0].equalsIgnoreCase("add")) {
						StringBuilder build = new StringBuilder();
						for(int i = 1; i < args.length; i++) {
							build.append(args[i] + " ").toString();
						}
						broadcast.addNewBroadcast(build.toString());
						sender.sendMessage(ChatColor.GREEN + "you've successfully added a new broadcast!");
					}
				}
			} else {
				playerPermission.getPermissionError(sender, cmd, args);
			}
		}
		return false;
	}

}
