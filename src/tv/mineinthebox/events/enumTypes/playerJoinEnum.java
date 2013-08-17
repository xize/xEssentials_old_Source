package tv.mineinthebox.events.enumTypes;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import tv.mineinthebox.fileManager;

public enum playerJoinEnum {
	altmessage, welcomeMessage, isXeph0re, isWild, isSafe;

	public static void getAlternateAccounts(Player p) {
		String ip = p.getPlayer().getAddress().getHostName();
		try {
			File dir = new File(fileManager.getDir() + File.separator + "alts" + File.separator);
			if(dir.isDirectory()) {
				File[] list = dir.listFiles();
				StringBuilder build = new StringBuilder();
				for(File f : list) {
					if(!f.getName().replace(".yml", "").equalsIgnoreCase(p.getName())) {
						FileConfiguration con = YamlConfiguration.loadConfiguration(f);
						if(con.getString("ip").equalsIgnoreCase(ip)) {
							build.append(con.getString("player").toString() + ",");
						}

					}
				}
				if(build.length() != 0) {
					for(Player a : Bukkit.getOnlinePlayers()) {
						if(!a.getName().equalsIgnoreCase(p.getName())) {
							if(a.hasPermission("xEssentials.isStaff")) {
								a.sendMessage(ChatColor.RED + "Warning player " + ChatColor.GOLD + p.getName() + ChatColor.RED + " has may alternate accounts!");
								a.sendMessage(setTypeAlt(build.toString()));
							}
						}
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static String setTypeAlt(String nameList) {
		//Bukkit.broadcastMessage(nameList);
		String[] names = nameList.split(",");
		//System.out.println("this is names: " + names[0] + " and " + names[1]);
		StringBuilder alts = new StringBuilder();

		for(String name : names) {
			if(isBanned(name)) {
				alts.append(ChatColor.DARK_RED + "[Banned]" + ChatColor.WHITE + name + " ");
			} else if(whasBanned(name)) {
				alts.append(ChatColor.RED + "[Banned before]" + ChatColor.WHITE + name + " ");
			} else {
				alts.append(ChatColor.GRAY + "[not banned]" + ChatColor.WHITE + name + " ");
			}
		}
		return alts.toString().toString();
	}

	public static boolean isBanned(Player p) {
		try {
			File f = new File(fileManager.getDir() + File.separator + "bans" + File.separator + p.getName() + ".yml");
			if(f.exists()) {
				FileConfiguration con = YamlConfiguration.loadConfiguration(f);
				if(con.getBoolean("Banned")) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean whasBanned(Player p) {
		try {
			File f = new File(fileManager.getDir() + File.separator + "bans" + File.separator + p.getName() + ".yml");
			if(f.exists()) {
				FileConfiguration con = YamlConfiguration.loadConfiguration(f);
				if(!con.getBoolean("Banned") && con.isBoolean("Banned")) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean isBanned(String p) {
		try {
			File f = new File(fileManager.getDir() + File.separator + "bans" + File.separator + p + ".yml");
			if(f.exists()) {
				FileConfiguration con = YamlConfiguration.loadConfiguration(f);
				if(con.getBoolean("Banned") && con.isBoolean("Banned")) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean whasBanned(String p) {
		try {
			File f = new File(fileManager.getDir() + File.separator + "bans" + File.separator + p + ".yml");
			if(f.exists()) {
				FileConfiguration con = YamlConfiguration.loadConfiguration(f);
				if(!con.getBoolean("Banned") && con.isBoolean("Banned")) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
