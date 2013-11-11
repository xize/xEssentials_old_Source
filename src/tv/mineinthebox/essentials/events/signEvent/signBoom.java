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
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

public class signBoom implements Listener {
	
	public static HashSet<String> boomplayer = new HashSet<String>();
	public static HashSet<String> newPlayer = new HashSet<String>();
	
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
						if(newPlayer.contains(s.getPlayer().getName())) {
							ItemStack sword = new ItemStack(Material.STONE_SWORD);
							ItemStack pick = new ItemStack(Material.STONE_PICKAXE);
							ItemStack shovel = new ItemStack(Material.STONE_SPADE);
							ItemStack axe = new ItemStack(Material.STONE_AXE);
							ItemStack wood = new ItemStack(Material.WOOD);
							ItemStack torch = new ItemStack(Material.TORCH);
							ItemStack bed = new ItemStack(Material.BED);
							ItemStack melon = new ItemStack(Material.MELON);
							sword.setAmount(1);
							pick.setAmount(1);
							shovel.setAmount(1);
							axe.setAmount(1);
							wood.setAmount(28);
							torch.setAmount(8);
							bed.setAmount(1);
							melon.setAmount(32);
							//log.info("This player has interacted with this sign");
							s.getPlayer().getServer().dispatchCommand(Bukkit.getConsoleSender(), "boom " + s.getPlayer().getName());
							s.getPlayer().getInventory().addItem(sword);
							s.getPlayer().getInventory().addItem(pick);
							s.getPlayer().getInventory().addItem(shovel);
							s.getPlayer().getInventory().addItem(axe);
							s.getPlayer().getInventory().addItem(wood);
							s.getPlayer().getInventory().addItem(torch);
							s.getPlayer().getInventory().addItem(bed);
							s.getPlayer().getInventory().addItem(melon);
							s.getPlayer().sendMessage(ChatColor.GOLD + "[Boom] " + ChatColor.GREEN + "Because you are new on the server we gave you some items!");
							newPlayer.remove(s.getPlayer().getName());
						}
					}
				}
			}
		}
	}
	
	@EventHandler
	public void newplayer(PlayerJoinEvent e) {
		if(!e.getPlayer().hasPlayedBefore()) {
			newPlayer.add(e.getPlayer().getName());
		}
	}
	
	@EventHandler
	public void pquit(PlayerQuitEvent e) {
		if(newPlayer.contains(e.getPlayer().getName())) {
			newPlayer.remove(e.getPlayer().getName());
		}
		if(boomplayer.contains(e.getPlayer().getName())) {
			boomplayer.remove(e.getPlayer().getName());
		}
	}
	
	@EventHandler
	public void newplayer(PlayerKickEvent e) {
		if(newPlayer.contains(e.getPlayer().getName())) {
			newPlayer.remove(e.getPlayer().getName());
		}
		if(boomplayer.contains(e.getPlayer().getName())) {
			boomplayer.remove(e.getPlayer().getName());
		}
	}
	
	@EventHandler
	public void pquit(PlayerKickEvent e) {
		if(newPlayer.contains(e.getPlayer().getName())) {
			newPlayer.remove(e.getPlayer().getName());
		}
		if(boomplayer.contains(e.getPlayer().getName())) {
			boomplayer.remove(e.getPlayer().getName());
		}
	}

}
