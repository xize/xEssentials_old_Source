package tv.mineinthebox.events.chatEvent;

import java.io.File;

import org.anjocaido.groupmanager.GroupManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.plugin.Plugin;

import ru.tehkode.permissions.bukkit.PermissionsEx;
import tv.mineinthebox.fileManager;
import tv.mineinthebox.xEssentials;
import tv.mineinthebox.xEssentialsMemory;
import tv.mineinthebox.resources.vanish.vanishApi;

import com.dthielke.herochat.Herochat;

import de.bananaco.bpermissions.api.ApiLayer;
import de.bananaco.bpermissions.api.CalculableType;

@SuppressWarnings("deprecation")
public class chatEvent implements Listener {

	@EventHandler
	public void onSmile(PlayerChatEvent e) {
		//checking on chat plugins:), for the actually suffix recall
		if(xEssentials.getPlugin().getServer().getPluginManager().isPluginEnabled("PermissionsEx")) {

			String pex = PermissionsEx.getUser(e.getPlayer().getName()).getSuffix();
			e.setMessage(xEssentialsMemory.setSmilleys(getMessage(e.getMessage(), pex), pex));

		} else if(xEssentials.getPlugin().getServer().getPluginManager().isPluginEnabled("GroupManager")) {
			Plugin groupManager = (GroupManager) e.getPlayer().getServer().getPluginManager().getPlugin("GroupManager");
			GroupManager groupHandler = (GroupManager) groupManager;
			String suffix = groupHandler.getWorldsHolder().getWorldPermissions(e.getPlayer()).getUserSuffix(e.getPlayer().getName());
			e.setMessage(xEssentialsMemory.setSmilleys(getMessage(e.getMessage(), suffix), suffix));
		} else if(xEssentials.getPlugin().getServer().getPluginManager().isPluginEnabled("HeroChat")) {
			String herochat = Herochat.getChatService().getPlayerSuffix(e.getPlayer().getWorld(), e.getPlayer().getName());
			e.setMessage(xEssentialsMemory.setSmilleys(getMessage(e.getMessage(), herochat), herochat));
		} else if(xEssentials.getPlugin().getServer().getPluginManager().isPluginEnabled("bPermissions")) {
			String bPermissions = ApiLayer.getValue(e.getPlayer().getWorld().getName(), CalculableType.USER, e.getPlayer().getName(), "suffix");
			e.setMessage(xEssentialsMemory.setSmilleys(getMessage(e.getMessage(), bPermissions), bPermissions));
		} else {
			String color = "&f";
			e.setMessage(xEssentialsMemory.setSmilleys(getMessage(e.getMessage(), color), color));
		}
	}

	public String getMessage(String message, String suffixCallBack) {
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
