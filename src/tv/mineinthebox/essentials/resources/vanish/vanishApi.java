package tv.mineinthebox.essentials.resources.vanish;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import tv.mineinthebox.essentials.fileManager;
import tv.mineinthebox.essentials.xEssentialsMemory;

public class vanishApi {

	public static boolean isVanished(Player p) {
		if(xEssentialsMemory.returnPlayer(p).containsKey("Vanished")) {
			Boolean bol = (Boolean) xEssentialsMemory.returnPlayer(p).get("Vanished");
			if(bol) {
				return true;
			}
		}
		return false;
	}

	public static boolean isVanishedGetString(String p) {
		if(fileManager.file_exists(p.toLowerCase() + ".yml", fileManager.getDir() + File.separator + "vanish" + File.separator)) {
			if(fileManager.getBooleanValue(p.toLowerCase() + ".yml", "Vanished", fileManager.getDir() + File.separator + "vanish")) {
				return true;
			}
		}
		return false;
	}

	public static boolean vanish(Player p ) {
		if(!isVanished(p)) {
			for(Player player : Bukkit.getOnlinePlayers()) {
				 player.hidePlayer(p);
			}
			xEssentialsMemory.returnPlayer(p).put("Vanished", true);
			xEssentialsMemory.returnPlayer(p).put("noPickup", true);
			xEssentialsMemory.updatePlayerConfig(p);
		}
		return false;
	}

	public static boolean unvanish(Player p) {
		if(isVanished(p)) {
			for(Player player : Bukkit.getOnlinePlayers()) {
				player.showPlayer(p);
			}
			xEssentialsMemory.returnPlayer(p).put("Vanished", false);
			xEssentialsMemory.returnPlayer(p).put("noPickup", false);
			xEssentialsMemory.updatePlayerConfig(p);
		}
		return false;
	}

	public static boolean vanishOnReloadCheck() {
		for(Player p : Bukkit.getOnlinePlayers()) {
			if(isVanished(p)) {
				vanish(p);
			}
		}
		return false;
	}

	public static boolean vanishNoPickUp(Player p) {
		if(xEssentialsMemory.returnPlayer(p).containsKey("noPickup")) {
			Boolean bol = (Boolean) xEssentialsMemory.returnPlayer(p).get("noPickup");
			if(bol instanceof Boolean) {
				if(bol) {
					return true;
				}
			} else {
				return false;
			}
		}
		return false;
	}
	
	public static boolean setNoPickUp(Player p) {
		if(!vanishNoPickUp(p)) {
			xEssentialsMemory.returnPlayer(p).put("noPickup", true);
			xEssentialsMemory.updatePlayerConfig(p);
		}
		return false;
	}
	
	public static boolean unsetNoPickUp(Player p) {
		if(vanishNoPickUp(p)) {
			xEssentialsMemory.returnPlayer(p).put("noPickup", false);
			xEssentialsMemory.updatePlayerConfig(p);
		}
		return false;
	}

}
