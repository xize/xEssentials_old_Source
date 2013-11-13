package tv.mineinthebox.essentials.events.chatEvent;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import tv.mineinthebox.essentials.fileManager;
import tv.mineinthebox.essentials.xEssentialsMemory;
import tv.mineinthebox.essentials.hook.bPermissions;
import tv.mineinthebox.essentials.hook.groupmanager;
import tv.mineinthebox.essentials.hook.herochat;
import tv.mineinthebox.essentials.hook.hooks;
import tv.mineinthebox.essentials.hook.permissionsEx;
import tv.mineinthebox.essentials.resources.vanish.vanishApi;

@SuppressWarnings("deprecation")
public class chatEvent implements Listener {

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled=true)
	public void onSmile(PlayerChatEvent e) {
		//checking on chat plugins:), for the actually suffix recall
		if(hooks.isPexEnabled()) {
			String suffix = permissionsEx.getSuffx(e.getPlayer());
			e.setMessage(xEssentialsMemory.setSmilleys(getMessage(e.getMessage(), suffix), suffix));
		} else if(hooks.isGroupManagerEnabled()) {
			String suffix = groupmanager.getSuffix(e.getPlayer());
			e.setMessage(xEssentialsMemory.setSmilleys(getMessage(e.getMessage(), suffix), suffix));
		} else if(hooks.isHerochatEnabeld()) {
			String hero = herochat.getSuffix(e.getPlayer());
			e.setMessage(xEssentialsMemory.setSmilleys(getMessage(e.getMessage(), hero), hero));
		} else if(hooks.isbPermissionsEnabled()) {
			String bpermissions = bPermissions.getSuffix(e.getPlayer());
			e.setMessage(xEssentialsMemory.setSmilleys(getMessage(e.getMessage(), bpermissions), bpermissions));
		} else {
			String color = "&f";
			e.setMessage(xEssentialsMemory.setSmilleys(getMessage(e.getMessage(), color), color));
		}
	}

	/*public String getMessage(String message, String suffixCallBack) {
		if(xEssentialsMemory.hightlights) {
			String hashtag = ChatColor.translateAlternateColorCodes('&', xEssentialsMemory.hashtag);
			for(Player p : Bukkit.getOnlinePlayers()) {
				if(message.contains(p.getName())) {
					if(vanishApi.isVanished(p)) {
						return message.toString().replace(p.getName(), ChatColor.GRAY + ChatColor.translateAlternateColorCodes('&',"&3[offline]" + hashtag + p.getName()));
					} else {
						message.toString().replace(p.getName(), hashtag + p.getName() + ChatColor.translateAlternateColorCodes('&', suffixCallBack));
						effects(p);
						return message.toString().replace(p.getName(), ChatColor.translateAlternateColorCodes('&', hashtag + p.getName()) + ChatColor.translateAlternateColorCodes('&', suffixCallBack));
					}
				}
			}
			if(fileManager.isDirectory(fileManager.getDir() + File.separator + "alts")) {
				for(File user : fileManager.getFileList(fileManager.getDir() + File.separator + "alts")) {
					if(message.contains(user.getName().replace(".yml", ""))) {
						if(vanishApi.isVanishedGetString(user.getName().replace(".yml", ""))) {
							return message.toString().replace(user.getName().replace(".yml", ""), ChatColor.translateAlternateColorCodes('&',  "&3[offline]" + hashtag + user.getName().replace(".yml", "")) + ChatColor.translateAlternateColorCodes('&', suffixCallBack));	
						} else {
							return message.toString().replace(user.getName().replace(".yml", ""), ChatColor.translateAlternateColorCodes('&',"&3[offline]" + hashtag + user.getName().replace(".yml", "")) + ChatColor.translateAlternateColorCodes('&', suffixCallBack));	
						}
					}
				}	
			}
		}
		return message;
	}
	 */
	public String getMessage(String message, String suffix) {
		if(xEssentialsMemory.hightlights) {
			String hashtag = ChatColor.translateAlternateColorCodes('&', xEssentialsMemory.hashtag);
			String[] args = message.split(" ");
			for(String string : args) {
				if(!message.contains("@"+string)) {
					Player p = Bukkit.getPlayerExact(string);
					if(p instanceof Player) {
						if(vanishApi.isVanished(p)) {
							message = message.replaceAll(string, ChatColor.GRAY + ChatColor.translateAlternateColorCodes('&',"&3[offline]" + hashtag + p.getName()));
						} else {
							message = message.replaceAll(string, hashtag + p.getName() + ChatColor.translateAlternateColorCodes('&', suffix));
							effects(p);
						}
					} else if(fileManager.isDirectory(fileManager.getDir() + File.separator + "alts")) {
						if(fileManager.file_exists(string + ".yml", fileManager.getDir() + File.separator + "alts")) {
							message = message.replaceAll(string, ChatColor.translateAlternateColorCodes('&',"&3[offline]" + hashtag + string));
						}
					}
				}
			}
		}
		return message;
	}

	public static boolean effects(Player p) {
		if(vanishApi.isVanished(p)) {
			return false;
		} else {
			p.getWorld().playEffect(p.getLocation(), Effect.MOBSPAWNER_FLAMES, 100);
			p.playSound(p.getLocation(), Sound.NOTE_PIANO, 10, 100);
			p.playSound(p.getLocation(), Sound.NOTE_BASS_DRUM, 10, 100);
			p.playSound(p.getLocation(), Sound.NOTE_SNARE_DRUM, 10, 100);
		}
		return false;
	}


}
