package tv.mineinthebox.essentials.events.playerEvents;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import tv.mineinthebox.essentials.fileManager;

public class taskEvent implements Listener {
	@EventHandler
	public void HandleOnCommandTask(PlayerJoinEvent e) {
		if(fileManager.file_exists(e.getPlayer().getName().toLowerCase() + ".yml", fileManager.getDir() + File.separator + "tasks")) {
			e.getPlayer().getServer().dispatchCommand(Bukkit.getConsoleSender(), fileManager.getStringValue(e.getPlayer().getName().toLowerCase() + ".yml", "command", fileManager.getDir() + File.separator + "tasks"));
		}
	}
}
