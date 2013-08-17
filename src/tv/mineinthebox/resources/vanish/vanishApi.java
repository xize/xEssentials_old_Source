package tv.mineinthebox.resources.vanish;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import tv.mineinthebox.fileManager;

public class vanishApi {

	public static boolean isVanished(Player p) {
		if(fileManager.file_exists(p.getName() + ".yml", fileManager.getDir() + File.separator + "vanish")) {
			if(fileManager.getBooleanValue(p.getName() + ".yml", "Vanished", fileManager.getDir() + File.separator + "vanish")) {
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
		if(fileManager.file_exists(p.getName() + ".yml", fileManager.getDir() + File.separator + "vanish")) {
			fileManager.writeFile(p.getName() + ".yml", "Vanished", true, fileManager.getDir() + File.separator + "vanish");
			fileManager.writeFile(p.getName() + ".yml", "noPickup", true, fileManager.getDir() + File.separator + "vanish");
			for(Player target : Bukkit.getOnlinePlayers()) {
				target.hidePlayer(p);
			}
		} else {
			fileManager.writeFile(p.getName() + ".yml", "Vanished", true, fileManager.getDir() + File.separator + "vanish");
			fileManager.writeFile(p.getName() + ".yml", "noPickup", true, fileManager.getDir() + File.separator + "vanish");
			for(Player target : Bukkit.getOnlinePlayers()) {
				target.hidePlayer(p);
			}
		}
		return false;
	}

	public static boolean unvanish(Player p) {
		if(fileManager.file_exists(p.getName() + ".yml", fileManager.getDir() + File.separator + "vanish")) {
			fileManager.writeFile(p.getName() + ".yml", "Vanished", false, fileManager.getDir() + File.separator + "vanish");
			fileManager.writeFile(p.getName() + ".yml", "noPickup", false, fileManager.getDir() + File.separator + "vanish");
			for(Player target : Bukkit.getOnlinePlayers()) {
				target.showPlayer(p);
			}
		} else {
			fileManager.writeFile(p.getName() + ".yml", "Vanished", false, fileManager.getDir() + File.separator + "vanish");
			fileManager.writeFile(p.getName() + ".yml", "noPickup", false, fileManager.getDir() + File.separator + "vanish");
			for(Player target : Bukkit.getOnlinePlayers()) {
				target.showPlayer(p);
			}
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
		if(fileManager.file_exists(p.getName() + ".yml", fileManager.getDir() + File.separator + "vanish")) {
			if(fileManager.getBooleanValue(p.getName() + ".yml", "noPickup", fileManager.getDir() + File.separator + "vanish")) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
	
	public static boolean setNoPickUp(Player p) {
		if(fileManager.file_exists(p.getName() + ".yml", fileManager.getDir() + File.separator + "vanish")) {
			if(isVanished(p)) {
				fileManager.writeFile(p.getName() + ".yml", "noPickup", true, fileManager.getDir() + File.separator + "vanish");
			}
		}
		return false;
	}
	
	public static boolean unsetNoPickUp(Player p) {
		if(fileManager.file_exists(p.getName() + ".yml", fileManager.getDir() + File.separator + "vanish")) {
			if(isVanished(p)) {
				fileManager.writeFile(p.getName() + ".yml", "noPickup", false, fileManager.getDir() + File.separator + "vanish");
			}
		}
		return false;
	}

}
