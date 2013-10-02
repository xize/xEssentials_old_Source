package tv.mineinthebox.resources.ghostfactory;

import java.util.HashMap;
import java.util.HashSet;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import tv.mineinthebox.xEssentials;

public class ghost {
	public static HashSet<String> usernames = new HashSet<String>();
	public static HashMap<String, BukkitTask> tasks = new HashMap<String, BukkitTask>();
	
	public static boolean isGhost(String username) {
		if(usernames.contains(username)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static void setGhost(final Player p) {
		if(!usernames.contains(p.getName())) {
			usernames.add(p.getName());
			Scoreboard board = Bukkit.getServer().getScoreboardManager().getMainScoreboard();
			Team ghostTeam = board.getTeam("GHOST_TEAM_NAME");
			if(ghostTeam == null) {
	            ghostTeam = board.registerNewTeam("GHOST_TEAM_NAME");
	            ghostTeam.setCanSeeFriendlyInvisibles(true);
	            for(Player pr : Bukkit.getOnlinePlayers()) {
	            	ghostTeam.addPlayer(pr);
	            }
	            ghostTeam.addPlayer(p);
	            BukkitTask task = Bukkit.getScheduler().runTaskTimer(xEssentials.getPlugin(), new Runnable() {
					public void run() {
						if(p instanceof Player) {
							p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 1000, 15));
						} else {
							removeGhost(p);
						}
					}
	            }, 0, 20);
	            tasks.put(p.getName(), task);
			} else {
				ghostTeam.addPlayer(p);
	            for(Player pr : Bukkit.getOnlinePlayers()) {
	            	ghostTeam.addPlayer(pr);
	            }
	            BukkitTask task = Bukkit.getScheduler().runTaskTimer(xEssentials.getPlugin(), new Runnable() {
					public void run() {
						p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 1000, 15));
					}
	            }, 0, 10L);
	            tasks.put(p.getName(), task);
			}
		}
	}
	
	public static void removeGhost(final Player p) {
		if(usernames.contains(p.getName())) {
			if(tasks.containsKey(p.getName())) {
				Bukkit.getScheduler().cancelTask(tasks.get(p.getName()).getTaskId());
				tasks.remove(p.getName());
				usernames.remove(p.getName());
				if(p instanceof Player) {
				p.removePotionEffect(PotionEffectType.INVISIBILITY);
				} else {
					return;
				}
			}
		}
	}
	
	public static void removePlayerFromTeam(Player p) {
		Scoreboard board = Bukkit.getServer().getScoreboardManager().getMainScoreboard();
		Team ghostTeam = board.getTeam("GHOST_TEAM_NAME");
		if(ghostTeam != null) {
			if(ghostTeam.hasPlayer(p)) {
				ghostTeam.removePlayer(p);
			} else {
				return;
			}
		}
	}
	
	public static void addPlayerToTeam(Player p) {
		Scoreboard board = Bukkit.getServer().getScoreboardManager().getMainScoreboard();
		Team ghostTeam = board.getTeam("GHOST_TEAM_NAME");
		if(ghostTeam != null) {
			if(ghostTeam.hasPlayer(p)) {
				ghostTeam.addPlayer(p);
			} else {
				return;
			}
		}
	}
}
