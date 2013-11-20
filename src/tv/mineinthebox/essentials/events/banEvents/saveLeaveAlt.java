package tv.mineinthebox.essentials.events.banEvents;

import java.io.File;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import tv.mineinthebox.essentials.fileManager;

public class saveLeaveAlt implements Listener {
	
	@EventHandler
	public void saveAlt(PlayerQuitEvent e) {
		String ip = e.getPlayer().getAddress().getHostName();
		fileManager.writeFile(e.getPlayer().getName().toLowerCase() + ".yml", "ip", ip, fileManager.getDir() + File.separator + "alts");
		fileManager.writeFile(e.getPlayer().getName().toLowerCase() + ".yml", "player", e.getPlayer().getName(), fileManager.getDir() + File.separator + "alts");
	}

}
