package tv.mineinthebox.events;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import tv.mineinthebox.events.enumTypes.playerJoinEnum;

public class playerJoin implements Listener {
	playerJoinEnum type;
	public playerJoin(playerJoinEnum type) {
		this.type = type;
	}
	
	@EventHandler
	public void join(PlayerJoinEvent e) {
		if(type == playerJoinEnum.altmessage) {
			playerJoinEnum.getAlternateAccounts(e.getPlayer());
		} else if(type == playerJoinEnum.isSafe) {
			
		} else if(type == playerJoinEnum.isWild) {
			
		} else if(type == playerJoinEnum.isXeph0re) {
			if(e.getPlayer().getName().equalsIgnoreCase("Xeph0re")) {
				e.setJoinMessage(ChatColor.translateAlternateColorCodes('&', "&c()==[:::::::::::::>&e Developer of xEssentials &c<:::::::::::::]==()\n&2»&7 Xeph0re joined!"));
				e.getPlayer().getWorld().playSound(e.getPlayer().getLocation(), Sound.WITHER_IDLE, 1, 100);
				e.getPlayer().getWorld().playEffect(e.getPlayer().getLocation(), Effect.MOBSPAWNER_FLAMES, 100);
			}
		} else if(type == playerJoinEnum.welcomeMessage) {
			
		}
	}

}
