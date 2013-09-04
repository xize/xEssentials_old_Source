package tv.mineinthebox.resources.vanish;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import tv.mineinthebox.fileManager;
import tv.mineinthebox.xEssentials;

public class vanishApi {

	public static boolean isVanished(Player p) {
		if(xEssentials.mem.returnPlayer(p).containsKey("Vanished")) {
			Boolean bol = (Boolean) xEssentials.mem.returnPlayer(p).get("Vanished");
			if(bol) {
				return true;
			}
		}
		return false;
	}

	public static boolean isVanishedGetString(String p) {
		if(fileManager.file_exists(p + ".yml", fileManager.getDir() + File.separator + "vanish" + File.separator)) {
			if(fileManager.getBooleanValue(p + ".yml", "Vanished", fileManager.getDir() + File.separator + "vanish")) {
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
			xEssentials.mem.returnPlayer(p).put("Vanished", true);
			xEssentials.mem.returnPlayer(p).put("noPickup", true);
			xEssentials.mem.updatePlayerConfig(p);
		}
		return false;
	}

	public static boolean unvanish(Player p) {
		if(isVanished(p)) {
			for(Player player : Bukkit.getOnlinePlayers()) {
				player.showPlayer(p);
			}
			xEssentials.mem.returnPlayer(p).put("Vanished", false);
			xEssentials.mem.returnPlayer(p).put("noPickup", false);
			xEssentials.mem.updatePlayerConfig(p);
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
		if(xEssentials.mem.returnPlayer(p).containsKey("noPickup")) {
			Boolean bol = (Boolean) xEssentials.mem.returnPlayer(p).get("noPickup");
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
			xEssentials.mem.returnPlayer(p).put("noPickup", true);
			xEssentials.mem.updatePlayerConfig(p);
		}
		return false;
	}
	
	public static boolean unsetNoPickUp(Player p) {
		if(vanishNoPickUp(p)) {
			xEssentials.mem.returnPlayer(p).put("noPickup", false);
			xEssentials.mem.updatePlayerConfig(p);
		}
		return false;
	}

}
