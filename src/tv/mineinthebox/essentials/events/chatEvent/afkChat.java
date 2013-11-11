package tv.mineinthebox.essentials.events.chatEvent;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

import tv.mineinthebox.essentials.events.playerMoveEvent.afkCheck;

@SuppressWarnings("deprecation")
public class afkChat implements Listener {

	@EventHandler
	public void afkCheckChat(PlayerChatEvent e) {
		for(Player p : Bukkit.getOnlinePlayers()) {
			if(e.getMessage().contains(p.getName())) {
				if(afkCheck.afkList.contains(p.getName())) {
					if(afkCheck.afkMessage.containsKey(p.getName())) {
						e.getPlayer().sendMessage(ChatColor.GREEN + p.getName() + " has been afk [ " + afkCheck.afkMessage.get(p.getName()) + " ]");
						//e.setCancelled(true);	
					} else {
						e.getPlayer().sendMessage(ChatColor.GREEN + p.getName() + " has been afk");
						//e.setCancelled(true);
					}
				}
			}
		}
	}
	
}
