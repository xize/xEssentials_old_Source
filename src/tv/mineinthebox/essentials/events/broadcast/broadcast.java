package tv.mineinthebox.essentials.events.broadcast;

import java.util.ArrayList;
import java.util.Iterator;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import tv.mineinthebox.essentials.fileManager;
import tv.mineinthebox.essentials.xEssentials;
import tv.mineinthebox.essentials.xEssentialsMemory;
import tv.mineinthebox.essentials.events.handler;

public class broadcast {

	public static BukkitTask taskId;

	public static int i = 0;

	public static ArrayList<String> broadcastMessages() {
		return xEssentialsMemory.broadcast_list;
	}

	public static String returnPrefix() {
		return xEssentialsMemory.broadcast_prefix;
	}

	public static String returnSuffix() {
		return xEssentialsMemory.broadcast_suffix;
	}

	public static void activateBroadcast() {
		taskId = Bukkit.getScheduler().runTaskTimer(xEssentials.getPlugin(), new Runnable() {
			@Override
			public void run() {
				for(Player p : Bukkit.getOnlinePlayers()) {
					if(i < broadcastMessages().size()) {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', returnPrefix() + ": " + returnSuffix() + broadcastMessages().get(i).replace("%p", "&2@"+p.getName()+returnSuffix())));
					} else {
						i = -1;
					}
				}
				i++;
			}

		}, 0, 1500);
	}

	public static void broadcastReload() {
		broadcastStop();
		handler.restartListeners();
	}

	public static void broadcastStop() {
		Bukkit.getScheduler().cancelTask(taskId.getTaskId());
		broadcastMessages().clear();
	}

	public static void addNewBroadcast(String message) {
		broadcastMessages().add(message);
		fileManager.writeFile("broadcast.yml", "broadcast.messages", broadcastMessages(), fileManager.getDir());
		broadcastReload();
	}

	public static void setPrefix(String prefixM) {
		fileManager.writeFile("broadcast.yml", "broadcast.prefix", prefixM, fileManager.getDir());
		broadcastReload();
	}

	public static void setSuffix(String SuffixM) {
		fileManager.writeFile("broadcast.yml", "broadcast.suffix", SuffixM, fileManager.getDir());
		broadcastReload();
	}

	public static String showIDs() {
		if(broadcastMessages().size() > 0) {
			Iterator<String> it1 = broadcastMessages().iterator();
			StringBuilder build = new StringBuilder();
			int i = 0;
			while(it1.hasNext()) {
				build.append("#" + i + ": " + it1.next() + "\n").toString();
				i++;
			}
			return build.toString();
		}
		return null;
	}

	public static boolean deleteBroadcast(int id) {
		if(broadcastMessages().get(id) != null) {
			broadcastMessages().remove(id);
			fileManager.writeFile("broadcast.yml", "broadcast.messages", broadcastMessages(), fileManager.getDir());
			broadcastReload();
			return true;
		} else {
			return false;
		}
	}

}
