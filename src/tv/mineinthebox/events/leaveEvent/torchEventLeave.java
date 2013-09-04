package tv.mineinthebox.events.leaveEvent;

import java.util.LinkedList;

import org.bukkit.block.BlockState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import tv.mineinthebox.events.playerMoveEvent.torchEvent;

public class torchEventLeave implements Listener {
	@EventHandler
	public void torch(PlayerQuitEvent e) {
		if(torchEvent.isTorch(e.getPlayer())) {
			if(torchEvent.list.containsKey(e.getPlayer())) {
				LinkedList<BlockState> list = torchEvent.list.get(e.getPlayer());
				list.clear();
				torchEvent.list.remove(e.getPlayer());
			}
		}
	}

}
