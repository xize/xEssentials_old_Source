package tv.mineinthebox.essentials.events.leaveEvent;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import tv.mineinthebox.essentials.hook.hooks;
import tv.mineinthebox.essentials.hook.worldguard;
import tv.mineinthebox.essentials.resources.bansystem.ban;
import tv.mineinthebox.essentials.resources.vanish.vanishApi;

public class leaveMessageEvent implements Listener {
	@EventHandler
	public void LeaveMessage(PlayerQuitEvent e) {
		if(ban.isBanned(e.getPlayer())) {
			e.setQuitMessage("");	
			return;
		}
		if(vanishApi.isVanished(e.getPlayer())) {
			e.setQuitMessage("");	
			return;
		}
		if(hooks.isWorldGuardEnabled()) {
			worldguard.sendQuitMessage(e.getPlayer());
		} else {
			e.setQuitMessage(ChatColor.GREEN + e.getPlayer().getName() + " has left!");
		}
	}

}
