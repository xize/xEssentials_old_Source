package tv.mineinthebox.essentials.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tv.mineinthebox.essentials.permissions.playerPermission;
import tv.mineinthebox.essentials.resources.ghostfactory.ghost;

public class cmdghost {
	
	public static boolean execute(CommandSender sender, Command cmd, String[] args) {
		if(cmd.getName().equalsIgnoreCase("ghost")) {
			if(sender.hasPermission("xEssentials.command.ghost")) {
				Player p = (Player) sender;
				if(ghost.isGhost(sender.getName())) {
					p.sendMessage(ChatColor.GREEN + "you are no longer a ghost!");
					ghost.removeGhost(p);
				} else {
					p.sendMessage(ChatColor.GREEN + "you are a ghost!");
					ghost.setGhost(p);
				}
			} else {
				playerPermission.getPermissionError(sender, cmd, args);
			}
		}
		return false;
	}

}
