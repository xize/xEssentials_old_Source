package tv.mineinthebox.events.chatEvent;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

import tv.mineinthebox.xEssentialsMemory;
import tv.mineinthebox.resources.timeunit.timeunits;

@SuppressWarnings("deprecation")
public class muteManager implements Listener {
	
	@EventHandler
	public void getMutes(PlayerChatEvent e) {
		if(xEssentialsMemory.returnPlayer(e.getPlayer()).containsKey("muted")) {
			long time = (Long) xEssentialsMemory.returnPlayer(e.getPlayer()).get("muted");
			if(!timeunits.isOverTime(time)) {
				e.getPlayer().sendMessage(ChatColor.GREEN + "you are muted for " + timeunits.getElapsedTime((Long) xEssentialsMemory.returnPlayer(e.getPlayer()).get("muted")));
				e.setCancelled(true);
			} else {
				xEssentialsMemory.returnPlayer(e.getPlayer()).remove("muted");
				xEssentialsMemory.updatePlayerConfig(e.getPlayer());
			}
		}
	}

}
