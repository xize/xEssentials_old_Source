package tv.mineinthebox.events.kickEvent;

import java.io.File;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;

import tv.mineinthebox.fileManager;

public class saveKickAlt implements Listener {

	@EventHandler
	public void saveAlt(PlayerKickEvent e) {
		String ip = e.getPlayer().getAddress().getHostName();
		fileManager.writeFile(e.getPlayer().getName() + ".yml", "ip", ip, fileManager.getDir() + File.separator + "alts");
		fileManager.writeFile(e.getPlayer().getName() + ".yml", "player", e.getPlayer().getName(), fileManager.getDir() + File.separator + "alts");
	}
}
