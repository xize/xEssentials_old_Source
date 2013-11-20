package tv.mineinthebox.essentials.commands;

import java.io.File;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import tv.mineinthebox.essentials.fileManager;
import tv.mineinthebox.essentials.permissions.consolePermission;
import tv.mineinthebox.essentials.permissions.playerPermission;

public class cmdinvsee {
	
	public static boolean execute(CommandSender sender, Command cmd, String[] args) {
		if(cmd.getName().equalsIgnoreCase("invsee")) {
			if(sender instanceof Player) {
				if(sender.hasPermission("xEssentials.command.invsee")) {
					Player p = (Player) sender;
					if(args.length == 0) {
						sender.sendMessage(ChatColor.GOLD + ".oO___[invsee]___Oo.");
						sender.sendMessage(ChatColor.RED + "Admin: " + ChatColor.GRAY + "/invsee <player> " + ChatColor.WHITE +  ": shows player inventory");
					} else if(args.length == 1) {
						Player victem = Bukkit.getPlayerExact(args[0]);
						if(victem instanceof Player) {
							p.sendMessage(ChatColor.GREEN + "viewing online inventory of player " + args[0]);
							p.openInventory(victem.getInventory());
						} else {
							if(fileManager.file_exists(args[0].toLowerCase() + ".yml", fileManager.getDir() + File.separator + "inventory")) {
								try {
								List<?> list = (List<?>) fileManager.getListValue(args[0].toLowerCase() + ".yml", "inv", fileManager.getDir() + File.separator + "inventory");
								ItemStack[] items = (ItemStack[]) list.toArray(new ItemStack[0]);
								Inventory inv = Bukkit.createInventory(p, InventoryType.PLAYER);
								inv.setContents(items);
								sender.sendMessage(ChatColor.GREEN + "showing offline inventory of player " + args[0]);
								p.openInventory(inv);
								} catch(NullPointerException e) {
									sender.sendMessage(ChatColor.RED + "this offline inventory file is out of date");
								}
							} else {
								sender.sendMessage(ChatColor.RED + "no offline inventory found for player " + args[0]);
							}
						}
					}
				} else {
					playerPermission.getPermissionError(sender, cmd, args);
				}
			} else {
				consolePermission.getConsoleMessage(sender);
			}
		}
		return false;
	}

}
