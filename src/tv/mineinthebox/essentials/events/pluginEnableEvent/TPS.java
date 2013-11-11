package tv.mineinthebox.essentials.events.pluginEnableEvent;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginEnableEvent;

import tv.mineinthebox.essentials.xEssentials;

public class TPS implements Listener {
	
	public static int tps = 0;
	public static long second = 0;
	
	public static float getServerTicks() {
		return tps;
	}

	@EventHandler
	public void onPluginEnable(PluginEnableEvent e) {
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(xEssentials.getPlugin(), new Runnable() {
			long sec;
			int ticks;
			@Override
			public void run() {
				sec = System.currentTimeMillis() / 1000;
				if (second == sec) {
					ticks++; 
				} else {
					second = sec;
					tps = tps == 0 ? ticks : (tps + ticks) / 2;
					ticks = 0;
				}
			}
		}, 21, 1);
	}

}
