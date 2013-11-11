package tv.mineinthebox.essentials.events.playerMoveEvent;

import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import tv.mineinthebox.essentials.resources.worldguard.worldguardApi;

public class zoneEvent implements Listener {
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
}
