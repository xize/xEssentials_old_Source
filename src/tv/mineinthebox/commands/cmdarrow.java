package tv.mineinthebox.commands;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class cmdarrow {
	
	public static boolean execute(CommandSender sender, Command cmd, String[] args) {
		if(cmd.getName().equalsIgnoreCase("arrow")) {
			if(sender instanceof Player) {
				Player p = (Player) sender;
				Block block = p.getTargetBlock(null, 30);
				p.getWorld().spawnEntity(block.getLocation().getBlock().getRelative(BlockFace.UP).getLocation().getBlock().getRelative(BlockFace.UP).getLocation().getBlock().getRelative(BlockFace.UP).getLocation(), EntityType.ARROW);
			}
		}
		return false;
	}

}
