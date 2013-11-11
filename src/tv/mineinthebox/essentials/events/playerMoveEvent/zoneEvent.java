package tv.mineinthebox.essentials.events.playerMoveEvent;

import org.bukkit.Chunk;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import tv.mineinthebox.essentials.hook.hooks;
import tv.mineinthebox.essentials.hook.worldguard;

public class zoneEvent implements Listener {
	@EventHandler
	public void Zones(PlayerMoveEvent e) {
		if(hooks.isWorldGuardEnabled()) {
			Chunk from = e.getFrom().getChunk();
			Chunk to = e.getTo().getChunk();
			worldguard.sendRegionMessage(e.getPlayer(), from, to);
		}
	}
}
