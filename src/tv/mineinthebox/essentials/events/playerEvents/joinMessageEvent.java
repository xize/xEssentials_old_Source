package tv.mineinthebox.essentials.events.playerEvents;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import tv.mineinthebox.essentials.hook.hooks;
import tv.mineinthebox.essentials.hook.worldguard;
import tv.mineinthebox.essentials.resources.bansystem.ban;
import tv.mineinthebox.essentials.resources.vanish.vanishApi;

public class joinMessageEvent implements Listener {
	@EventHandler
	public void joinMessage(PlayerJoinEvent e) {
		if(ban.isBanned(e.getPlayer()) || ban.isTempBanned(e.getPlayer())) {
			e.setJoinMessage("");	
			return;
		}
		if(vanishApi.isVanished(e.getPlayer())) {
			e.setJoinMessage("");	
			return;
		}
		if(hooks.isWorldGuardEnabled()) {
			worldguard.sendJoinMessage(e.getPlayer());
		} else {
			e.setJoinMessage(ChatColor.GREEN + e.getPlayer().getName() + " has joined :)");
		}
	}

}
