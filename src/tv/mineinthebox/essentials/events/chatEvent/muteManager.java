package tv.mineinthebox.essentials.events.chatEvent;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

import tv.mineinthebox.essentials.xEssentialsMemory;
import tv.mineinthebox.essentials.resources.timeunit.timeunits;

@SuppressWarnings("deprecation")
public class muteManager implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void getMutes(PlayerChatEvent e) {
		if(isMuted(e.getPlayer())) {
			Long time = (Long) xEssentialsMemory.returnPlayer(e.getPlayer()).get("muted");
			if(timeunits.isOverTime(time)) {
				xEssentialsMemory.returnPlayer(e.getPlayer()).put("muted", 0);
				xEssentialsMemory.updatePlayerConfig(e.getPlayer());	
			} else {
				e.setCancelled(true);
			}
		}
	}
	
	public static boolean isMuted(Player p) {
		if(p instanceof Player) {
				if(xEssentialsMemory.returnPlayer(p).containsKey("muted")) {
					Long time = (Long) xEssentialsMemory.returnPlayer(p).get("muted");
					try {
						if(time == 0 || time == null) {
							return false;
						}
						if(!timeunits.isOverTime(time)) {
							return true;
						}
					} catch(NullPointerException e) {
						System.out.println("the time is null");
					}	
				} 
			}
		return false;
	}


}
