package tv.mineinthebox.essentials.commands;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import tv.mineinthebox.essentials.fileManager;
import tv.mineinthebox.essentials.permissions.playerPermission;

public class cmdConfiguration {
	
	public static ArrayList<String> entrys = new ArrayList<String>();
	
	public static boolean execute(CommandSender sender, Command cmd, String[] args) {
		if(cmd.getName().equalsIgnoreCase("configuration")) {
			if(sender.hasPermission("xEssentials.command.configuration")) {
				sender.sendMessage(ChatColor.GREEN + "doing configuration files from xEssentials 1.1 to 1.2");
				File[] playerFiles = fileManager.getFileList(fileManager.getDir() + File.separator + "players");
				for(File f : playerFiles) {
					entrys.add(f.getName());
					File replacement = new File(f.getPath().replace(f.getName(), f.getName().toLowerCase()));
					f.renameTo(replacement);
				}
				File[] gamemodes = fileManager.getFileList(fileManager.getDir() + File.separator + "gamemodes");
				for(File f : gamemodes) {
					entrys.add(f.getName());
					File replacement = new File(f.getPath().replace(f.getName(), f.getName().toLowerCase()));
					f.renameTo(replacement);
				}
				File[] alts = fileManager.getFileList(fileManager.getDir() + File.separator + "alts");
				for(File f : alts) {
					entrys.add(f.getName());
					File replacement = new File(f.getPath().replace(f.getName(), f.getName().toLowerCase()));
					f.renameTo(replacement);
				}
				File[] bans = fileManager.getFileList(fileManager.getDir() + File.separator + "bans");
				for(File f : bans) {
					entrys.add(f.getName());
					File replacement = new File(f.getPath().replace(f.getName(), f.getName().toLowerCase()));
					f.renameTo(replacement);
				}
				File[] inv = fileManager.getFileList(fileManager.getDir() + File.separator + "inventory");
				for(File f : inv) {
					entrys.add(f.getName());
					File replacement = new File(f.getPath().replace(f.getName(), f.getName().toLowerCase()));
					f.renameTo(replacement);
				}
				File[] modreq = fileManager.getFileList(fileManager.getDir() + File.separator + "modreq");
				for(File f : modreq) {
					entrys.add(f.getName());
					File replacement = new File(f.getPath().replace(f.getName(), f.getName().toLowerCase()));
					f.renameTo(replacement);
				}
				File[] modreq_done = fileManager.getFileList(fileManager.getDir() + File.separator + "modreq_done");
				for(File f : modreq_done) {
					entrys.add(f.getName());
					File replacement = new File(f.getPath().replace(f.getName(), f.getName().toLowerCase()));
					f.renameTo(replacement);
				}
				File[] homes = fileManager.getFileList(fileManager.getDir() + File.separator + "homes");
				for(File f : homes) {
					entrys.add(f.getName());
					File replacement = new File(f.getPath().replace(f.getName(), f.getName().toLowerCase()));
					f.renameTo(replacement);
				}
				File[] warps = fileManager.getFileList(fileManager.getDir() + File.separator + "warps");
				for(File f : warps) {
					entrys.add(f.getName());
					File replacement = new File(f.getPath().replace(f.getName(), f.getName().toLowerCase()));
					f.renameTo(replacement);
				}
				File[] vanish = fileManager.getFileList(fileManager.getDir() + File.separator + "vanish");
				for(File f : vanish) {
					entrys.add(f.getName());
					File replacement = new File(f.getPath().replace(f.getName(), f.getName().toLowerCase()));
					f.renameTo(replacement);
				}
				File[] compass = fileManager.getFileList(fileManager.getDir() + File.separator + "compass");
				for(File f : compass) {
					entrys.add(f.getName());
					File replacement = new File(f.getPath().replace(f.getName(), f.getName().toLowerCase()));
					f.renameTo(replacement);
				}
				sender.sendMessage(ChatColor.GREEN + "xEssentials configuration conversion done! " + entrys.size() + " changed files");
				entrys.clear();
			} else {
				playerPermission.getPermissionError(sender, cmd, args);
			}
		}
		return false;
	}

}
