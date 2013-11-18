package tv.mineinthebox.essentials.events.playerEvents;

import org.bukkit.ChatColor;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class disablePvp implements Listener {
	
	@EventHandler
	public void pvpDisable(EntityDamageByEntityEvent e) {
		if(e.getEntity() instanceof Player) {
			if(e.getDamager() instanceof Player) {
				Player p = (Player) e.getDamager();
				p.sendMessage(ChatColor.RED + "pvp has been disabled!");
				e.setCancelled(true);
			} else if(e.getDamager() instanceof Arrow) {
				Arrow arrow = (Arrow) e.getDamager();
				if(arrow.getShooter() instanceof Player) {
					Player p = (Player) arrow.getShooter();
					p.sendMessage(ChatColor.RED + "pvp has been disabled!");
					arrow.remove();
					e.setCancelled(true);
				}
			}
		}
	}

}
