package tv.mineinthebox.essentials.events.playerEvents;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;

import tv.mineinthebox.essentials.resources.ghostfactory.ghost;

public class ghostkickLeave implements Listener {
	@EventHandler
	public void RemoveFromTeam(PlayerKickEvent e) {
		ghost.removePlayerFromTeam(e.getPlayer());
	}
	
	@EventHandler
	public void RemoveGhost(PlayerKickEvent e) {
		if(ghost.isGhost(e.getPlayer().getName())) {
			ghost.removeGhost(e.getPlayer());
		}
	}
}
