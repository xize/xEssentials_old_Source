package tv.mineinthebox.essentials.commands;

import java.io.File;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import tv.mineinthebox.essentials.fileManager;
import tv.mineinthebox.essentials.hook.hooks;
import tv.mineinthebox.essentials.hook.worldguard;
import tv.mineinthebox.essentials.permissions.playerPermission;

public class cmdgamemode {

	public static boolean execute(CommandSender sender, Command cmd, String[] args) {
		if(cmd.getName().equalsIgnoreCase("gamemode")) {
			if(sender.hasPermission("xEssentials.command.gamemode")) {
				if(args.length == 0) {
					Player p = (Player) sender;
					sender.sendMessage(ChatColor.GREEN + "your gamemode is " + p.getGameMode().name());
				} else if(args.length == 1) {
					Player p = (Player) sender;
					if(args[0].equalsIgnoreCase("c") || args[0].equalsIgnoreCase("1")) {
						if(p.getGameMode() != GameMode.CREATIVE) {
							//Bukkit.broadcast(ChatColor.GRAY + p.getName() + " has set his gamemode to creative!", "xEssentials.gamemode");
							for(Player player : Bukkit.getOnlinePlayers()) {
								if(player.hasPermission("xEssentials.command.gamemode")) {
									player.sendMessage(ChatColor.GRAY + p.getName() + " has set his gamemode to creative!");
								}
							}
							if(useInventorySystem()) {
								saveSurvivalInventory(p);
								loadCreativeInventory(p);
								p.setGameMode(GameMode.CREATIVE);
								if(hooks.isWorldGuardEnabled()) {
									worldguard.turnOffWand(p);	
								}
							} else {
								p.setGameMode(GameMode.CREATIVE);
								if(hooks.isWorldGuardEnabled()) {
									worldguard.turnOffWand(p);	
								}
							}
						} else {
							p.sendMessage(ChatColor.RED + "error you are allready creative!");
						}
					} else if(args[0].equalsIgnoreCase("s") || args[0].equalsIgnoreCase("0")) {
						if(p.getGameMode() != GameMode.SURVIVAL) {
							//Bukkit.broadcast(ChatColor.GRAY + p.getName() + " has set his gamemode to survival!", "xEssentials.gamemode");
							for(Player player : Bukkit.getOnlinePlayers()) {
								if(player.hasPermission("xEssentials.command.gamemode")) {
									player.sendMessage(ChatColor.GRAY + p.getName() + " has set his gamemode to survival!");
								}
							}
							if(useInventorySystem()) {
								saveCreativeInventory(p);
								loadSurvivalInventory(p);
								p.setGameMode(GameMode.SURVIVAL);
								if(hooks.isWorldGuardEnabled()) {
									worldguard.turnOffWand(p);	
								}
							} else {
								p.setGameMode(GameMode.SURVIVAL);
								if(hooks.isWorldGuardEnabled()) {
									worldguard.turnOffWand(p);	
								}
							}
						} else {
							p.sendMessage(ChatColor.RED + "error you are allready survival!");
						}
					}
				} else if(args.length == 2) {
					if(args[0].equalsIgnoreCase("s") || args[0].equalsIgnoreCase("0")) {
						Player victem = Bukkit.getPlayer(args[1]);
						if(victem instanceof Player) {
							if(victem.getGameMode() != GameMode.SURVIVAL) {
								//Bukkit.broadcast(ChatColor.GRAY + p.getName() + " has set the gamemode for " + victem.getName() + " to survival!", "xEssentials.gamemode");
								for(Player player : Bukkit.getOnlinePlayers()) {
									if(player.hasPermission("xEssentials.command.gamemode")) {
										player.sendMessage(ChatColor.GRAY + sender.getName() + " has set the gamemode for " + victem.getName() + " to survival!");
									}
								}
								victem.sendMessage(ChatColor.DARK_GRAY + "gamemode has been changed to survival by " + sender.getName());
								if(useInventorySystem()) {
									saveCreativeInventory(victem);
									loadSurvivalInventory(victem);
									victem.setGameMode(GameMode.SURVIVAL);
									if(hooks.isWorldGuardEnabled()) {
										worldguard.turnOffWand(victem);	
									}
								} else {
									victem.setGameMode(GameMode.SURVIVAL);
									if(hooks.isWorldGuardEnabled()) {
										worldguard.turnOffWand(victem);	
									}
								}
							} else {
								sender.sendMessage(ChatColor.RED + "this player is allready in survival mode!");
							}
						} else {
							sender.sendMessage(ChatColor.RED + "this player is not online!");
						}
					} else if(args[0].equalsIgnoreCase("c") || args[0].equalsIgnoreCase("1")) {
						Player victem = Bukkit.getPlayer(args[1]);
						if(victem instanceof Player) {
							if(victem.getGameMode() != GameMode.CREATIVE) {
								//Bukkit.broadcast(ChatColor.GRAY + p.getName() + " has set the gamemode for " + victem.getName() + " to creative!", "xEssentials.gamemode");
								for(Player player : Bukkit.getOnlinePlayers()) {
									if(player.hasPermission("xEssentials.command.gamemode")) {
										player.sendMessage(ChatColor.GRAY + sender.getName() + " has set the gamemode for " + victem.getName() + " to creative!");
									}
								}
								victem.sendMessage(ChatColor.DARK_GRAY + "gamemode has been changed to creative by " + sender.getName());
								if(useInventorySystem()) {
									saveSurvivalInventory(victem);
									loadCreativeInventory(victem);
									victem.setGameMode(GameMode.CREATIVE);
									if(hooks.isWorldGuardEnabled()) {
										worldguard.turnOffWand(victem);	
									}
								} else {
									victem.setGameMode(GameMode.CREATIVE);
									if(hooks.isWorldGuardEnabled()) {
										worldguard.turnOffWand(victem);	
									}
								}
							} else {
								sender.sendMessage(ChatColor.RED + "this player is allready in creative mode!");
							}
						} else {
							sender.sendMessage(ChatColor.RED + "this player is not online!");
						}
					}
				}
			} else {
				playerPermission.getPermissionError(sender, cmd, args);
			}
		}
		return false;
	}

	public static boolean useInventorySystem() {
		try {
			if(fileManager.file_exists("player.yml", fileManager.getDir())) {
				if(fileManager.getBooleanValue("player.yml", "useSeperatedInventorys", fileManager.getDir())) {	
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static void saveSurvivalInventory(Player p) {
		try {
			if(fileManager.file_exists(p.getName().toLowerCase() + "_survival.yml", fileManager.getDir() + File.separator + "gamemodes")) {
				fileManager.returnFile(p.getName().toLowerCase() + "_survival.yml", fileManager.getDir() + File.separator + "gamemodes").delete();
				fileManager.writeFile(p.getName().toLowerCase() + "_survival.yml", "inv", p.getPlayer().getInventory().getContents(), fileManager.getDir() + File.separator + "gamemodes");
				fileManager.writeFile(p.getName().toLowerCase() + "_survival.yml", "armor", p.getPlayer().getInventory().getArmorContents(), fileManager.getDir() + File.separator + "gamemodes");
			} else {
				fileManager.writeFile(p.getName().toLowerCase() + "_survival.yml", "inv", p.getPlayer().getInventory().getContents(), fileManager.getDir() + File.separator + "gamemodes");
				fileManager.writeFile(p.getName().toLowerCase() + "_survival.yml", "armor", p.getPlayer().getInventory().getArmorContents(), fileManager.getDir() + File.separator + "gamemodes");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void loadSurvivalInventory(Player p) {
		try {
			if(fileManager.file_exists(p.getName().toLowerCase() + "_survival.yml", fileManager.getDir() + File.separator + "gamemodes")) {
				List<?> list = fileManager.getListValue(p.getName().toLowerCase() + "_survival.yml", "inv", fileManager.getDir() + File.separator + "gamemodes");
				List<?> armor = fileManager.getListValue(p.getName().toLowerCase() + "_survival.yml", "armor", fileManager.getDir() + File.separator + "gamemodes");
				if (list != null) {
					for (int i = 0; i < Math.min(list.size(), p.getInventory().getSize()); i++) {
						p.getInventory().setItem(i, (ItemStack)list.get(i));
					}
				}
				for(int i = 0; i < Math.min(armor.size(), p.getInventory().getArmorContents().length); i++) {
					if(i == 0) {
						ItemStack item = new ItemStack((ItemStack) armor.get(3));
						p.getPlayer().getInventory().setHelmet(item);
					} else if(i == 1) {
						ItemStack item = new ItemStack((ItemStack) armor.get(2));
						p.getPlayer().getInventory().setChestplate(item);
					} else if(i == 2) {
						ItemStack item = new ItemStack((ItemStack) armor.get(1));
						p.getPlayer().getInventory().setLeggings(item);
					} else if(i == 3) {
						ItemStack item = new ItemStack((ItemStack) armor.get(0));
						p.getPlayer().getInventory().setBoots(item);
					}
				}
				armor.clear();
				list.clear();
			} else {
				return;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void saveCreativeInventory(Player p) {
		try {
			if(fileManager.file_exists(p.getName().toLowerCase() + "_creative.yml", fileManager.getDir() + File.separator + "gamemodes")) {
				fileManager.returnFile(p.getName().toLowerCase() + "_creative.yml", fileManager.getDir() + File.separator + "gamemodes").delete();
				fileManager.writeFile(p.getName().toLowerCase() + "_creative.yml", "inv", p.getPlayer().getInventory().getContents(), fileManager.getDir() + File.separator + "gamemodes");
				fileManager.writeFile(p.getName().toLowerCase() + "_creative.yml", "armor", p.getPlayer().getInventory().getArmorContents(), fileManager.getDir() + File.separator + "gamemodes");
			} else {
				fileManager.writeFile(p.getName().toLowerCase() + "_creative.yml", "inv", p.getPlayer().getInventory().getContents(), fileManager.getDir() + File.separator + "gamemodes");
				fileManager.writeFile(p.getName().toLowerCase() + "_creative.yml", "armor", p.getPlayer().getInventory().getArmorContents(), fileManager.getDir() + File.separator + "gamemodes");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void loadCreativeInventory(Player p) {
		try {
			if(fileManager.file_exists(p.getName().toLowerCase() + "_creative.yml", fileManager.getDir() + File.separator + "gamemodes")) {
				List<?> list = fileManager.getListValue(p.getName().toLowerCase() + "_creative.yml", "inv", fileManager.getDir() + File.separator + "gamemodes");
				List<?> armor = fileManager.getListValue(p.getName().toLowerCase() + "_creative.yml", "armor", fileManager.getDir() + File.separator + "gamemodes");
				if (list != null) {
					for (int i = 0; i < Math.min(list.size(), p.getInventory().getSize()); i++) {
						p.getInventory().setItem(i, (ItemStack)list.get(i));
					}
				}
				for(int i = 0; i < Math.min(armor.size(), p.getInventory().getArmorContents().length); i++) {
					if(i == 0) {
						ItemStack item = new ItemStack((ItemStack) armor.get(3));
						p.getPlayer().getInventory().setHelmet(item);
					} else if(i == 1) {
						ItemStack item = new ItemStack((ItemStack) armor.get(2));
						p.getPlayer().getInventory().setChestplate(item);
					} else if(i == 2) {
						ItemStack item = new ItemStack((ItemStack) armor.get(1));
						p.getPlayer().getInventory().setLeggings(item);
					} else if(i == 3) {
						ItemStack item = new ItemStack((ItemStack) armor.get(0));
						p.getPlayer().getInventory().setBoots(item);
					}
				}
				armor.clear();
				list.clear();
			} else {
				return;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
