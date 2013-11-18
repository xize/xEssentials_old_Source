package tv.mineinthebox.essentials.events.vanishEvents;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.DoubleChestInventory;
import org.bukkit.inventory.Inventory;

import tv.mineinthebox.essentials.resources.vanish.vanishApi;

public class vanishInteract implements Listener {
	
	@EventHandler
	public void vanishOnInteract(PlayerInteractEvent e) {
		if(vanishApi.isVanished(e.getPlayer())) {
			if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
				if(e.getClickedBlock().getType() == Material.CHEST || e.getClickedBlock().getType() == Material.TRAPPED_CHEST) {
					if(e.getClickedBlock().getState() instanceof Chest) {
						Chest chest = (Chest) e.getClickedBlock().getState();
						if(chest.getInventory().getSize() == 27) {
							Inventory inv = Bukkit.createInventory(chest, chest.getInventory().getType());
							inv.setContents(chest.getInventory().getContents());
							e.getPlayer().openInventory(inv);
							e.setCancelled(true);
							e.getPlayer().sendMessage(ChatColor.GRAY + "you are vanished so you silence accessed the chest");
						} else if(chest.getInventory().getSize() == 54) {
							DoubleChestInventory inv = (DoubleChestInventory) chest.getInventory();
							Inventory inv2 = Bukkit.createInventory(inv.getHolder(), 54);
							inv2.setContents(inv.getContents());
							e.getPlayer().openInventory(inv2);
							e.setCancelled(true);
							e.getPlayer().sendMessage(ChatColor.GRAY + "you are vanished so you silence accessed the chest");
						}
					}
				} else {
					return;
				}
			}
		}
	}

}
