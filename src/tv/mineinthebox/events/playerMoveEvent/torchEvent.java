package tv.mineinthebox.events.playerMoveEvent;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import tv.mineinthebox.xEssentialsMemory;

public class torchEvent implements Listener {
	public static Map<Player, LinkedList<BlockState>> list = new HashMap<Player, LinkedList<BlockState>>();
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public static void torch(PlayerMoveEvent e) {
		if(isTorch(e.getPlayer())) {
			if(e.getPlayer().getItemInHand().getType() == Material.TORCH) {
				Block block = e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN);
				if(block.getType() == Material.AIR || block.getType() == Material.WATER || block.getType() == Material.STATIONARY_WATER || block.getType() == Material.LAVA || block.getType() == Material.STATIONARY_LAVA) {
					return;
				}
				e.getPlayer().sendBlockChange(block.getLocation(), Material.GLOWSTONE, block.getData());
				getTrailList(e.getPlayer()).add(block.getState());
				removeGlow(e.getPlayer());
			}
		}
	}
	
	public static LinkedList<BlockState> getTrailList(Player p) {
		LinkedList<BlockState> blocklist = list.get(p);
		if(blocklist == null) {
			blocklist = new LinkedList<BlockState>();
			list.put(p, blocklist);
		}
		return blocklist;
	}
	
	public static void removeGlow(Player p) {
		LinkedList<BlockState> blocklist = list.get(p);
		if(blocklist.size() > 10) {
			BlockState old = blocklist.removeFirst();
			old.update();
		}
	}
	
	public static boolean hasTrail(Player p) {
		return list.containsKey(p);
	}
	
	public static boolean isTorch(Player p) {
		if(xEssentialsMemory.returnPlayer(p).containsKey("torch")) {
			Boolean bol = (Boolean) xEssentialsMemory.returnPlayer(p).get("torch");
			if(bol instanceof Boolean) {
				if(bol) {
					return true;
				}
			}
		}
		return false;
	}

}
