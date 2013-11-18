package tv.mineinthebox.essentials.events.playerEvents;

import java.io.File;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import tv.mineinthebox.essentials.fileManager;

public class saveInventory implements Listener {
	
	@EventHandler
	public void saveInv(PlayerQuitEvent e) {
		if(fileManager.getBooleanValue("player.yml", "save-playerInventory", fileManager.getDir())) {
			fileManager.writeFile(e.getPlayer().getName() + ".yml", "inv", e.getPlayer().getInventory().getContents(), fileManager.getDir() + File.separator + "inventory");
		}
	}

}
