package tv.mineinthebox.essentials.resources.bansystem;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import tv.mineinthebox.essentials.fileManager;

public class ban {
	public static void getAlternateAccounts(Player p) {
		String ip = p.getPlayer().getAddress().getHostName();
		try {
			File dir = new File(fileManager.getDir() + File.separator + "alts");
			if(dir.isDirectory()) {
				File[] list = dir.listFiles();
				StringBuilder build = new StringBuilder();
				for(File f : list) {
					if(!f.getName().toLowerCase().replace(".yml", "").equalsIgnoreCase(p.getName().toLowerCase())) {
						FileConfiguration con = YamlConfiguration.loadConfiguration(f);
						if(con.getString("ip").equalsIgnoreCase(ip)) {
							build.append(con.getString("player").toString() + ",");
						}

					}
				}
				if(build.length() != 0) {
					for(Player a : Bukkit.getOnlinePlayers()) {
						if(!a.getName().toLowerCase().equalsIgnoreCase(p.getName().toLowerCase())) {
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

	public static void getAlternateAccounts(CommandSender sender, Player victem) {
		String ip = victem.getPlayer().getAddress().getHostName();
		try {
			File dir = new File(fileManager.getDir() + File.separator + "alts");
			if(dir.isDirectory()) {
				File[] list = dir.listFiles();
				StringBuilder build = new StringBuilder();
				for(File f : list) {
					if(!f.getName().toLowerCase().replace(".yml", "").equalsIgnoreCase(victem.getName().toLowerCase())) {
						FileConfiguration con = YamlConfiguration.loadConfiguration(f);
						if(con.getString("ip").equalsIgnoreCase(ip)) {
							build.append(con.getString("player").toString() + ",");
						}

					}
				}
				if(build.length() != 0) {
					sender.sendMessage(ChatColor.GREEN + "alternate accounts found for player " + victem.getName());
					sender.sendMessage(setTypeAlt(build.toString()));
				} else {
					sender.sendMessage(ChatColor.RED + "no alternate accounts found for player " + victem.getName());
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void getAlternateAccounts(CommandSender sender, String victem) {
		if(fileManager.file_exists(victem.toLowerCase() + ".yml", fileManager.getDir() + File.separator + "alts")) {
			String ip = fileManager.getStringValue(victem.toLowerCase() + ".yml", "ip", fileManager.getDir() + File.separator + "alts");
					try {
						File dir = new File(fileManager.getDir() + File.separator + "alts");
						if(dir.isDirectory()) {
							File[] list = dir.listFiles();
							StringBuilder build = new StringBuilder();
							for(File f : list) {
								if(!f.getName().toLowerCase().replace(".yml", "").equalsIgnoreCase(victem.toLowerCase())) {
									FileConfiguration con = YamlConfiguration.loadConfiguration(f);
									if(con.getString("ip").equalsIgnoreCase(ip)) {
										build.append(con.getString("player").toString() + ",");
									}

								}
							}
							if(build.length() != 0) {
								sender.sendMessage(ChatColor.GREEN + "alternate accounts found for player " + victem);
								sender.sendMessage(setTypeAlt(build.toString()));
							} else {
								sender.sendMessage(ChatColor.RED + "no alternate accounts found for player " + victem);
							}
						}
					} catch(Exception e) {
						e.printStackTrace();
					}
		} else {
			sender.sendMessage(ChatColor.RED + victem + " has never played before");
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
			File f = new File(fileManager.getDir() + File.separator + "bans" + File.separator + p.getName().toLowerCase() + ".yml");
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
			File f = new File(fileManager.getDir() + File.separator + "bans" + File.separator + p.getName().toLowerCase() + ".yml");
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
			File f = new File(fileManager.getDir() + File.separator + "bans" + File.separator + p.toLowerCase() + ".yml");
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
			File f = new File(fileManager.getDir() + File.separator + "bans" + File.separator + p.toLowerCase() + ".yml");
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

	public static boolean isTempBanned(Player p) {
		try {
			File f = new File(fileManager.getDir() + File.separator + "bans" + File.separator + p.getName().toLowerCase() + ".yml");
			if(f.exists()) {
				FileConfiguration con = YamlConfiguration.loadConfiguration(f);
				if(con.getBoolean("Tempbanned")) {
					return true;
				} else {
					return false;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean isTempBanned(String p) {
		try {
			File f = new File(fileManager.getDir() + File.separator + "bans" + File.separator + p.toLowerCase() + ".yml");
			if(f.exists()) {
				FileConfiguration con = YamlConfiguration.loadConfiguration(f);
				if(con.getBoolean("Tempbanned")) {
					return true;
				} else {
					return false;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
