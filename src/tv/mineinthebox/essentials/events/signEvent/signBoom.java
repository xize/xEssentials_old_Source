package tv.mineinthebox.essentials.events.signEvent;

import java.util.HashSet;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class signBoom implements Listener {

	public static HashSet<String> boomplayer = new HashSet<String>();

	public static void setInArray(String string) {
		boomplayer.add(string);
	}

	@EventHandler
	public void m(PlayerMoveEvent p) {
		if(boomplayer.contains(p.getPlayer().getName())) {
			if(p.getPlayer().getLocation().getBlock().getRelative(BlockFace.SELF).getType() == Material.WEB) {
				boomplayer.remove(p.getPlayer().getName());
			} else {
				p.getPlayer().getWorld().createExplosion(p.getPlayer().getLocation(), 0.0F, false);
			}
		}
	}
	@EventHandler
	public void damage(EntityDamageEvent p) {
		if(p.getEntity() instanceof Player && p.getCause() == DamageCause.FALL) {
			Player ap = (Player) p.getEntity();
			if(boomplayer.contains(ap.getName())) {
				boomplayer.remove(ap.getName());
				p.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void SignBoom(SignChangeEvent s) {
		if(s.getLine(0).equalsIgnoreCase("[boom]") && s.getPlayer().hasPermission("xEssentials.boom")) {
			s.setLine(0, ChatColor.GOLD + "[Boom]");
			s.getBlock().getState().update(true);
			s.getPlayer().sendMessage(ChatColor.GOLD + "[Boom] " + ChatColor.GREEN + "You successfully placed a boom sign!");
		} else {
			if(s.getLine(0).equalsIgnoreCase("[boom]") && !s.getPlayer().hasPermission("xEssentials.boom")) {
				s.getBlock().breakNaturally();
				s.getPlayer().sendMessage("You are not allowed to place such signs!");
				s.setCancelled(true);		
			}
		}
	}

	@EventHandler
	public void signRightClickBoom(PlayerInteractEvent s) {
		if(s.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(s.getClickedBlock().getState().getType() == Material.SIGN || s.getClickedBlock().getState().getType() == Material.SIGN_POST || s.getClickedBlock().getState().getType() == Material.WALL_SIGN) {
				//log.info("This is a sign...");
				Sign sign = (Sign) s.getClickedBlock().getState();
				if(sign.getLine(0).contains("[Boom]")) {
					//log.info("Launching dispatch command....");
					if(s.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
						s.getPlayer().sendMessage(ChatColor.GOLD + "[Boom] " + ChatColor.RED + "You need to be survival to do this!");
						s.setCancelled(true);
					} else {
						//log.info("This player has interacted with this sign");
						s.getPlayer().getServer().dispatchCommand(Bukkit.getConsoleSender(), "boom " + s.getPlayer().getName());
						s.getPlayer().sendMessage(ChatColor.GOLD + "[Boom] " + ChatColor.GREEN + "boooooom!");
					}
				}
			}
		}
	}

	@EventHandler
	public void newplayer(PlayerKickEvent e) {
		if(boomplayer.contains(e.getPlayer().getName())) {
			boomplayer.remove(e.getPlayer().getName());
		}
	}

	@EventHandler
	public void pquit(PlayerKickEvent e) {
		if(boomplayer.contains(e.getPlayer().getName())) {
			boomplayer.remove(e.getPlayer().getName());
		}
	}

}
