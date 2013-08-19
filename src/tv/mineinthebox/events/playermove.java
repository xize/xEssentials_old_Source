package tv.mineinthebox.events;

import java.io.File;
import java.util.HashSet;

import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import tv.mineinthebox.fileManager;
import tv.mineinthebox.resources.vanish.vanishApi;
import tv.mineinthebox.resources.worldguard.worldguardApi;

public class playermove implements Listener {

	public static HashSet<String> firefly = new HashSet<String>();

	@EventHandler
	public void fireFly(PlayerMoveEvent e) {
		if(firefly.contains(e.getPlayer().getName())) {
			if(vanishApi.isVanished(e.getPlayer())) {
				//only draw client side particles while being vanished
				e.getPlayer().playEffect(e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).getLocation(), Effect.MOBSPAWNER_FLAMES, 1);
				e.getPlayer().playEffect(e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).getLocation(), Effect.ENDER_SIGNAL, 1);
			} else {
				//draw particles towards everyone
				e.getPlayer().getWorld().playEffect(e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).getLocation(), Effect.MOBSPAWNER_FLAMES, 1);
				e.getPlayer().getWorld().playEffect(e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).getLocation(), Effect.ENDER_SIGNAL, 1);	
			}
		}
	}
	
	@EventHandler
	public void Zones(PlayerMoveEvent e) {
		Chunk from = e.getFrom().getChunk();
		Chunk to = e.getTo().getChunk();
		if(from.getX() != to.getX() || from.getZ() != to.getZ()) {
			if(!worldguardApi.isInRegion(from.getBlock(1, 1, 1).getLocation()) && worldguardApi.isInRegion(to.getBlock(1, 1, 1).getLocation())) {
				e.getPlayer().sendMessage(ChatColor.GOLD + ".oO___[Entering safe zone]___Oo.");
			} else if(worldguardApi.isInRegion(from.getBlock(1, 1, 1).getLocation()) && !worldguardApi.isInRegion(to.getBlock(1, 1, 1).getLocation())) {
				e.getPlayer().sendMessage(ChatColor.GOLD + ".oO___[Leaving safe zone]___Oo.");
			}
		}
	}
	
	@EventHandler
	public void torch(PlayerMoveEvent e) {
		if(fileManager.isSet(e.getPlayer().getName() + ".yml", "torch", fileManager.getDir() + File.separator + "players")) {
			if(fileManager.getBooleanValue(e.getPlayer().getName() + ".yml", "torch", fileManager.getDir() + File.separator + "players")) {
				if(e.getPlayer().getItemInHand().getType() == Material.TORCH) {
					Block block = e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN);
					if(block.getType() == Material.AIR || block.getType() == Material.WATER || block.getType() == Material.STATIONARY_WATER || block.getType() == Material.LAVA || block.getType() == Material.STATIONARY_LAVA) {
						return;
					} else {
						e.getPlayer().sendBlockChange(block.getLocation(), Material.GLOWSTONE, block.getData());	
					}
				}
			}
		}
	}
}
