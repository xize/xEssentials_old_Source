package tv.mineinthebox.essentials.events.chatEvent;

import java.util.HashMap;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import tv.mineinthebox.essentials.xEssentialsMemory;
import tv.mineinthebox.essentials.commands.cmdban;
import tv.mineinthebox.essentials.commands.cmdban.banType;

@SuppressWarnings("deprecation")
public class AntiFloodSpam implements Listener {
	
	public static HashMap<String, Long> chatTime = new HashMap<String, Long>();
	
	@EventHandler
	public void playerChatTime(PlayerChatEvent e) {
		if(chatTime.containsKey(e.getPlayer().getName())) {
			if(System.currentTimeMillis() < chatTime.get(e.getPlayer().getName())) {
				cmdban.setBanned(xEssentialsMemory.antiFloodSpamMessage, e.getPlayer(), banType.floodspam);
			} else {
				chatTime.remove(e.getPlayer().getName());
			}
		} else {
			chatTime.put(e.getPlayer().getName(), System.currentTimeMillis());
		}
	}
	
	@EventHandler
	public void pQuit(PlayerQuitEvent e) {
		if(chatTime.containsKey(e.getPlayer().getName())) {
			chatTime.remove(e.getPlayer().getName());
		} else {
			return;
		}
	}

}
