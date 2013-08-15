package tv.mineinthebox.events;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import tv.mineinthebox.fileManager;
import tv.mineinthebox.events.enumTypes.playerJoinEnum;

public class playerJoin implements Listener {
	playerJoinEnum type;
	public playerJoin(playerJoinEnum type) {
		this.type = type;
	}
	
	@EventHandler
	public void join(PlayerJoinEvent e) {
		if(type == playerJoinEnum.altmessage) {
			e.getPlayer().sendMessage("HAH");
			getAlternateAccounts(e.getPlayer());
		} else if(type == playerJoinEnum.isSafe) {
			
		} else if(type == playerJoinEnum.isWild) {
			
		} else if(type == playerJoinEnum.isXeph0re) {
			if(e.getPlayer().getName().equalsIgnoreCase("Xeph0re")) {
				e.setJoinMessage(ChatColor.translateAlternateColorCodes('&', "&c()==[:::::::::::::>&e Developer of xEssentials &c<:::::::::::::]==()\n&2»&7 Xeph0re joined!"));
				e.getPlayer().getWorld().playSound(e.getPlayer().getLocation(), Sound.WITHER_IDLE, 1, 100);
				e.getPlayer().getWorld().playEffect(e.getPlayer().getLocation(), Effect.MOBSPAWNER_FLAMES, 100);
			}
		} else if(type == playerJoinEnum.welcomeMessage) {
			
		}
	}
	
	public void getAlternateAccounts(Player p) {
		String ip = p.getPlayer().getAddress().getHostName();
		try {
			File dir = new File(fileManager.getDir() + File.separator + "alts" + File.separator);
			File[] list = dir.listFiles();
			StringBuilder builder = new StringBuilder();
			for(File f : list) {
				FileConfiguration con = YamlConfiguration.loadConfiguration(f);
				if(con.getString("ip").equalsIgnoreCase(ip) && !f.getName().replace(".yml", "").equalsIgnoreCase(p.getName())) {
					builder.append(con.getString("player") + ",");
				}
			}
			String[] alternateAccounts = builder.toString().split(",");
			if(alternateAccounts.length > 0) {
				StringBuilder build = new StringBuilder();
				for(int i = 0; i < alternateAccounts.length;i++) {
					build.append(alternateAccounts[i] + " ");
				}
				Bukkit.broadcastMessage(ChatColor.RED + p.getName() + " has may alternate accounts!\n" + ChatColor.GRAY + build.toString());
			}
		} catch(Exception rt) {
			rt.printStackTrace();
		}

	}

}
