package tv.mineinthebox.essentials.events.joinEvent;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import tv.mineinthebox.essentials.fileManager;

public class modreqEvent implements Listener {
	@EventHandler
	public void modreqListenerEvent(PlayerJoinEvent e) {
		if(e.getPlayer().hasPermission("xEssentials.command.check.admin")) {
			if(fileManager.file_exists(e.getPlayer().getName() + ".yml", fileManager.getDir() + File.separator + "modreq_done")) {
				File f = fileManager.returnFile(e.getPlayer().getName() + ".yml", fileManager.getDir() + File.separator + "modreq_done");
				String date = fileManager.getStringValue(e.getPlayer().getName() + ".yml", "date", fileManager.getDir() + File.separator + "modreq_done");
				String comment = fileManager.getStringValue(e.getPlayer().getName() + ".yml", "comment", fileManager.getDir() + File.separator + "modreq_done");
				String helped = fileManager.getStringValue(e.getPlayer().getName() + ".yml", "helped", fileManager.getDir() + File.separator + "modreq_done");
				e.getPlayer().sendMessage(ChatColor.GREEN + "[" + date + "]" + "your modreq has been closed by staff member " + helped);
				e.getPlayer().sendMessage(ChatColor.GRAY + "comment: " + comment);
				f.delete();
			}
			File[] list = fileManager.getFileList(fileManager.getDir() + File.separator + "modreq");
			int size = list.length;
			if(size > 0) {
				e.getPlayer().sendMessage(ChatColor.GOLD + "[modreq]" + ChatColor.RED + "there are modreqs open, " + size + " in total.");	
			} else {
				e.getPlayer().sendMessage(ChatColor.GOLD + "[modreq]" + ChatColor.GRAY + "currently there are no modreqs open!");	
			}
		} else {
			if(fileManager.file_exists(e.getPlayer().getName() + ".yml", fileManager.getDir() + File.separator + "modreq_done")) {
				File f = fileManager.returnFile(e.getPlayer().getName() + ".yml", fileManager.getDir() + File.separator + "modreq_done");
				String date = fileManager.getStringValue(e.getPlayer().getName() + ".yml", "date", fileManager.getDir() + File.separator + "modreq_done");
				String comment = fileManager.getStringValue(e.getPlayer().getName() + ".yml", "comment", fileManager.getDir() + File.separator + "modreq_done");
				String helped = fileManager.getStringValue(e.getPlayer().getName() + ".yml", "helped", fileManager.getDir() + File.separator + "modreq_done");
				e.getPlayer().sendMessage(ChatColor.GREEN + "[" + date + "]" + "your modreq has been closed by staff member " + helped);
				e.getPlayer().sendMessage(ChatColor.GRAY + "comment: " + comment);
				f.delete();
			}
		}
	}
}
