package tv.mineinthebox.essentials.hook;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

import tv.mineinthebox.essentials.xEssentialsMemory;
import tv.mineinthebox.essentials.events.pvp.Economy;

public class Vault {
	
	public static void desposit(Player p, Double amount) {
		RegisteredServiceProvider<Economy> economyProvider = Bukkit.getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
		Economy econ = economyProvider.getProvider();
		econ.depositPlayer(p.getName(), amount);
	}

}
